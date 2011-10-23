class AddPhotoUrlToComplaint < ActiveRecord::Migration
  def self.up
    add_column :complaints, :photo_url, :text
  end

  def self.down
    remove_column :complaints, :photo_url
  end
end
