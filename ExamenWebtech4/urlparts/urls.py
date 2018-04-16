from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^(https?:\/\/)?([\da-z\.-]+\.[a-z\.]{2,6}|[\d\.]+)([\/:?=&#]{1}[\da-z\.-]+)*[\/\?]?$', views.index, name='index'),
]
