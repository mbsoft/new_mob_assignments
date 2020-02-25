var express = require('express');
var router = express.Router();

/* Create a skateboard instance. */
router.post('/', function(req, res, next) {
  res.send('Create a skateboard instance.' + " API version:" + req.api_version);
});

/* GET a skateboard details. */
router.get('/:skateboardId', function(req, res, next) {
  res.send('Getting a skateboard details.' + req.params.skateboardId + " API version:" + req.api_version);
});

/* Update a skateboard instance. */
router.put('/:skateboardId', function(req, res, next) {
  res.send('Update a skateboard instance.' + req.params.skateboardId + " API version:" + req.api_version);
});

/* Share one of my skateboard to marketplace. */
router.put('/share/:skateboardId', function(req, res, next) {
  res.send('Share one of my skateboard to marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
});

/* Unshare one of my skateboard to marketplace. */
router.put('/unshare/:skateboardId', function(req, res, next) {
  res.send('Unshare one of my skateboard to marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
});

/* Borrow a skateboard from marketplace  . */
router.put('/borrow/:skateboardId', function(req, res, next) {
  res.send('Borrow a skateboard from marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
});


module.exports = router;
