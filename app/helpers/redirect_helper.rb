module RedirectHelper
  def redirect_to_complaints
    redirect_to :action => 'index', :controller => 'complaints'
  end

  def redirect_to_login
    flash[:message] = "You have signed up. Login with your username and password"
    redirect_to :action => 'new', :controller => 'sessions'
  end
end
