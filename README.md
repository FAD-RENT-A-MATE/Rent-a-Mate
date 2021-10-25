# Rent-a-Mate

Original App Design Project - README
===

# Rent A Mate

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
The purpose of this dating app is to target single or lonely users and allow them to pick a date for different occasions including, social events, functions, and date nights. If you are looking for love, a fling, or just a buddy, Rent-a-Mate is the go to app. The user can pick the type of date they would like by selecting through photos of their preference. The user will have the option to rate each candidate and also select the person of interest from other people's rates.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Social/dating
- **Mobile:** Website can be viewed from mobile device.
- **Story:** Allows users to be matched with a compatible partner for whatever meets their needs.
- **Market:** This app is perfect for anyone looking for a quick date or something long-term
- **Habit:** Users can browse for matches as long as they wish. 
- **Scope:** Dating apps have evolved by providing convenience in the modern dating world. Dating online has allowed users to connect with one another before meeting in person.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can create an account
* User can log in
* User can set a profile picture
* User can set a description
* User can rate others
* User can view a feed of profiles

**Optional Nice-to-have Stories**

* User can send a direct message to other users
* User can view a feed based on their preferences: looking for love, quick fling, or both.

### 2. Screen Archetypes

* Create an Account/ Login Screen
   * User must create an account in order to be allowed access to the site.
   * User must make first initial payment to gain access to the account.
   * User must log in before accessing the account
   * If user forgot password, they will click the "forgot password" button to retrieve password.
* Home Feed Screen
    * User can view a feed of other user profiles. (User will filter candidates based off of ethnicity, gender, and location. The screen will display candidates photo and ratings.)
* Profile Page
    * User can view a page of their profile and update it if they wish. Profile will display user photos and ratings from other account users.
* Candidate's Profile page
    * Users will have the option to click on the candidates photo to view their profile. From their profile, the user will have access to ratings and comments left by other users. (To confirm the selected candidate, click the submit button. To cancel your selection, click the cancel button. If the user selects 'cancel' the user will return to the home screen)
* Settings
    * User will be able to modify settings for their account including: password, profile image, description, and etc.



### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home feed 
* Profile page
* Settings

**Flow Navigation** (Screen to Screen)

* Create an Account Screen 
   * =>Profile Screen
* Login Screen
    * =>Profile Screen 
* Home Feed Screen
    * =>Candidates Profile Screen
* Profile Screen
   * =>None
* Candidates Profile Screen 
    * =>Home Feed
* Settings Screen
    * =>None

## Wireframes
[Add picture of your hand sketched wireframes in this section]
![](https://i.imgur.com/2rbGgDV.jpg)


### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
![](https://i.imgur.com/3d7SzvP.png)
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]








CRUD	HTTP Verb	Example
Create	POST	Creating a new rank from the candidate’s profile
Read	GET	Fetching posts for a user's profile
Update	PUT	Changing a user's profile image
Delete	DELETE	Deleting a comment/rank from the user’s profile.

•	Home Feed Screen
o	(Read/GET) Query all ranks for each user
o	(Read/GET) Query profile image from candidate
o	(Update/PUT) Search candidate’s account 
•	Profile Screen
o	(Read/GET) Query logged in user object
o	(Update/PUT) Update user profile image
o	(Create/POST) Create a new post object
o	(Create/POST) Create a new comment on a user profile
o	(Delete) Delete existing comment
o	(Create/POST) Create a new rank on a user’s profile
o	(Delete) Delete existing rank
	
Parse Method	Example
Create and save objects
Creating a new rank/post 
Query objects and set conditions
Fetching post/ranks for a user's profile
Query object, update properties & save
Changing a user's profile image
Query object and delete
Deleting a comment by the user who posted the comment







// (Read/GET) Query all ranks for each user
let query = PFQuery(className:"Rank")
query.whereKey("author", equalTo: currentUser)
query.order(byDescending: "createdAt")
query.findObjectsInBackground { (ranks: [PFObject]?, error: Error?) in
   if let error = error {
      print(error.localizedDescription)
   } else if let ranks = ranks {
      print("Successfully retrieved \(ranks.count) ranks.")
      // TODO: Do something with posts...
   }
}

