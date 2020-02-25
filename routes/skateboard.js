var express = require('express');
var router = express.Router();

/* Create a skateboard instance. */
router.post('/', function(req, res, next) {
  res.send('Create a skateboard instance.');
});

/* GET a skateboard details. */
router.get('/:skateboardId', function(req, res, next) {
  res.send('Getting a skateboard details.' + req.params.skateboardId);
});

/* Update a skateboard instance. */
router.put('/:skateboardId', function(req, res, next) {
  res.send('Update a skateboard instance.' + req.params.skateboardId);
});

/* Share one of my skateboard to marketplace. */
router.put('/share/:skateboardId', function(req, res, next) {
  res.send('Share one of my skateboard to marketplace.' + req.params.skateboardId);
});

/* Unshare one of my skateboard to marketplace. */
router.put('/unshare/:skateboardId', function(req, res, next) {
  res.send('Unshare one of my skateboard to marketplace.' + req.params.skateboardId);
});

/* Borrow a skateboard from marketplace  . */
router.put('/borrow/:skateboardId', function(req, res, next) {
  res.send('Borrow a skateboard from marketplace.' + req.params.skateboardId);
});


module.exports = router;
