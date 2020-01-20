const ping = require('./ping');
const versions = require('./versions');
const v1_skateboard_listSkateboards = require('./v1/skateboard/listSkateboards');
const v1_skateboard_addSkateboard = require('./v1/skateboard/addSkateboard');
const v1_skateboard_getSkateboardById = require('./v1/skateboard/getSkateboardById');
const v1_skateboard_deleteSkateboard = require('./v1/skateboard/deleteSkateboard');
const v1_skateboard_updateSkateboard = require('./v1/skateboard/updateSkateboard');
const v1_user_listUsers = require('./v1/user/listUsers');
const { name, version, description } = require('../package.json')



module.exports = { ping, versions, v1_skateboard_listSkateboards, v1_skateboard_addSkateboard, v1_skateboard_getSkateboardById,
    v1_skateboard_deleteSkateboard, v1_skateboard_updateSkateboard, v1_user_listUsers}
