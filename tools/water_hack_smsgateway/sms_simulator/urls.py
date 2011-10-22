from django.conf.urls.defaults import *
from django.views.generic.simple import direct_to_template

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('',
    # Example:
    (r'^simulator/send_sms$', 'simulator.views.send_sms'),
    (r'^simulator/send_sms_to_user$', direct_to_template, {'template': 'send_sms_to_user.html'}),
    (r'^simulator/receive_sms_from_user$',  direct_to_template, {'template': 'receive_sms_from_user.html'}),
    (r'^simulator/receive_sms$', 'simulator.views.receive_sms'),
    (r'^simulator/pull_sms$', 'simulator.views.pull_sms'),
    (r'^simulator/static/(?P<path>.*)$', 'django.views.static.serve', {'document_root': 'static/'}),

    # Uncomment the next line to enable the admin:
    # (r'^admin/', include(admin.site.urls)),
)
