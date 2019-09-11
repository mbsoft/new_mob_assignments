"""skateboard_rest_api URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.urls import include, path
from rest_framework import routers

from .boards.views import AvailableSkateBoardView, SkateBoardViewSet

# Create router
router = routers.DefaultRouter()
# Register boards endpoint with the router
router.register(r'boards', SkateBoardViewSet)

# Include urls created by router
urlpatterns = [
    # Register available boards endpoint with the router
    path(r'boards/available/', AvailableSkateBoardView.as_view()),
    # Include the rest of the routes
    path(r'', include(router.urls)),
]
