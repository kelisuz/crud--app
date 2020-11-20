# Aquent developer candidate project

You can find the code to use as the basis for this project at https://github.com/aquent/crud-app. Please fork the repo on GitHub and submit a link to your fork.

This is a Maven project. It is a simple CRUD web application known to work with Java 8. It uses Spring Boot with Thymeleaf views and Spring JDBC with an embedded database. The established features allow the user to manage a list of people with contact information.

Please implement the stories below to the best of your ability. Feel free to add features or technical improvements you feel are important or valuable as you see fit and have time. Be as creative as you want (even if that means using a completely different approach.) Feel free to correct our mistakes as well.

## Story #1

Add ability to manage clients (companies):

* The user should be able to create, edit, delete and list Clients.
* Clients should have a company name, website URI, phone number, and physical/mailing address.
* Clients can have zero, one, or multiple associated contacts.
* When editing a person, the user should be able to choose the associated client.
* When viewing a person, the associated client should be shown.
* When viewing a client, the associated contacts should be shown.
* When editing a client, the user should be able to add or remove associated contacts.

## Story #2

Add client­side styling and validation:

* Using your preferred javascript validation technique, prevent the submission on invalid data on the edit forms and inform the user of errors. The validations implemented on the front­end should be equivalent to the existing server side validation.
* Add standard styling to the pages using a modern CSS3/HTML5 framework like Twitter Bootstrap or Foundation (or similar).
