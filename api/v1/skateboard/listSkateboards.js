
const mongo = require('../../../data/mongo_mock_db');

const listSkateboards = (req, res) => {
    let listResults = [];
    mongo.collection('skateboards').find({isActive:true}).toArray(function(err, skateboards) {
        listResults = skateboards;
    });
    res.json(listResults);
};

module.exports =  listSkateboards;
