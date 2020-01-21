# using flask_restful 
from flask import Flask, jsonify, request
from flask_restful import Resource, Api, reqparse

from database_setup import Skateboard, board_schema, boards_schema, session, parser

# creating the flask app 
app = Flask(__name__)

# creating an API object 
api = Api(app)



class Ping(Resource):
    def get(self):
        return jsonify({'message': 'hello world'})


class Board(Resource):
    def get(self):
        boards = session.query(Skateboard).all()
        results = boards_schema.dump(boards)
        return jsonify(results)

    def post(self):
        args = parser.parse_args()
        newboard = Skateboard(id=int(args['id']), index=args['index'], guid=args['guid'], isActive=True, picture=args['picture'],
                              age=int(args['age']), color=args['color'], alias=args['alias'],
                              currentAddress=args['currentAddress'])
        session.add(newboard)
        session.commit()
        return jsonify({'status': 'success'})


class BoardById(Resource):
    def get(self, id):
        board = session.query(Skateboard).filter_by(id=id).one()
        result = board_schema.dump(board)
        return jsonify(result)

    def delete(self, id):
        board = session.query(Skateboard).filter_by(id=id).one()
        session.delete(board)
        session.commit()
        return jsonify({'status': 'success'})


api.add_resource(Ping, '/')
api.add_resource(Board, '/api/v1/skateboard/')
api.add_resource(BoardById, '/api/v1/skateboard/<int:id>')

# driver function
if __name__ == '__main__':
    app.run(debug=True)
