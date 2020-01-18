
const mongo = require('../../../data/mongo_mock_db');

const {
    name,
    version,
    description
} = require('../../../package.json');

const listSkateboards = (req, res) => {
    var listResults = [];
    mongo.collection('skateboards').find({isActive:true}).toArray(function(err, skateboards) {
        listResults = skateboards;
    });
    res.json(listResults);
};

module.exports =  listSkateboards;