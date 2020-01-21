import unittest

from app import app
from app.models import db, Skateboard

from flask_fixtures import FixturesMixin

# Configure the app with the testing configuration
app.config.from_object('app.config.TestConfig')


# Make sure to inherit from the FixturesMixin class
class TestFoo(unittest.TestCase, FixturesMixin):

    # Specify the fixtures file(s) you want to load.
    # Change the list below to ['authors.yaml'] if you created your fixtures
    # file using YAML instead of JSON.
    fixtures = ['skateboards.json']

    # Specify the Flask app and db we want to use for this set of tests
    app = app
    db = db

    # Your tests go here

    def test_skateboards(self):
        skateboards = Skateboard.query.all()
        assert len(skateboards) == Skateboard.query.count() == 1
