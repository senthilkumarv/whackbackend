class ComplaintController < ApplicationController
  def index
    @complaints = Complaint.all
  end

  def create
  end

end
