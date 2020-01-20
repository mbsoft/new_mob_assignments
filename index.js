
const express = require('express');
const YAML = require('yamljs');
const { connector, summarise } = require('swagger-routes-express');
const MongoMock = require('mongomock');
const api = require('./api');
const seed_data = require('./data/seed_data');
const logger = require('./logger');
const app = express();

async function run() {
    app.use(express.json({ type: () => true }));
    const apiDefinition = YAML.load('api/swagger/swagger.yaml');
    const connect = connector(api, apiDefinition);

    console.log(summarise(apiDefinition));

    connect(app);

    /* istanbul ignore next */
    if (!process.argv.join(' ').includes('mocha')) {
        const server = app.listen(3200, '0.0.0.0', () => {
            logger.info('Express started listening on 3200');
        });

    } else {
        logger.info('Express skipped binding while running test process');
    }
}

/* istanbul ignore next */
run().catch((error) => {
    logger.info('Error starting...');
    process.kill(process.pid, 'SIGINT');
});


module.exports = {
    app
}


