from .base import *

SECRET_KEY = os.environ("SECRET_KEY")

DEBUG = False
ALLOWED_HOSTS = ["tbd.junk-t.com"]

CSRF_COOKIE_SECURE = True
SESSION_COOKIE_SECURE = True

MEDIA_ROOT = os.path.expanduser("~/resource/")
MEDIA_URL = ''

# TODO 実際はMySQLを使う
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': os.path.join(os.path.expanduser("~/"), 'db.sqlite3'),
    }
}

FIXTURE_DIR = [
    os.path.join(BASE_DIR, 'fixtures/production')
]
