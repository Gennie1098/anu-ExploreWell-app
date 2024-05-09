# [G6 - ExploreWell] Report

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#application-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-meetings)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative
- Firebase Repository Link: https://simple-signup-56342-default-rtdb.asia-southeast1.firebasedatabase.app/
    - Confirm: I have already added comp21006442@gmail.com as a Developer to the Firebase project prior to due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
    - Username: comp2100@anu.edu.au	Password: comp2100
    - Username: comp6442@anu.edu.au	Password: comp6442

## Team Members and Roles
The key area(s) of responsibilities for each member

| UID      |      Name       |                     Role |
|:---------|:---------------:|-------------------------:|
| u7779236 |    Kai Phan     | Data Developer + BE + QA |
| u7793565 |   Qihua Huang   |                  BE + QA |
| u7776887 |    Qinjue Wu    |                  BE + QA |
| u7284324 | Lachlan Stewart |                  BE + QA |
| u7616055 |  Gennie Nguyen  |       PM + Designer + FE |


## Summary of Individual Contributions

1. **u7779236, Kai Phan**  I have 20% contribution, as follows: <br>
- **Code Contribution in the final App**
    - Firebase Connect - 
    - Feature Login - class Dummy: [Dummy.java](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java)
    - Feature Sign in  -  class AnotherClass: [functionOne()](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43), [function2()](the-URL)
    - Feature Logout - 
    - Data Instances - [Data File](items/final_data.json)
    - Codebase -



- **Code and App Design**
    - [What design patterns, data structures, did the involved member propose?]*
    - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* <br><br>

- **Others**: (only if significant and significantly different from an "average contribution")
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>

2. **u7284324, Lachlan Stewart** 20% Contribution as follows: <br>
    - Creation of posts (add class)
    - Organisation of posts in an AVL tree
    - Tokenizer (add class)
    - Parser (add class)
    - Tokenizer and Parser Unit Tests (add class)
    - Creation of Comments (add classe)

- **Code and App Design**
    - I suggested the use of the AVL tree for storing the posts for retrieval by title
    - I proposed and implemented the user of AutoCompletion in relevant text fields to help the user write compliant posts and searches

- **Others**: (only if significant and significantly different from an "average contribution")
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>



## Application Description

**ExploreWell: Your Gateway to a Healthier Planet and Self**

ExploreWell is an innovative application designed for outdoor enthusiasts who are passionate about hiking, climbing, diving, and more. It serves as a comprehensive platform to **connect like-minded individuals**, **offer valuable resources**, and **provide practical tools** to enhance your outdoor adventures.

### Key Features:
- **Social Connectivity:** Create and join communities with fellow adventurers to share experiences, tips, and stories.
- **Expert Guidance:** Access a network of certified outdoor professionals, each verified with a distinct badge next to their profile, ensuring trustworthy advice.
- **Resource Hub:** Find detailed guides on trails, dive sites, and climbing routes, including difficulty ratings and sustainability practices.
- **Health & Safety:** Get real-time updates on weather conditions, safety alerts, and first-aid tips tailored to your location and activity.
- **Sustainable Practices:** Learn how to engage with nature responsibly with our sustainability toolkit, aligned with the UN's Sustainable Development Goals, particularly Goal 3: Good Health and Well-being.
- **Event Planner:** Organize or participate in eco-friendly outdoor events that promote physical health and environmental awareness.

### Why Choose ExploreWell?
With ExploreWell, you're not just embarking on another outdoor journey; you're joining a movement towards **sustainable living** and **personal well-being**. Our app encourages users to explore the natural world responsibly, fostering a community that values health, safety, and the environment.

**Join ExploreWell today and transform your outdoor experiences into a force for good!**


### Application Use Cases and or Examples

**Target Users: Outdoor Enthusiasts**

- **Case 1: Community Engagement**
  *Sarah, an avid hiker, uses ExploreWell to connect with other hiking enthusiasts in her area. She joins a community within the app where members share their favorite trails and safety tips. Sarah finds a new hiking buddy through the app, and they plan a weekend trail hike using the Event Planner feature.*

