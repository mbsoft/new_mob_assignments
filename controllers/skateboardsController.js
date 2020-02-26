var logger = require('../utils/logger');
const config = require('config');
var skateboards = config.get('skateboards');

exports.sharedList = function(req, res, next) {
  logger.info("Get list of shared skateboard in marketplace.");
  var respData = {};
  respData.api_version = req.api_version;
  respData.data = skateboards;
  res.send(respData);

}

exports.list = function(req, res, next) {
  logger.info("Get list of skateboard belongs to current users.");
  var respData = {};
  respData.api_version = req.api_version;
  respData.data = skateboards;
  res.send(respData);
}
