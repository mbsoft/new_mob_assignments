from flask_sqlalchemy import SQLAlchemy

from app import app

db = SQLAlchemy(app)

class Skateboard(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    first_name = db.Column(db.String(30))
    last_name = db.Column(db.String(30))