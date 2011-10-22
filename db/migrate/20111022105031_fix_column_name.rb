class FixColumnName < ActiveRecord::Migration
  def self.up
    rename_column :complaints, :type, :complaint_type 
  end

  def self.down
  end
end
