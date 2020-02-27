const supertest = require('supertest');
const controller = require('../../controllers/skateboardController');
const app = require('../../app');

describe("Testing the skateboard API", () => {

	it("Tests the base route and returns true for status", async () => {

		const response = await supertest(app).get('/skateboard/1');
		expect(response.status).toBe(200);
		expect(response.body.api_version).toBe("LATEST");
    expect(response.body.data.id).toBe(2);
	});
});
