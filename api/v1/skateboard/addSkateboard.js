const mongo = require('../../../data/mongo_mock_db');
const logger = require('../../../logger');

const addSkateboard = (req, res) => {

    logger.info('POST addSkateboard called');
    mongo.collection('skateboards').insert(req.body,function(err){
        logger.info(err);
    });

    res.json(req.body)
};

module.exports =  addSkateboard;
