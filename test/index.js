

const chai = require('chai');
const chaiHttp = require('chai-http');
const request = require('supertest');
const app = require('../index').app;
const should = chai.should();

chai.use(chaiHttp);

describe('GET all skateboards', () => {
   before(() => {

   });

   it('should return all skateboard records', (done) => {
       request(app)
           .get('/api/v1/skateboard')
           .end((err, res) => {
               res.should.have.status(200);
               res.body.should.be.a('array');
               done();
           });

   });
   after(() => {

   });
});

describe('GET single skateboard', () => {
   before(() => {

   });

   it('should return a single skateboard record', (done) => {
       request(app)
           .get('/api/v1/skateboard/5e2202afabe5d89b7ee1b295')
           .end((err, res) => {
               res.should.have.status(200);
               done();
           });
   });

   after(() => {

   });

});

describe('DELETE single skateboard', () => {
    before(() => {

    });

    it('should delete a single skateboard record', (done) => {
        request(app)
            .delete('/api/v1/skateboard/5e2202afabe5d89b7ee1b295')
            .end((err, res) => {
                res.should.have.status(200);
                res.body.status.should.eq('Success');
                done();
            });
    });

    after(() => {

    });

});

describe('POST new skateboard', () => {
    before(() => {

    });

    it('should add a new skateboard record', (done) => {
        request(app)
            .post('/api/v1/skateboard/')
            .send('{\n' +
                '        "_id": "5e2202e24c057d99fe697b05",\n' +
                '        "index": 0,\n' +
                '        "guid": "0bdfd444-7419-4236-a17c-281fb89e8bf4",\n' +
                '        "isActive": true,\n' +
                '        "picture": "http://placehold.it/32x32",\n' +
                '        "age": 1,\n' +
                '        "color": "brown",\n' +
                '        "alias": "CODACT3",\n' +
                '        "currentAddress": "446 Coles Street, Nipinnawasee, North Carolina, 951",\n' +
                '        "registered": "2019-05-04T10:09:23 +04:00",\n' +
                '        "latitude": 40.1434,\n' +
                '        "longitude": -83.2225,\n' +
                '        "tags": [\n' +
                '            "minim",\n' +
                '            "sunt",\n' +
                '            "incididunt",\n' +
                '            "deserunt",\n' +
                '            "nisi",\n' +
                '            "voluptate",\n' +
                '            "consectetur"\n' +
                '        ],\n' +
                '        "manufacturer": "KPC"\n' +
                '    }\n' +
                '    ')
            .end((err, res) => {
                res.should.have.status(200);
                done();
            });
    });

    after(() => {

    });

});

describe('UPDATE a skateboard', () => {
    before(() => {

    });

    it('should update a skateboard record', (done) => {
        request(app)
            .put('/api/v1/skateboard/')
            .send('{\n' +
                '        "_id": "5e2202e24c057d99fe697b05",\n' +
                '        "index": 0,\n' +
                '        "guid": "0bdfd444-7419-4236-a17c-281fb89e8bf4",\n' +
                '        "isActive": true,\n' +
                '        "picture": "http://placehold.it/32x32",\n' +
                '        "age": 1,\n' +
                '        "color": "brown",\n' +
                '        "alias": "CODACT4",\n' +
                '        "currentAddress": "446 Coles Street, Nipinnawasee, North Carolina, 951",\n' +
                '        "registered": "2019-05-04T10:09:23 +04:00",\n' +
                '        "latitude": 40.1434,\n' +
                '        "longitude": -83.2225,\n' +
                '        "tags": [\n' +
                '            "minim",\n' +
                '            "sunt",\n' +
                '            "incididunt",\n' +
                '            "deserunt",\n' +
                '            "nisi",\n' +
                '            "voluptate",\n' +
                '            "consectetur"\n' +
                '        ],\n' +
                '        "manufacturer": "KPC"\n' +
                '    }\n' +
                '    ')
            .end((err, res) => {
                res.should.have.status(200);
                res.body._id.should.eq('5e2202e24c057d99fe697b05');
                done();
            });
    });

    after(() => {

    });

});

describe('GET all users', () => {
    before(() => {

    });

    it('should return all user records', (done) => {
        request(app)
            .get('/api/v1/user')
            .end((err, res) => {
                res.should.have.status(200);
                res.body.should.be.a('array');
                done();
            });

    });
    after(() => {

    });
});

describe('GET api versions', () => {
    before(() => {

    });

    it('should return api version details', (done) => {
        request(app)
            .get('/')
            .end((err, res) => {
                res.should.have.status(200);
                done();
            });

    });
    after(() => {

    });
});

describe('GET ping status', () => {
    before(() => {

    });

    it('should return service status details', (done) => {
        request(app)
            .get('/ping')
            .end((err, res) => {
                res.should.have.status(200);
                done();
            });

    });
    after(() => {

    });
});
