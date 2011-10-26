class SessionsController < ApplicationController
  include RedirectHelper
  
  def new
    if current_user
      redirect_to "/complaint/index"
    end
  end

  def create
    user = User.authenticate(params[:email], params[:password])
    if user
      session[:user_id] = user.id
      redirect_to_complaints
    else
      flash.now.alert = "Invalid email or password"
      render "new"
    end
  end

  def destroy
    session[:user_id] = nil
    redirect_to "/login", :notice => "Logged out!"
  end
end
