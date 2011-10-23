class AddLatitudeAndLongitudeToComplaint < ActiveRecord::Migration
  def self.up
    add_column :complaints, :latitude, :float
    add_column :complaints, :longitude, :float
  end

  def self.down
    remove_column :complaints, :longitude
    remove_column :complaints, :latitude
  end
end
