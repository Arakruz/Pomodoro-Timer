# Pomodoro Study Timer and Planner

### This project was done for a computer science 210 course. It was effectively my first time doing a full project from
### start to finish. I might come back and add some new features and make some small changes at a later time, they will be 
### all marked on the list below with a !!! at the end.

## Project Overview

 **This project aims to** make an easy-to-use and highly customizable timer and planner for those trying to focus using 
 the *Pomodoro technique*. Pomodoro technique is a method of time management where you break down your work, study, or 
 focus time into:

- **Focus Time**
   - The time when you focus on the task at hand, originally 25 minutes, but as you get more used to the method, this 
   time can be significantly increased
   
- **Break Time**
   -  Between every focus time, there should be a break. In this case, it's a shorter break that helps to *assimilate 
    information* and give your brain a little rest. Originally done with 5 minutes but can vary wildly depending on your 
    focus time and how tiring you want to make your focus session.
    
- **Rest Time**
    - After 4 focus times (or what I'm calling a cycle), your brain starts to lose performance and needs a longer 
    resting period, and that's what this is for! Originally 10 minutes but similarly to the short break can vary wildly.
     

 I was already using this technique for quite a while and saw an impressive improvement in my study sessions. *However*, 
 I'm quite disappointed with the current apps in the market, always missing one or more features that I find essential. 
 That's why I decided to do this app, among other things, to allow the user to set up pre-made timers and use them to 
 make it *easier* and *simpler* to make use of this technique.
 
 ---
 
## User Stories
User stories are a way to describe how someone can use this application. These will also work as a task list of things 
to be implemented.

#### Changing basic information

##### Adding information
- [X] As a user, I want to add a session to my sessions list.

- [X] As a user, I want to add a focus timer to my focus session.
- [X] As a user, I want to add a short break timer to my focus session.
- [X] As a user, I want to add a long break timer to my focus session.

##### Removing and editing current information 
- [X] As a user, I want to remove a focus session from my sessions list.

- [X] As a user, I want to edit a focus session from my sessions list.
    - [X] As a user, I want to edit a focus timer from my focus session.
    
    - [X] As a user, I want to edit a short break timer from my focus session.
    - [X] As a user, I want to edit a long break timer from my focus session.

    
##### User interface
- [X] As a user, I want to see my sessions list with the timers and names of my sessions.

- [X] ~~As a user, I want to interact with the program through the console, changing screens, setting names etc.~~
- [X] As a user, I want to interact with the program through a GUI.
- [X] As a user, I want to select a focus session.
- [X] As a user, I want to be notified once a timer reaches 0. 

##### Main functionalities
- [X] As a user, I want to start a focus session (to be added with the GUI due to terminal functionality).

   - [X] As a user, I want to pause a focus session.
   
   - [X] As a user, I want to stop a focus session.
   - [X] As a user, I want to be able to see what timer is currently going and see it running.
   - [X] As a user, I want to receive a message when the current timer runs out.
   - [X] As a user, I want the timer only to change types after I allow it.
   
- [X] As a user, I want to be able to save my sessions to file
   
- [X] As a user, I want to be able to load my sessions from file

- [X] As a user, I want the sessions list to be automatically loaded from file when I open the program

## Phase 4 (part of the assignment needed for the course)

### Phase 4: Task 2

Changed the classes FocusSession and SessionsList to be more robust. In FocusSession intSetter and the constructor
throw SmallerThanOneException if a timer is trying to be set as 0 or less. In SessionsList the method removeSession
throws NoSessionException if no session is selected or given session is not in the list. Also, all classes in the 
packages "buttons" and "tools" are subclasses of the abstract classes Button and Tool respectively. They override some 
methods and utilize fields from the appropriate abstract class.

### Phase 4: Task 3

If I had more time to work on this code and refactor it I would start by changing the classes StopButton and StartButton
to properly follow the Liskov Substitution Principle. Another change would be how Editor, FocusSession and SessionList
interacts with other classes. It would be better to have all references to SessionsList and FocusSession basesd on the
objects in Editor, and possibly add observers to update some of the the fields in other classes instead of maintaining 
more complicated and restricted methods.
