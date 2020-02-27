var express = require('express');
var router = express.Router();

var controller = require('../controllers/skateboardsController');

/* GET shared skateboard list available now. */
router.get('/shared/list', controller.sharedList);

/* Get list of skateboards. Including owned by me and borrowed. */
router.get('/list', controller.list);

module.exports = router;
