import json

from django.test import TestCase
from rest_framework import status
from rest_framework.test import APIClient

from .models import SkateBoard


class BoardsAPITests(TestCase):
    """
    Boards endpoint tests
    """

    def setUp(self):
        self.client = APIClient()

    def test_get_boards_successful(self):
        """
        It returns all boards
        """

        # Create boards
        SkateBoard.objects.create(
            owner='Lorem',
            brand='Element',
            weight=7.5,
            location='Brighton, MI',
        )
        SkateBoard.objects.create(
            owner='Lorem',
            brand='Enjoi',
            weight=7.6,
            location='Novi, MI',
        )
        SkateBoard.objects.create(
            owner='Ipsum',
            brand='Toy Machine',
            weight=8.0,
            location='Los Angeles, CA',
        )

        uri = '/boards/'
        response = self.client.get(uri)

        # It returns 200 status code
        self.assertEqual(response.status_code, status.HTTP_200_OK)

        # It return correct amount of boards
        self.assertEqual(len(json.loads(response.content)), 3)
