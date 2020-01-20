
const mongo = require('../../../data/mongo_mock_db');
const logger = require('../../../logger');

const deleteSkateboard = (req, res) => {
    logger.info('DELETE deleteSkateboard called');
    mongo.collection('skateboards').remove({_id:req.params.boardId},function(err, skateboard) {
            res.json({status: "Success"});
    });

};

module.exports =  deleteSkateboard;
