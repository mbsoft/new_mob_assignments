from rest_framework import generics, status, viewsets
from rest_framework.decorators import action
from rest_framework.response import Response

from .models import SkateBoard
from .serializers import AvailabilitySerializer, SkateBoardSerializer


class SkateBoardViewSet(viewsets.ModelViewSet):
    """
    Default CRUD Views for SkateBoard Model using basic DRF model view set
    """
    queryset = SkateBoard.objects.all()
    serializer_class = SkateBoardSerializer

    @action(detail=True, url_path='available', methods=['PUT'], serializer_class=AvailabilitySerializer)
    def set_available(self, request, pk=None):
        board = self.get_object()
        serializer = AvailabilitySerializer(data=request.data)
        if serializer.is_valid():
            board.is_available = serializer.data['is_available']
            board.save()
            # Trigger any background work needed, for example send notification to
            # users who are waiting for this board to become available
            return Response(AvailabilitySerializer(board).data)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



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
