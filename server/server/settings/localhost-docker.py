from .localhost import *

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'junktion',
        'USER': 'junk-t',
        'PASSWORD': 'junk-t-pass',
        'HOST': 'mysql',
        'PORT': '3306'
    }
}