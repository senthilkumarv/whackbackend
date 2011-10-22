require 'uuid'

class ComplaintController < ApplicationController
  def index
    @complaints = Complaint.all
    respond_to do |f|
      f.html
    end
  end

  def create
    complaint = Complaint.new
    complaint.mobile = params["mobile"]
    complaint.location = params["location"]
    complaint.name = params["name"]
    complaint.complaint_type = params["type"]
    complaint.reference_id = UUID.new.generate.gsub("-", "")[1..10].upcase!
    complaint.status = "Open"
    complaint.save
  end
end
