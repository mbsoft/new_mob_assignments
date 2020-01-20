const {
    name,
    version,
    description
} = require('../package.json')

const versions = (req, res) => {
    res.json([
            {
                version: 1.0,
                path: '/api/v1'
            },
            {
                version: 2.0,
                path: '/api/v2'
            }
        ]
    );
};


module.exports = versions;