"""
Django settings for skateboard_rest_api project production environment.
"""

from . base import *

# Don't run with debug turned on in production!
DEBUG = False

# Set allowed hostname
ALLOWED_HOSTS = ['api.skateboards.clarq.dev']

# Load secret key from environment
SECRET_KEY = os.environ.get('APP_SECRET')

# Production environment loads database details from environment
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql',
        'NAME': os.environ.get('DB_NAME'),
        'USER': os.environ.get('DB_USER'),
        'HOST': os.environ.get('DB_HOST'),
        'PORT': os.environ.get('DB_PORT'),
    }
}
