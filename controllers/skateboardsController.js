var logger = require('../utils/logger');


exports.sharedList = function(req, res, next) {
  logger.info('GET shared skateboard list available now!' + " API version:" + req.api_version);
  res.send('GET shared skateboard list available now!' + " API version:" + req.api_version);
}

exports.list = function(req, res, next) {
  logger.info('Get list of skateboard owned by me or borrowed by me!!' + " API version:" + req.api_version);
  res.send('Get list of skateboard owned by me or borrowed by me!!' + " API version:" + req.api_version);
}
