const MongoMock = require('mongomock');
const seed_data = require('./seed_data');

const mongo = new MongoMock(seed_data);

module.exports = mongo;