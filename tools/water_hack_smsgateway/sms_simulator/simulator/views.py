from django.http import HttpRequest, HttpResponse, HttpResponseRedirect
from django.utils.encoding import smart_str
import commands
import settings
import re

def receive_sms(request):
    user_phone_number = request.GET.get('user_phone_number', '')
    app_phone_number = request.GET.get('app_phone_number', '')
    message = request.GET.get('message', '')
    cmd = '(sleep .5;echo ' + user_phone_number + ' ' + app_phone_number + ' text ' + message + ';sleep .5;) | telnet ' + settings.SERVER_NAME + ' 10000'
    code, response = commands.getstatusoutput(smart_str(cmd))
    message = _format_alert_messages(response)
    return HttpResponse(content=message, content_type="text/plain")

def pull_sms(request):
    cmd = '(sleep .10;echo 6666 7777 text ;sleep .10;) | telnet ' + settings.SERVER_NAME + ' 10000'
    code, response = commands.getstatusoutput(smart_str(cmd))
    message = _format_alert_messages(response)
    return HttpResponse(content=message, content_type="text/plain")

def _format_alert_messages(response):
    resp_str = ''
    separator = ' : '
    line_separator = '<br/>'
    for resp_ln in response.split("\n"):
        print resp_ln
        match = re.search(r'([0-9]+\s+)text (.*)', resp_ln)
        if match:
            resp_str = line_separator.join([resp_str, separator.join([match.group(1), match.group(2)])])
    return resp_str

