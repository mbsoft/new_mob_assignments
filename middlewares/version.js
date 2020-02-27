var version = function (req, res, next) {
  req.api_version = req.headers['accept-version'] ? req.headers['accept-version'] : "LATEST";
  next()
}

module.exports = version;
