class Complaint < ActiveRecord::Base
  validates_presence_of :mobile, :location, :complaint_type
end
