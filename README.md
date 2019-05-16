### Skateboard REST API

In the shared mobility space, there are a number of different types of shared assets - cars, scooters, ebikes. For this project, we want to create a starting point for an API that will allow individual skateboarders to share their boards. 

The goal of this project is to assess development skills suitable for a backend developer position by testing knowledge and proficiency with software design, unit testing and web technologies like HTTP and REST.

- **Project Name:** Skateboard REST API
- **Project Goal:** Create/define an API that will help skateboarder to share their 'boards'
- **Technology:** Pick any language, web framework and testing framework you like
- **Deliverables:**  Submit a pull request on https://mbsoft.github.com/new_mob_assignments that includes your complete solution

**Description: (Note: these stories are intentionally somewhat vague)**

As a skateboard owner I want to be able to add my individual board to a skateboard sharing marketplace.

As a skateboard owner I want to be able to indicate that my board is available or unavailable for sharing

As a skateboard owner I want to be able to modify the details for the board that I share.

As a skateboard borrower, I want to see a list of available boards for rent

**Task:** Build the REST API and background photo resizing described above. Write unit tests for each component.

**Requirements:**

1. Design your API in a RESTful way and respond with JSON.
1. Make sure your code has tests.
Write the code and design your system to be as realistic and production-ready as possible. Follow best-practices and focus on quality.
1. A skateboard might have the following attributes: name of owner, brand, weight, length, location, timestamp and any other attribution you deem necessary
1. Add endpoints that support your solution 

some examples:
 
  * **Create board:**

    - Request: attributes for a board including the photo as a [HTTP multipart/form-data upload](http://stackoverflow.com/questions/4238809/example-of-multipart-form-data).
    - Should parse input including file upload, save to a 'database', enqueue background job and send response.
    - To keep things simple, no authentication needs to be performed. 

  * **Get all boards:**

    - Should return all boards in the system ordered by date (newest first).
  
  * **Get all boards:**

1. Describe your solution in a README and how to run it. Ensure that you clearly state assumptions that you made.


**Guidelines**

Please commit early and often and with good commit messages. This will allow us to see how you've approached the problem. Don't worry about changing things around often.

Please ask any questions if something is unclear

**What is this?**

This repo contains the job assignment for potential Backend engineers in DENSO's New Mobility team.