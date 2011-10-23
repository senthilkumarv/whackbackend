class Complaint < ActiveRecord::Base
  validates_presence_of :mobile, :location, :complaint_type
  
  def turn_around_time

    distance_in_hours   = (((updated_at - created_at).abs) / 3600).round
    distance_in_minutes = ((((updated_at - created_at).abs) % 3600) / 60).round

    difference_in_words = ''

    difference_in_words << "#{distance_in_hours} #{distance_in_hours > 1 ? 'hours' : 'hour' } and " if distance_in_hours > 0
    difference_in_words << "#{distance_in_minutes} #{distance_in_minutes == 1 ? 'minute' : 'minutes' }"
  end
end
