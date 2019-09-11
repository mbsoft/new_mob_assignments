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

        valid_board_data = [
            {
                'owner': 'Lorem',
                'brand': 'Element',
                'weight': 7.5,
                'location': 'Brighton, MI'
            },
            {
                'owner': 'Lorem',
                'brand': 'Enjoi',
                'weight': 7.6,
                'location': 'Novi, MI'
            },
            {
                'owner': 'Lorem',
                'brand': 'Enjoi',
                'weight': 7.6,
                'location': 'Novi, MI'
            },
        ]

        # Create boards
        self.boards = []
        for board_def in valid_board_data:
            self.boards.append(
                SkateBoard.objects.create(**board_def)
            )

    def test_get_boards_successful(self):
        """
        It returns all boards
        """
        uri = '/boards/'
        response = self.client.get(uri)

        # It returns 200 status code
        self.assertEqual(response.status_code, status.HTTP_200_OK)

        # It return correct amount of boards
        self.assertEqual(len(json.loads(response.content)), 3)

    def test_get_board_successful(self):
        # Create boards
        board = self.boards[0]

        uri = '/boards/{}/'.format(board.id)
        response = self.client.get(uri)

        self.assertEqual(response.status_code, status.HTTP_200_OK)

        # It returns correct payload
        response_payload = json.loads(response.content)
        expected_fields = [
            'id',
            'owner',
            'brand',
            'weight',
            'location',
            'created_at',
            'updated_at',
        ]
        for field_name in expected_fields:
            self.assertTrue(field_name in response_payload)
            # Remove field from payload
            del response_payload[field_name]

        # Make sure there are no unexpected fields left in object
        self.assertEqual(len(response_payload), 0)
