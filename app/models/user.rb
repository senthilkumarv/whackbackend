require 'digest/sha1'

class User < ActiveRecord::Base
  attr_accessible :email, :password, :password_confirmation
  
  attr_accessor :password
  before_save :encrypt_password
  
  validates_confirmation_of :password
  validates_presence_of :password, :on => :create
  validates_presence_of :email
  validates_uniqueness_of :email
  
  def self.authenticate(email, password)
    user = find_by_email(email)
    if user && user.password_hash == self.encrypt(password, user.password_salt)
      user
    else
      nil
    end
  end
  
  def self.encrypt(pass, salt)
    Digest::SHA1.hexdigest(pass+salt)
  end
  
  def encrypt_password
    if password.present?
      self.password_salt = UUID.new.generate.gsub("-", "")[1..10]
      self.password_hash = User.encrypt password, password_salt
    end
  end
end
