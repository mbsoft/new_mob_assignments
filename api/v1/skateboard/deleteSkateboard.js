
const mongo = require('../../../data/mongo_mock_db');

const deleteSkateboard = (req, res) => {
    var result = {};

    mongo.collection('skateboards').remove({_id:req.params.boardId},function(err, skateboard) {
            res.json({status: "Success"});
    });

};

module.exports =  deleteSkateboard;