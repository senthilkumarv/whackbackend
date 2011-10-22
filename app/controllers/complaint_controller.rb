require 'uuid'

class ComplaintController < ApplicationController
  def index
    @complaints = Complaint.all
    respond_to do |f|
      f.html
    end
  end

  def create
    complaint = complaint_from params
    if complaint.save
      response = json_from(:response => 201,
                           :reference_id => complaint.reference_id,
                           :status => complaint.status)
    else
      response = json_from(:response => 507,
                           :reference_id => "",
                           :status => "NA")
    end

    set_json_response response
  end

  def close
    if params["reference_id"] && params["mobile"]
      complaint = Complaint.find_by_reference_id params["reference_id"]
      if complaint && complaint.mobile == params["mobile"]
        complaint.status = "Closed"
        complaint.save
        message = "Successfully closed."
      end
    else
      message = "Provide correct mobile number and complaint id."
      complaint = nil
    end


    if complaint
      response = json_from(:response => 200,
                           :message => message,
                           :status => complaint.status,
                           :reference_id => complaint.reference_id)
    else
      response = json_from(:response => 404,
                           :message => message,
                           :status => "NA",
                           :reference_id => "NA")
    end

    set_json_response response
  end
  
  def status
    if params["reference_id"]
      complaint = Complaint.find_by_reference_id params["reference_id"]
    elsif params["mobile"]
      complaint = Complaint.find_all_by_mobile(params["mobile"]).last
    end

    if complaint
      response = json_from(:response => 200,
                           :status => complaint.status,
                           :reference_id => complaint.reference_id)
    else
      response = json_from(:response => 404,
                           :status => "NA",
                           :reference_id => "NA")
    end

    set_json_response response
  end


  private
  def json_from map
    map.to_json
  end

  def set_json_response response
    respond_to do |format|
      format.json {
        render :json => response
      }
    end
  end

  def complaint_from params
    complaint = Complaint.new
    complaint.mobile = params["mobile"]
    complaint.location = params["location"]
    complaint.name = params["name"]
    complaint.complaint_type = params["type"]
    complaint.reference_id = UUID.new.generate.gsub("-", "")[1..10].upcase!
    complaint.status = "Open"
    complaint
  end
end
