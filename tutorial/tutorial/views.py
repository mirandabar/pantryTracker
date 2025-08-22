#from django.http import HttpResponse
from django.shortcuts import render


def home(request):
    #return HttpResponse("Welcome to the Pantry Tracker!")
    return render(request, 'home.html')

def about(request):
    #return HttpResponse("This is the about page.")
    return render(request, 'about.html')