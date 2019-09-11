from rest_framework import viewsets

from .models import SkateBoard
from .serializers import SkateBoardSerializer


class SkateBoardViewSet(viewsets.ModelViewSet):
    """
    Default CRUD Views for SkateBoard Model using basic DRF model view set
    """
    queryset = SkateBoard.objects.all()
    serializer_class = SkateBoardSerializer
