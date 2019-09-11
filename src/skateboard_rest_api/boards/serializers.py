from rest_framework import serializers

from .models import SkateBoard


class SkateBoardSerializer(serializers.ModelSerializer):
    """
    Skate Board model serializer. Currently exposes all fields.
    """
    class Meta:
        model = SkateBoard
        fields = [
            'owner',
            'brand',
            'weight',
            'location',
            'created_at',
            'updated_at'
        ]
