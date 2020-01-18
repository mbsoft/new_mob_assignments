const mongo = require('../../../data/mongo_mock_db');

const addSkateboard = (req, res) => {

    mongo.collection('skateboards').insert(req.body,function(err){
        console.log(err);
    });

    res.json(req.body)
};

module.exports =  addSkateboard;