- **Case 2: Expert Advice**
  *John, a beginner climber, seeks advice on starting his climbing journey. He uses ExploreWell to find certified professionals who provide him with guidance on equipment and techniques. John feels confident and prepared for his first climb thanks to the expert tips he received.*

- **Case 3: Sustainable Exploration**
  *Emma, passionate about marine life, uses ExploreWell's Resource Hub to find sustainable diving sites. She learns about the importance of preserving coral reefs and follows the sustainability toolkit's guidelines during her dives. Emma also participates in a beach cleanup event organized through the app.*

- **Case 4: Health & Safety Prioritization**
  *Mike, planning a mountain biking adventure, checks ExploreWell for real-time weather updates and safety alerts for his chosen route. He also reviews the first-aid tips provided by the app, ensuring he's well-prepared for any unexpected situations.*

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

<hr> 

### Application UML ---- TODO

![ClassDiagramExample](media/_examples/ClassDiagramExample.png) <br>
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*

<hr>

## Code Design and Decisions

In the UI, we used MultiAutoCompleteTextView and AutoCompleteTextViews to assist the user in writing search strings and posts which are compliant with the grammar. 

We used Fragments to structure the application in a modular, scaleable and modern fashion.

<hr>

### Data Structures

*[What data structures did your team utilise? Where and why?]*

Here is a partial (short) example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *AVL Tree*
    * *Objective: used for storing posts by title*
    * *Code Locations: TODO*
    * *Reasons:*
        * Allows for efficient retrieval *

2. ...

3. ...

<hr>

### Design Patterns
*[What design patterns did your team utilise? Where and why?]*

1. *xxx Pattern*
    * *Objective: used for storing xxxx for xxx feature.*
    * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
    * *Reasons:*
        * ...

<hr>

### Parser

### <u>Grammar(s)</u>
The grammar is intended to be simple and user friendly:

Tokens:
- Alpha
- At
- Hashtag

Formal Grammar:
- \<X\> ::= \<T\> | \<T\> \<Y\> | \<Y\>
- \<T\> ::= alpha | alpha \<T\>
- \<Y\> ::= \<Z\> | \<Z\> \<Y\>
- \<Z\> ::= hashtag alpha | at alpha

This grammar was the result of several iterations. Initially we considered whitespace to be more important and had a special token for it, but removing the whitespace as done in labs in the course was a better way to go and left us with a simpler grammar to implement. It also gives the user room for error in case they accidentally add some whitespace. 

The development of the tokenizer was test-driven, it was not added to the app until it passed all tests.

We also intended to have the user input complex titles (any title with more than one word and/or non-alphanumeric punctuation) within double quotes, but we decided this would also be too complex.

Originally, it was decided to make the tokenizer only accept alphanumeric titles, but this was changed to allow titles which do not contain the # or @ symbols. For example, the user might want to input the title "Mt. Majura", which uses the '.' symbol.

### <u>Tokenizers and Parsers</u>
The Tokenizer and Parser is used to ensure that the user provides a valid search string and to parse a valid search query. 
Whitespace separates tokens.

<hr>

### Others

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

<br>
<hr>

## Implemented Features
*[What features have you implemented? where, how, and why?]* <br>
*List all features you have completed in their separate categories with their featureId. THe features must be one of the basic/custom features, or an approved feature from Voice Four Feature.*

### Basic Features
1. [LogIn]. Description of the feature ... (easy)
    * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
    * Description of feature: ... <br>
    * Description of your implementation: ... <br>

2. [DataFiles]. Description  ... ... (...)
    * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
    * Link to the Firebase repo: ...

3. ...
   <br>

### Custom Features
Feature Category: Privacy <br>
1. [Privacy-Request]. Description of the feature  (easy)
    * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
    * Description of your implementation: ... <br>
      <br>

2. [Privacy-Block]. Description ... ... (medium)
   ... ...
   <br><br>

