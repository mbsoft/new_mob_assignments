var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var httpLogger = require('morgan');

var indexRouter = require('./routes/index');
var skateboardRouter = require('./routes/skateboard');
var skateboardsRouter = require('./routes/skateboards');
var versionControl = require('./middlewares/version');

var logger = require('./utils/logger');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(httpLogger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(versionControl);
app.use('/', indexRouter);
app.use('/skateboard', skateboardRouter);
app.use('/skateboards', skateboardsRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

logger.warn("Sever start up!");

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // log console.error();
  logger.error(err);
  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
