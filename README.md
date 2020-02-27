### Skateboard REST API

In the shared mobility space, there are a number of different types of shared assets - cars, scooters, ebikes. For this project, we want to create a starting point for an API that will allow individual skateboarders to share their boards. 

The goal of this project is to assess development skills suitable for a backend developer position by testing knowledge and proficiency with software design, unit testing and web technologies like HTTP and REST.

- **Project Name:** Skateboard REST API
- **Project Goal:** Create/define an API that will help skateboarders to share their 'boards'
- **Technology:** Pick any language, framework and testing framework you like
- **Deliverables:**  Fork the project into your own account. Submit a pull request on https://github.com/mbsoft/new_mob_assignments that includes your complete solution

**Description: (Note: these stories are intentionally somewhat vague)**

As a skateboard owner I want to be able to add my individual board to a skateboard sharing marketplace.

As a skateboard owner I want to be able to indicate that my board is available or unavailable for sharing

As a skateboard owner I want to be able to modify the details for the board that I share.

As a skateboard borrower, I want to see a list of available boards

**Task:** Build the REST API that might support the stories described above. Write unit tests for each component.

**Requirements:**

1. Design your API in a RESTful way and respond with JSON.
1. Make sure your code has tests.
1. Consider some form of logging
1. Write the code and design your system to be as realistic and production-ready as possible. Follow best-practices and focus on quality.
1. A skateboard might have the following attributes: name of owner, brand, weight, length, location, timestamp and any other attribution you deem necessary
1. Add endpoints that support your solution 
1. Try to keep track of the time spent on the project as this might be discussed
1. Bonus points for adding any kind of front-end that can be used to visualize available boards through use of the designed APIs
some examples:
 
  * **Create skateboard:**

    - attributes for a board might include the photo, dimensions, specs etc.
    - To keep things simple, no authentication needs to be performed. 

  * **Get skateboards**
    - perhaps based on status, spec filter etc.
    
  * **Update skateboard**
    - change status, add additional details/specs etc.
  
  * **Delete skateboard**
  
  
 ....

1. Describe your solution in a README and how to run it. Ensure that you clearly state assumptions that you made.


**Guidelines**

Please commit early and often and with good commit messages. This will allow us to see how you've approached the problem. Don't worry about changing things around often.

Be prepared to discuss some of your approaches and design decisions.
 
Please ask any questions if something is unclear

**What is this?**

This repo contains the job assignment for potential Backend engineers in DENSO's New Mobility team.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------Skateboard Sharing Implementation--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------[  By : Muthanna S. Al-Jelihawi ]--------------------------------------------------------------------------------------------------
---------------------------------------------[  ON : Wednesday 02- 26- 2020  ]--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Design 
-------
The system is designed into two seprate solutions. 
    The first project is a web-service-api using .netcore 3.1, named  Skateboard.REST.API
    The second project is a MVC .netcore 3.1 web application. named SkateboardSharingApp

Due to the lack of time and scope of this challenge assignment no database creation or design was implemented. instead a dynamic in memory list was esatblished

Skateboard.REST.API
===================
This was designed using Microsoft .netcore 3.1 technology which will enable the service to run on different platforms like windows or linux
The service support 2 different controller one for the owner and another for the borrower. normally i would have craeted 2 different services one for each and 
they would each communicate with a database using some kind of messaging bus.
The service has endpoints that a client apps(like mobile app or websites or other services) is able to call and communicate with by json messages
The service uses depednacy injection to load specific services at run time to be able to logg information and erros as well to communicat with a static singleton dynamic lits.

SkateboardSharingApp
====================
This was designed using Microsoft MVC .netcore 3.1 Web application technology which will enable the service to run on different platforms like windows or linux
The service support 2 different controller one for the owner(Skateboardscontroller) and another for the borrower(Borrowercontroller). 

The Skateboardscontroller is used for skateboard owners; here they can do CRUD operations (like Edit, delete,active/inactive skateboard and create new records)
the project does not have any database hooked/designed for it, but it uses restfull calls to the Skateboard.REST.API project to get/save data.

The Borrowercontroller is used only by users of type borrower, here they can view skateborads and book them. once again the controller uses restfull calls to the Skateboard.REST.API project to get/save data.
the borrower can only see skateboards that are active.

The mvc application uses depednacy injection to load specific services at run time to be able to logg information and erros and to communicat with the skateboard.rest.api

How to Operate
==============
Inorder to get the total solution to run we will need to follow these steps:
1- Build and run the Skateboard.REST.API
2- Please notice that the service will have the following url http://localhost:33968 if different copy it and keep it for next step
3- If the url from step 2 is different, we will need to open the file "CallAPIService.cs" and navigate to line 306 update the url there.
4- If the url from step 2 is different, we will need to open the file "CallAPIService.cs" and navigate to line 373 update the url there.
5- Build and run the SkateboardSharingApp

How to use the system
=====================
Once the web application starts, the system will navigate to the Home screen, You should see a welcome message.
The home screen asks to click on the link "User Type", this is required as the system needs to differentiate between what type of user you are?
after clicking the "User Type" you will navigate to a user type page. here you must click to chose if you want to brows the syatem as an owner or a borrwer.
Since the scope of the challebge dose not include user management system the system will require the user to select an id for them to be able to brows the system

Borrower user
-------------
once borrwer is been chosen and an id is selected you can click on the submit, which will navigate to the borrower skateboard list view page(Index)
here the borrower is able to see availabel skateboards and has the ability to checkout/uncheck out  by selecting the toogel check out link for each.
as soon as the toggle checkout link is clicked the list will refresh and you will be able to see that a flag is set to checlout for the skatboard you picked as well as a checkd out date.

Owner user
-------------
Once owner is been chosen and an id is selected you can click on the submit, which will navigate to the owner skateboard list view page(Index)
Here the owner is able to see only his skateboards and has the ability to Edit/Delete/View details. 
The owner can make his skatboard un avaialbe in the market for sharing by editing the record and unchecking the is available checkbox and then saving


Assumptions
===========
All users already have accounts and subscriptions for the service. In other words, we won’t care about user management or payment.
Owner can only see his skateboards
Barrower can borrow more than one skateboard.
Borrower can see his borrowed skateboard and available ones as well


