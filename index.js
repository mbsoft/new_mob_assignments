
const express = require('express');
const SwaggerParser = require('swagger-parser');
const YAML = require('yamljs');
const { connector, summarise } = require('swagger-routes-express');
const MongoMock = require('mongomock');
const api = require('./api');
const seed_data = require('./data/seed_data');
const app = express();

async function run() {
    app.use(express.json({ type: () => true }));
    const apiDefinition = YAML.load('skateboard-api.yml');
    const connect = connector(api, apiDefinition);

    console.log(summarise(apiDefinition));

    connect(app);

    /* istanbul ignore next */
    if (!process.argv.join(' ').includes('mocha')) {
        const server = app.listen(3200, '0.0.0.0', () => {
            console.log('Express started listening on 3200');
        });

    } else {
        console.log('Express skipped binding while running test process');
    }
}

/* istanbul ignore next */
run().catch((error) => {
    console.log('Error starting...');
    process.kill(process.pid, 'SIGINT');
});


module.exports = {
    app
}


