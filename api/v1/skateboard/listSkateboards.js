
const mongo = require('../../../data/mongo_mock_db');
const logger = require('../../../logger');

const listSkateboards = (req, res) => {
    let listResults = [];
    logger.info('GET listSkateboards called');
    mongo.collection('skateboards').find({isActive:true}).toArray(function(err, skateboards) {
        listResults = skateboards;
    });
    res.json(listResults);
};

module.exports =  listSkateboards;
