import sys

# for creating the mapper code
from sqlalchemy import  Column, ForeignKey, Integer, String, Boolean, Float
# for configuration and class code
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from flask import Flask
from flask_restful import reqparse
from flask_marshmallow import Marshmallow
from sqlalchemy import create_engine


# for creating foreign key relationship between the tables
from sqlalchemy.orm import relationship
app = Flask(__name__)
# for configuration

ma = Marshmallow(app)

# create declarative_base instance
Base = declarative_base()

# Connect to Database and create database session
engine = create_engine('sqlite:///boardgroove.db?check_same_thread=False')
Base.metadata.bind = engine

DBSession = sessionmaker(bind=engine)
session = DBSession()


class BoardSchema(ma.Schema):
    class Meta:
        fields = ('id', 'index', 'guid', 'isActive', 'picture', 'age', 'color', 'alias', 'currentAddress')


board_schema = BoardSchema()
boards_schema = BoardSchema(many=True)

parser = reqparse.RequestParser()
parser.add_argument('id')
parser.add_argument('index')
parser.add_argument('guid')
parser.add_argument('picture')
parser.add_argument('age')
parser.add_argument('color')
parser.add_argument('alias')
parser.add_argument('currentAddress')
parser.add_argument('registered')
parser.add_argument('latitude')
parser.add_argument('longitude')
parser.add_argument('manufacturer')

class Skateboard(Base):
    __tablename__ = 'skateboard'

    id = Column(Integer, primary_key=True)
    index = Column(Integer, nullable=False)
    guid = Column(String(50), nullable=False)
    isActive = Column(Boolean, nullable=False)
    picture = Column(String(128), nullable=True)


    age = Column(Integer, nullable=True)
    color = Column(String(32), nullable=True)
    alias = Column(String(128), nullable=True)
    currentAddress = Column(String(256), nullable=True)
    registered = Column(String(32), nullable=True)
    latitude = Column(Float, nullable=True)
    longitude = Column(Float, nullable=True)
    manufacturer = Column(String(128), nullable=True)

    sqlite_autoincrement=True

# creates a create_engine instance at the bottom of the file
engine = create_engine('sqlite:///boardgroove.db')

Base.metadata.create_all(engine)
