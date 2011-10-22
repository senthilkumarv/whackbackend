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
    if complaint.save
      @response = {:response => 201,
        :reference_id => complaint.reference_id,
        :status => complaint.status}.to_json
    else
      @response = {:response => 507,
        :reference_id => "",
        :status => "NA"}.to_json
    end

    respond_to do |format|
      format.json {
        render :json => @response
      }
      format.html
    end
  end

  def close
    if params["id"] && params["mobile"]
      complaint = Complaint.find_by_reference_id params["id"]
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
      @response = {:response => 200,
        :message => message,
        :status => complaint.status,
        :reference_id => complaint.reference_id}.to_json
    else
      @response = {:response => 404,
        :message => message,
        :status => "NA",
        :reference_id => "NA"}.to_json
    end
    
    respond_to do |format|
      format.json {
        render :json => @response
      }
    end
  end
  
  def status
    if params["id"]
      complaint = Complaint.find_by_reference_id params["id"]
    elsif params["mobile"]
      complaint = Complaint.find_all_by_mobile(params["mobile"]).last
    end

    if complaint
      @response = {:response => 200,
        :status => complaint.status,
        :reference_id => complaint.reference_id}.to_json
    else
      @response = {:response => 404,
        :status => "NA",
        :reference_id => "NA"}.to_json
    end
    
    respond_to do |format|
      format.json {
        render :json => @response
      }
    end
  end
  
  def show
    if params[:id]
      complaint = Complaint.find_by_id params[:id]
    else
    end

    respond_to do |format|
      format.html
      format.json {
        render :json => @response
      }
    end
  end
end
