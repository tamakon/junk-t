from .base import *

SECRET_KEY = os.environ("SECRET_KEY")

DEBUG = False
ALLOWED_HOSTS = ["tbd.junk-t.com"]

CSRF_COOKIE_SECURE = True
SESSION_COOKIE_SECURE = True

MEDIA_ROOT = os.path.expanduser("~/resource/")
MEDIA_URL = ''
