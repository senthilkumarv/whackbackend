class CreateComplaints < ActiveRecord::Migration
  def self.up
    create_table :complaints do |t|
      t.text :location
      t.text :status
      t.text :type
      t.text :name
      t.text :reference_id
      t.text :mobile

      t.timestamps
    end
  end

  def self.down
    drop_table :complaints
  end
end
