var logger = require('../utils/logger');
const config = require('config');

exports.create = function(req, res, next) {

  res.send('Create a skateboard instance!!' + " API version:" + req.api_version);
}

exports.get = function(req, res, next) {
  var skateboards = config.get('skateboards');

  if(skateboards.hasOwnProperty(req.params.skateboardId)){
    var respData = {};
    respData.api_version = req.api_version;
    respData.data = skateboards[req.params.skateboardId];
    res.send(respData);
  }else{
    res.status(404)        // HTTP status 404: NotFound
       .send('Skateboard not found. Id:' + req.params.skateboardId);
    logger.warn('Skateboard not found. Id:' + req.params.skateboardId);
  }

}

exports.update = function(req, res, next) {
  logger.warn("Update a skateboard instance.");
  res.send('Update a skateboard instance.' + req.params.skateboardId + " API version:" + req.api_version);
}

exports.share = function(req, res, next) {
  logger.warn("Share one of my skateboard to marketplace");
  res.send('Share one of my skateboard to marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
}

exports.unshare = function(req, res, next) {
  logger.warn("Unshare one of my skateboard to marketplace");
  res.send('Unshare one of my skateboard to marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
}

exports.borrow =  function(req, res, next) {
  res.send('Borrow a skateboard from marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
}
