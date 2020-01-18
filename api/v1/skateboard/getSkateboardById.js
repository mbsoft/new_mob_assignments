
const mongo = require('../../../data/mongo_mock_db');

const {
    name,
    version,
    description
} = require('../../../package.json');

const getSkateboardById = (req, res) => {
    var result = {};

    mongo.collection('skateboards').findOne({_id:req.params.boardId},function(err, skateboard) {
        result = skateboard;
    });

    res.json(result);

};

module.exports =  getSkateboardById;