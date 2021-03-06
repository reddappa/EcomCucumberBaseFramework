Feature: Login page feature

@login
Scenario: Login with correct credentials
Given user is on login page
When user enters username 'UserName'
And user enters password 'Password'
And user clicks on Login button
Then user gets the title of the page
And page title should be "My account - My Store"

@login
Scenario: Login with incorrect credentials
Given user is on login page
When user enters username 'UserName'
And user enters password 'Password'
And user clicks on Login button
Then user gets the title of the page
And page title should be "My account - My Store"