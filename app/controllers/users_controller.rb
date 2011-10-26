class UsersController < ApplicationController
  include RedirectHelper
  
  def new
    @user = User.new
  end

  def create
    @user = User.new(params[:user])
    if @user.save
      redirect_to_login
    else
      render "new"
    end
  end
end

