var express = require('express');
var router = express.Router();

var controller = require('../controllers/skateboardController');

/* Create a skateboard instance. */
router.post('/', controller.create);

/* GET a skateboard details. */
router.get('/:skateboardId', controller.get);

/* Update a skateboard instance. */
router.put('/:skateboardId', controller.update);

/* Share one of my skateboard to marketplace. */
router.put('/share/:skateboardId', controller.share);

/* Unshare one of my skateboard to marketplace. */
router.put('/unshare/:skateboardId', controller.unshare);

/* Borrow a skateboard from marketplace  . */
router.put('/borrow/:skateboardId', controller.borrow);


module.exports = router;
