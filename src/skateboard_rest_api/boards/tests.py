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

        self.expected_fields = [
            'id',
            'owner',
            'brand',
            'weight',
            'location',
            'created_at',
            'updated_at',
        ]

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
                'owner': 'Ipsum',
                'brand': 'Toy Machine',
                'weight': 8.0,
                'location': 'Los Angeles, CA'
            },
        ]

        # Create boards
        self.boards = []
        for board_def in valid_board_data:
            self.boards.append(
                SkateBoard.objects.create(**board_def)
            )

        # Definition to use for creating another board
        self.new_board_def = {
            'owner': 'Ipsum',
            'brand': 'Habitat',
            'weight': 6.5,
            'location': 'Los Angeles, CA'
        }

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
        for field_name in self.expected_fields:
            self.assertTrue(field_name in response_payload)
            # Remove field from payload
            del response_payload[field_name]

        # Make sure there are no unexpected fields left in object
        self.assertEqual(len(response_payload), 0)

    def test_get_board_unsuccessful(self):
        # Get non existent board id
        non_existent_board_id = len(self.boards)

        uri = '/boards/{}/'.format(non_existent_board_id)
        response = self.client.get(uri)

        self.assertEqual(response.status_code, status.HTTP_404_NOT_FOUND)

    def test_create_board_successful(self):
        # Create board
        uri = '/boards/'
        response = self.client.post(uri, data=json.dumps(self.new_board_def), content_type='application/json')

        # It returns 201 status code
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

        # It returns correct payload
        response_payload = json.loads(response.content)
        for field_name in self.expected_fields:
            # Field exists
            self.assertTrue(field_name in response_payload)
            # Value matches POST payload
            if field_name in self.new_board_def:
                self.assertTrue(response_payload[field_name], self.new_board_def[field_name])

            # Remove field from payload
            del response_payload[field_name]

        # Make sure there are no unexpected fields left in object
        self.assertEqual(len(response_payload), 0)

    def test_create_board_unsuccessful(self):
        # Create bad board definition by removing the owner field
        bad_board_def = self.new_board_def.copy()
        del bad_board_def['owner']

        uri = '/boards/'
        response = self.client.post(uri, data=json.dumps(bad_board_def), content_type='application/json')

        # It returns 400 status code
        self.assertEqual(response.status_code, status.HTTP_400_BAD_REQUEST)
