var express = require('express');
var router = express.Router();


/* GET shared skateboard list available now. */
router.get('/shared/list', function(req, res, next) {
  res.send('GET shared skateboard list available now.');
});

/* Get list of skateboards. Including owned by me and borrowed. */
router.get('/list', function(req, res, next) {
  res.send('Get list of skateboard owned by me or borrowed by me.');
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

/* GET a skateboard details. */
router.get('/:skateboardId', function(req, res, next) {
  res.send('Getting a skateboard details.' + req.params.skateboardId);
});

/* Create a skateboard instance. */
router.post('/', function(req, res, next) {
  res.send('Create a skateboard instance.');
});

/* Update a skateboard instance. */
router.put('/:skateboardId', function(req, res, next) {
  res.send('Update a skateboard instance.' + req.params.skateboardId);
});


module.exports = router;
