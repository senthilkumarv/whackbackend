xml.instruct! :xml, :version => "1.0" 
xml.rss :version => "2.0" do
  xml.channel do
    xml.title "WaProSol"
    xml.description "Water related complaints"
    xml.link complaints_url

    for complaint in @complaints
      xml.item do
        xml.title complaint.complaint_type
        xml.description complaint.location + " " + complaint.status 
        xml.pubDate complaint.created_at.to_s(:rfc822)
        xml.link complaint_url(complaint)
        xml.guid complaint_url(complaint)
      end
    end
  end
end