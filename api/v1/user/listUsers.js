
const mongo = require('../../../data/mongo_mock_db');

const listUsers = (req, res) => {
    let listResults = [];
    mongo.collection('users').find().toArray(function(err, users) {
        listResults = users;
    });
    res.json(listResults);
};

module.exports =  listUsers;
