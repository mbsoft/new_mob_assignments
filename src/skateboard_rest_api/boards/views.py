from rest_framework import viewsets
from rest_framework import generics

from .models import SkateBoard
from .serializers import SkateBoardSerializer


class SkateBoardViewSet(viewsets.ModelViewSet):
    """
    Default CRUD Views for SkateBoard Model using basic DRF model view set
    """
    queryset = SkateBoard.objects.all()
    serializer_class = SkateBoardSerializer


class AvailableSkateBoardView(generics.ListAPIView):
    """
    List Only View for available SkateBoard Models
    """
    serializer_class = SkateBoardSerializer

    def get_queryset(self):
        """
        Return queryset that filters and only returns available boards
        :return:
        """
        return SkateBoard.objects.filter(is_available=True)