Feature Category: Firebase Integration <br>
3. [FB-Auth] Description of the feature (easy)
    * Code: [Class X, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
    * [Class B](../src/path/to/class/file.java#L30-85): methods A, B, C, lines of code: 30 to 85
    * Description of your implementation: ... <br>

<hr>

### Surprise Features

- If implemented, explain how your solution addresses the task (any detail requirements will be released with the surprise feature specifications).
- State that "Suprised feature is not implemented" otherwise.

<br> <hr>

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*
*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

*Here is an example:*

1. *Bug 1:*
    - *A space bar (' ') in the sign in email will crash the application.*
    - ...

2. *Bug 2:*
3. ...

<br> <hr>


## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Login/ Sign in
    - Code: [AuthenticationUnitTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7616055/gp-24s1/-/blob/main/app/src/test/java/com/anu/gp24s1/AuthenticationUnitTest.java?ref_type=heads) for the [Validator Class, entire file](https://gitlab.cecs.anu.edu.au/u7616055/gp-24s1/-/blob/main/app/src/main/java/com/anu/gp24s1/utils/Validator.java?ref_type=heads)
    - *Number of test cases: 10*
    - *Code coverage: 100%*
    - *Types of tests created and descriptions: *
        - Null Input Tests: These tests check the behavior of the code when it is provided with null inputs. For example, `userNameValidation_NullUserName` and `passwordValidation_Null` test the methods `isUserNameValid` and `isPasswordValid` respectively with null inputs.

        - Empty Input Tests: These tests verify the code’s behavior when it is given empty inputs. For instance, `userNameValidation_EmptyUserName` and `passwordValidation_Empty` test the methods `isUserNameValid` and `isPasswordValid` respectively with empty strings.

        - Invalid Input Tests: These tests are designed to check the code’s behavior with invalid inputs. For example, `userNameValidation_InvalidEmailUserName` and `passwordValidation_InvalidPassword` test the methods `isUserNameValid` and `isPasswordValid` respectively with inputs that do not meet the criteria for a valid username or password.

        - Valid Input Tests: These tests confirm that the code behaves as expected when provided with valid inputs. For instance, `userNameValidation_ValidEmailUserName`, `userNameValidation_ValidNonEmailUserName`, `passwordValidation_ValidPassword`, and `passwordValidation_ValidScapePassword` test the methods `isUserNameValid` and `isPasswordValid` respectively with inputs that meet the criteria for a valid username or password.


2. xxx

...

<br> <hr>


## Team Management

### Meetings Records
* Link to the minutes of your meetings like above. There must be at least 4 team meetings.
  (each commited within 2 days aftre the meeting)
* Your meetings should also have a reasonable date spanning across Week 6 to 11.*


- *[Team Meeting 1](meeting-240401.md)*
- *[Team Meeting 2](minute-240408.md)*
- *[Team Meeting 3](meeting-240415.md)*
- *[Team Meeting 4](meeting-240418.md)*
- *[Team Meeting 5](meeting-240422.md)*
- *[Team Meeting 6](meeting-30042024.md)*
- *[Team Meeting 7](meeting-240506.md)*

<hr>

### Conflict Resolution Protocol
- **Majority Rule for Problem Solving**:
    - If a problem arises that affects the team or the project, we will discuss the issue as a team.
    - Each member will have the opportunity to propose a solution.
    - We will then vote on the proposed solutions, and the solution with the majority votes will be implemented.

- **Peer Support for Challenges**:
    - If a team member encounters a challenge or gets stuck on a task, they are encouraged to raise their voice and seek help from the team.
    - We believe in the power of collective problem-solving and that every challenge can be overcome with teamwork.

- **Strict Adherence to Deadlines**:
    - All team members are expected to follow the project plan and meet their respective deadlines.
    - This is crucial to ensure that our project stays on track and is completed within the stipulated time frame.

- **Mitigation Plan for Unforeseen Incidents**:
    - In the event of unforeseen incidents such as a team member falling sick, the rest of the team will step in to help.
    - Tasks will be redistributed among the remaining team members to ensure that the project remains on schedule.

