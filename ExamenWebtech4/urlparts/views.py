# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
import re
from django.http import HttpResponse

def index(request):
    test = request.path
    test = test.replace("urlparts", "----")
    test = test.replace("/", "----")
    return HttpResponse(test)
