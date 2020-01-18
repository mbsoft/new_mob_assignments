const mongo = require('../../../data/mongo_mock_db');

const updateSkateboard = (req, res) => {

    mongo.collection('skateboards').update({_id: req.body._id},{$set: {alias:req.body.alias}},{}, function(err){
        console.log(err);
    });

    res.json(req.body)
};

module.exports =  updateSkateboard;