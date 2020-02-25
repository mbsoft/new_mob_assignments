exports.create = function(req, res, next) {
  res.send('Create a skateboard instance!!' + " API version:" + req.api_version);
}

exports.get = function(req, res, next) {
  res.send('Getting a skateboard details. Id is:' + req.params.skateboardId + " API version:" + req.api_version);
}

exports.update = function(req, res, next) {
  res.send('Update a skateboard instance.' + req.params.skateboardId + " API version:" + req.api_version);
}

exports.share = function(req, res, next) {
  res.send('Share one of my skateboard to marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
}

exports.unshare = function(req, res, next) {
  res.send('Unshare one of my skateboard to marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
}

exports.borrow =  function(req, res, next) {
  res.send('Borrow a skateboard from marketplace.' + req.params.skateboardId + " API version:" + req.api_version);
}
