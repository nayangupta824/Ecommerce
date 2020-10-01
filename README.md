# MyEcommerce

This is a basic E-Commerce website I built using JSF2 , primefaces during my Training/Internship at DXC technologies (HPE).<br />
There is an option for signup where a new user can signup with a valid email account. A verification link is sent to the user email to verify the account. After that the user is allowed to login.<br />
There are all the necessary validations working on signup and login page (although any bugs are welcome :)..)<br />
After successfull login a user can view the items in the gallery with following functionality : 
* Search
* Sort : default , price low-high , price high-low
* Categories (only two : shirts and shoes)
* Cart
* Orders (with status tracking)
* User profile needs to be worked on

After successfull order the user receives an email of successfull order and a message on mobile. (the api credits for texlocal have been exhausted. Get a new Key for messaging service to work again)

## Prerequisites
```
1) Java EE IDE
2) Apache Tomcat Server
    - Goto Apache/conf/context.xml
    - Edit it
    - Uncomment <Manager pathname="" /> tag in the XML to disable session persistence across Tomcat restarts
3) MySql Database
    - Import the MySql dump provided.
```

## Installing

* Integrate the MySql Database by changing the database password.
```
- cd workspace/MyEcommerce/src/model
- Edit AccountModel.java
- Change the variable 'pass' to the user password
- Then change the user in the respective databse connections (if it is "root" no change needed).
- Then change the email and password from where the email will be sent to the user in workspace/MyEcommerce/src/controller/AccountController
```
* 'import' the project into your workspace and right click on project name and run it on the server.

## Built With
* [JSF-2](https://en.wikipedia.org/wiki/JavaServer_Faces)
* [Primefaces](https://www.primefaces.org/)

## What's Next
* Adding customized suggestions
* Improving the GUI
* Adding More functionalities

## Issues?
Found any Bug? Please feel free to report it. :)
