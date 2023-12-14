## Ravelry_SeleniumTests

---

### About the project
This is a test automation project created using the Page Object model (POM) in Java with Maven, TestNG, and Selenium WebDriver.
##### Website
The website used for testing is [Ravelry](https://ravelry.com/). Ravelry functions as an organizational tool for fiber artists, including knitters, crocheters, and more. The site features tools to keep track of and share projects, resources, and ideas.  
More information is available on Ravelry's [About Our Site](https://www.ravelry.com/about) and [Wikipedia page](https://en.wikipedia.org/wiki/Ravelry).  
Ravelry can be accessed at https://www.ravelry.com/.
##### Login credentials
Most of the site functionality can only be accessed when the user is logged in. For testing purposes, an account was created using the following credentials:
<details>
  <summary>Login credentials</summary>

- username: `vcstestuser`
- password: `-vcs-_TestPa$$#2#2`
> These credentials should not be shared publicly.
</details>

##### Limitations
Ravelry limits login rates - if several login attempts fail consecutively (displaying incorrect password message), it is advised to wait approx. 15 minutes before resuming testing.  
Alternatively, these are credentials for a second test account:
<details>
  <summary>Login credentials</summary>

- username: `vcstestuser1`
- password: `-VCS-^t3stPW$)$)_i`
> These credentials should not be shared publicly.
</details>

Credentials need to be configured manually in the respective test classes.

---
### Steps to execute
1. Clone the repository
2. Import the project in any Java IDE
3. Run project using `testng.xml` configuration to execute all test classes in parallel OR run each test/test class sequentially manually

---
### Test scenarios and test cases
- **TS1** Login *[Login page]*
    - **TC1.1** Login using existing correct username and password
        - Enter an existing and correct username into Username field
        - Enter a correct password into Password field
        - Click "Log In" button  
           Expected result: successful login - Home page is displayed with user picture in the top right
    - **TC1.2** Login using non-existing username
        - Enter a non-existing or blank username into Username field
        - Enter any password into Password field
        - Click "Log In" button  
           Expected result: Incorrect Username message is displayed next to Username field
    - **TC1.3** Login using existing username and wrong password
        - Enter an existing and correct username into Username field
        - Enter an incorrect or blank password into Password field
        - Click "Log In" button  
           Expected result: Incorrect Password message is displayed next to Password field
- **TS2** Search yarns *[Yarns page]* *[Login required]*
    - **TC2.1** Search yarns using query which returns data
        - Enter a query into "Search yarns" field
        - Click "Search" button in "Search yarns" field
        - View Yarn Search page  
           Expected result: Yarn Search page 1 shows only results including the query
    - **TC2.2** Search yarns using empty query
        - Leave "Search yarns" field empty
        - Click "Search" button in "Search yarns" field
        - View Yarn Search page  
           Expected result: Yarn Search page 1 shows all yarns, sorted by project count
- **TS3** Browse yarns by fiber *[Yarns page]* *[Login required]*
    - **TC3.1** Browse yarns by selecting fiber
        - Select a fiber type element from list of types
        - View Yarn Search page  
           Expected result: Yarn Search page 1 shows only results including selected fiber and subtypes
- **TS4** Add or edit project notes *[Project page]* *[Login required]*
    - **TC4.1** Add a new note (containing no heading) to a project when no note exists
        - Make sure that the project does not already have a note
        - Click on "Add note" button
        - Leave heading field empty
        - Add new note text into body field
        - Click "Save this note"
        - Refresh the page  
           Expected result: a "Notes" element is created and the new note is displayed
    - **TC4.2** Edit an existing note entry (containing no heading) of a project
        - Click on "Notes" field
        - Add new note text
        - Click "Save this note"
        - Refresh the page  
           Expected result: new note text is displayed along with old text
    - **TC4.3** Replace an existing note entry (containing no heading) of a project
        - Click on "Notes" field
        - Remove existing note text
        - Add new note text
        - Click "Save this note"
        - Refresh the page  
           Expected result: old note text is replaced with new text
    - **TC4.4** Remove an existing non-dated note entry from project by removing field text
        - Click on "Notes" field
        - Remove existing note text
        - Click "Save this note"
        - Refresh the page  
           Expected result: note is removed from project and Notes field is no longer displayed
- **TS5** Edit pattern rating from project page *[Project page]* *[Login required]*
    - **TC5.1** Change overall rating
        - Make sure that the project has a Ravelry pattern attached
        - Select a 1 to 5 star value in "My overall rating"
        - Refresh the page  
           Expected result: overall pattern rating is saved and displayed
    - **TC5.2** Change clarity rating
        - Make sure that the project has a Ravelry pattern attached
        - Select a 1 to 5 star value in "My clarity rating"
        - Refresh the page  
           Expected result: pattern clarity rating is saved and displayed
    - **TC5.3** Change difficulty rating
        - Make sure that the project has a Ravelry pattern attached
        - Select a 1 to 10 bar segment value in "My difficulty rating"
        - Refresh the page  
           Expected result: pattern difficulty rating is saved and displayed
- **TS6** Edit project progress *[Project page]* *[Login required]*
    - **TC6.1** Edit progress on a project In Progress
        - Make sure that the project status is "In Progress"
        - Select desired percentage value in Progress bar
        - Refresh the page  
           Expected result: Progress percentage is saved and displayed
- **TS7** Add pattern to favorites *[Pattern page]* *[Login required]*
    - **TC7.1** Add pattern to favorites without adding notes
        - Click "Add to favorites" button
        - Leave "Your notes or comments" field empty
        - Click "Save changes" button
        - Navigate to My Notebook -> My Favorites  
           Expected result: upon navigating to My Favorites, chosen pattern is displayed as first pattern in list
    - **TC7.2** Add pattern to favorites and add a note
        - Click "Add to favorites" button
        - Enter text into "Your notes or comments" field
        - Click "Save changes" button
        - Navigate to My Notebook -> My Favorites  
           Expected result: upon navigating to My Favorites, chosen pattern is displayed along with the note text as first pattern in list

---
### Logging and screenshots
This project implements TestNG's ITestListener interface to capture screenshots (using Selenium) upon test failure.  
Screenshots are saved in the `screenshots` directory.  
Additional TestNG reports are saved in the `test-output` directory.