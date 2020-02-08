const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const proxy = require('express-http-proxy');

const indexRouter = require('./routes/index');
const ordersRouter = require('./routes/orders');

const app = express();
const getServicePath = require('./services/get-service-path');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);

app.use('/orders', ordersRouter);

module.exports = app;
