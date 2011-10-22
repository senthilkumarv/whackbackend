import os, sys, site

site.addsitedir('/data/env/lib/python2.7/site-packages')
import django.core.handlers.wsgi

apache_configuration= os.path.dirname(__file__)
project = os.path.dirname(apache_configuration)
workspace = os.path.dirname(project)
sys.path.append(workspace)
sys.path.append('/data')
sys.path.append('/data/sms_simulator')
sys.path.append('/data/sms_simulator/current')
sys.path.append('/data/sms_simulator/current/simulator')
os.environ['DJANGO_SETTINGS_MODULE'] = 'current.settings'
application = django.core.handlers.wsgi.WSGIHandler()
