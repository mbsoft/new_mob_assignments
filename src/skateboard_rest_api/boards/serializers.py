from rest_framework import serializers

from .models import SkateBoard


class SkateBoardSerializer(serializers.ModelSerializer):
    """
    Skate Board model serializer. Currently exposes all fields.
    """
    class Meta:
        model = SkateBoard
        fields = [
            'id',
            'owner',
            'brand',
            'weight',
            'location',
            'is_available',
            'created_at',
            'updated_at'
        ]
