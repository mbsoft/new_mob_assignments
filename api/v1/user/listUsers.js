
const mongo = require('../../../data/mongo_mock_db');
const logger = require('../../../logger');

const listUsers = (req, res) => {
    let listResults = [];
    logger.info('GET listSkateboards called');
    mongo.collection('users').find().toArray(function(err, users) {
        listResults = users;
    });
    res.json(listResults);
};

module.exports =  listUsers;
