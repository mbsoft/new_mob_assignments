exports.sharedList = function(req, res, next) {
  res.send('GET shared skateboard list available now!' + " API version:" + req.api_version);
}

exports.list = function(req, res, next) {
  res.send('Get list of skateboard owned by me or borrowed by me!' + " API version:" + req.api_version);
}
