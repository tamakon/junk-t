from django.urls import path

from screen import views

app_name = 'screen'
urlpatterns = [
    path('', views.index, name='index'),
]
