from .base import *

SECRET_KEY = 'c83jpzg11-&cby)65g20yze_6hxz3kw1%1p1czeln&l_x5@jl$'

DEBUG = True
ALLOWED_HOSTS = []

CSRF_COOKIE_SECURE = False
SESSION_COOKIE_SECURE = False

MEDIA_ROOT = BASE_DIR
MEDIA_URL = ''

# Database
# https://docs.djangoproject.com/en/2.1/ref/settings/#databases

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': os.path.join(os.path.dirname(BASE_DIR), 'db.sqlite3'),
    }
}

# needed when running client as another server.
CORS_ORIGIN_ALLOW_ALL = True

FIXTURE_DIRS = [
    os.path.join(BASE_DIR, 'fixtures/localhost')
]
