@product
Feature: Login

@invalid-login
Scenario: Unable to login without username and password
Given the user is on the Login page
When the user clicks the Login button without fill the form
Then the website give error message "Username is required"