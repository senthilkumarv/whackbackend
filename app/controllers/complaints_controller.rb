require 'uuid'
include Geokit::Geocoders

class ComplaintsController < ApplicationController
  SUCCESSFULLY_REGISTERED = "Complaint was successfully registered"
  MATCH_NOT_FOUND = "Could not find complaint matching the input" 
  SUCCESSFULLY_RESOLVED = "Complaint marked as resolved"
  NEED_TO_CORRECT_DATA = "Provide correct mobile number and complaint id"
  MATCH_FOUND = "Found matching complaint"
  COULDNOT_REGISTER_COMPLAINT = "Could not register complaint. Ensure that location and complaint type are provided"
  
  def index
    @complaints = Complaint.all
    respond_to do |f|
      f.html
    end
  end

  def new
    @complaint = Complaint.new

    respond_to do |format|
      format.html
    end
  end
  
  def upload_pic
    puts 'Inside Upload Pic'
    puts params
    file = params['file']
    fileName = params['id'] + '.png'
    puts "#{file} #{fileName}"
    directory = "/Users/senthilkumarv/data"
    path = File.join(directory, fileName)
    File.open(path, "wb") { |f| f.write(file.read) }
    render :nothing => true
  end

  def show
    @complaint = Complaint.find_by_id params["id"]

    respond_to do |f|
      f.html
    end
  end
  
  def create
    @complaint = complaint_from params
    respond_to do |format|
      if @complaint.save
        format.html do
          redirect_to @complaint, :notice => "Complaint regsitered successfully"
        end

        format.json do
          render :json => json_from(:response => 200,
                                    :reference_id => @complaint.reference_id,
                                    :message => SUCCESSFULLY_REGISTERED,
                                    :status => @complaint.status)
        end
      else
        format.html do
          render :action => "new"
        end

        format.json do
          render :json => json_from(:response => 507,
                                    :reference_id => "",
                                    :status => "NA",
                                    :message => COULDNOT_REGISTER_COMPLAINT)
        end
      end
    end
  end

  def close
    if params["reference_id"] && params["mobile"]
      complaint = Complaint.find_by_reference_id params["reference_id"]
      if complaint && complaint.mobile == params["mobile"]
        complaint.status = "Closed"
        complaint.save
      end
    else
      complaint = nil
    end


    if complaint
      response = json_from(:response => 200,
                           :message => SUCCESSFULLY_RESOLVED,
                           :status => complaint.status,
                           :reference_id => complaint.reference_id)
    else
      response = json_from(:response => 404,
                           :message => NEED_TO_CORRECT_DATA,
                           :status => "NA",
                           :reference_id => "NA")
    end
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
                           :message => MATCH_FOUND,
                           :reference_id => complaint.reference_id)
    else
      response = json_from(:response => 404,
                           :message => MATCH_NOT_FOUND,
                           :status => "NA",
                           :reference_id => "NA")
    end

    set_json_response response
  end
  
  def upload_file
    puts params
  end

  def feed
    complaints = Complaint.all
    respond_to do |format|
      format.json {
        render :json => complaints.to_json
      }
      format.xml {
        render :xml => complaints.to_xml
      }
    end
  end
  
  def report
    @complaints_ws = Complaint.find_all_by_complaint_type('WS')
    @complaints_wl = Complaint.find_all_by_complaint_type('WL')
    @complaints_wd = Complaint.find_all_by_complaint_type('WD')
    respond_to do |format|
      format.html
    end
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
    complaint = Complaint.new params[:complaint]
    if complaint.location 
      complaint.latitude = GoogleGeocoder.do_geocode(complaint.location).lat
      complaint.longitude  = GoogleGeocoder.do_geocode(complaint.location).lng
    elsif(complaint.latitude && complaint.longitude)
      loc = GoogleGeocoder.do_reverse_geocode([params["lat"], params["lng"]])
      complaint.location = loc.full_address
    end
    complaint.photo_url = "No Photo Attached" unless complaint.photo_url
    complaint.name = "Anonymous" unless complaint.name
    complaint.reference_id = DateTime.now.to_i.abs
    complaint.status = "Open"
    complaint
  end
end
