from django.conf.urls import url
from django.urls import include
from rest_framework import routers

from api import views

app_name = 'api'
router = routers.DefaultRouter()
router.register('images', views.ImageViewSet)
urlpatterns = [
    url('', include(router.urls)),
    url(r'resource/images/(?P<tag>.+)$', views.image_content)
]
