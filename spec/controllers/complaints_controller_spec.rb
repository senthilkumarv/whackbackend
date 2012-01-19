require 'spec_helper'

describe ComplaintsController do
  context "create" do
    it "create a complaint with enough details" do
      post :create, :complaint => {:location => "Koramangala", :complaint_type => "WL", :mobile => "1234567890"}, :format => :json
      response.should be_success
      body = JSON.parse response.body
      body["message"].should == ComplaintsController::SUCCESSFULLY_REGISTERED
      body["status"].should == Complaint::OPEN
    end

    it "should ignore create requests from html" do
      post :create, :complaint => {:location => "Koramangala", :complaint_type => "WL", :mobile => "1234567890"}
      response.should_not be_success
    end
  end

  context "status" do
    it "should get the correct complaint" do
      @complaint = Complaint.new({
                                   :location => "Koramangala",
                                   :complaint_type => "WL",
                                   :mobile => "1234567890",
                                   :reference_id => "45656",
                                   :status => Complaint::OPEN
                                 })
      Complaint.stub!(:find_by_reference_id).and_return @complaint
      get :show, :id => "45656", :format => :json
      response.should be_success
      body = JSON.parse response.body
      body["status"].should == Complaint::OPEN
    end

    it "should send 404 for non existent complaint" do
      @complaint = Complaint.new({
                                   :reference_id => "45656",
                                   :status => Complaint::OPEN
                                 })
      Complaint.stub!(:find_by_reference_id).and_return nil
      get :show, :id => "45656", :format => :json
      response.should_not be_success
      response.status.should == 404
    end
end
end
