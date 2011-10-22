import os, sys, site

site.addsitedir('/home/cycletel/cycletel/env/lib/python2.7/site-packages')
import django.core.handlers.wsgi

apache_configuration= os.path.dirname(__file__)
project = os.path.dirname(apache_configuration)
workspace = os.path.dirname(project)
sys.path.append(workspace)
sys.path.append('/data')
sys.path.append('/data/sms_simulator')
os.environ['DJANGO_SETTINGS_MODULE'] = 'sms_simulator.settings'
application = django.core.handlers.wsgi.WSGIHandler()