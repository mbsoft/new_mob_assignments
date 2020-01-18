
const mongo = require('../../../data/mongo_mock_db');

const getSkateboardById = (req, res) => {
    let result = {};

    mongo.collection('skateboards').findOne({_id:req.params.boardId},function(err, skateboard) {
        result = skateboard;
    });

    res.json(result);

};

module.exports =  getSkateboardById;
