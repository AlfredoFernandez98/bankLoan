1. The API
Build the API with the techstack and architecture we have used so far. This means that you should use Javalin, JPA, DTOs, Java streams, and optionally fetching data from other REST API’s.
Make sure that you have a good selection of endpoints. You should have at least 4 unique endpoints per team member - and all CRUD methods should be implemented.
The endpoints should be secured with JWT tokens. Decide which roles you want to have in your API. Like USER, ADMIN etc.
Make sure that you implement good error handling in your API, and that the API returns the correct status codes and error messages in json format.
Create an http file with manual tests.
2. Documenting the API
Caddy Logo

At the beginning of the project, you should decide on the endpoints you would like to have in your API (before you start coding). Use this basic method described in the toolbox: API documentation. Fill out the Word Document and hand it in on Moodle together with a brief description of what the API is about. Some sort of a vision for the API. The teachers will review the documentation and give you feedback.

The finished API should show the Javalin built in route overview on the /api/routes endpoint. Make it public so that we can see it.

3. Testing the API (and DAOs)
You should write enough tests to cover the important functionality of the backend.

Write tests for the DAO and Service Layer. Use JUnit, Hamcrest, and test containers. You don’t need to test all methods in the Service Layer, and certainly not in the DTOs and Entities. But you should test the methods that are directly used in the endpoints.

Write tests for the endpoints. Use JUnit, Hamcrest, Rest Assured, and test containers. You should also test the security of the endpoints, and not only happy path cases. Also test the error handling in the API. Could be for unauthorized access, not found, bad request etc.

4. Deploying the API
You should deploy the API to a Digital Ocean Droplet.

You should use the deployment pipeline we have introduced. So GitHub Actions, Docker Hub, Docker Compose files, and Watchtower to keep the API Container up to date. You should also use Caddy to serve the API over HTTPS on a sub-domain.
