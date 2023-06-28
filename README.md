# Android Whots-App Client
This repository contains an Android application developed for the Advanced Programming Class assignment. The app serves as a client for our chat system and includes several new features and enhancements compared to the previous assignment.

## Features
The Android chat app offers the following features:

## User Authentication and Registration
* Login screen: Users can securely log in to the chat app using their credentials.
* Registration screen: New users can create an account by providing the necessary details.
## Chat Screens
The chat functionality is divided into two different screens:

1. Contacts screen: Displays a list of all the chats the user is engaged in. It shows the contacts' names and profile pictures.
2. Chat screen: Opens when a user selects a specific chat from the contacts screen. It allows users to exchange messages in real-time.
## Adding New Contacts
Users can add new contacts to their chat list by using the "Add Contact" feature. This allows them to initiate conversations with new users.

## Settings
The app includes a settings screen where users can customize various aspects of the application:

* Server Settings: Users can modify the server address they connect to, allowing flexibility in working with different servers.
* Theme Selection: Users can change the app's theme, and switch between using light-mode, dark-mode, or system default.
## Local Data Storage
The Android app stores a local copy of chats, messages, and other relevant information. This allows viewing your contacts and messages offline.

## Push Notifications
The server implements push notifications using Firebase. When a user sends a message to another user, the server pushes the notification to the recipient's device. If the app is closed, the user still receives the notification.

## Integration with Web App
The Android app seamlessly integrates with the web application developed in the previous assignment. It enables Real-time messaging:

* Users can send and receive messages, as well as other actions, in real-time with other users, whether they are using the Android app or the web interface.

How to Run the App
To run the Android chat app, follow these steps:

1. Clone the repository to your local machine.
2. Open the server folder.
3. Run npm i to install all the necessary dependencies.
4. Start the server using npm start.
5. Open the project in Android Studio.
6. Run the project in the emulator or on your device.
7. Click on "Set Server Settings" and enter the server IP.
8. Log in or sign in to start using the app.
9. Enjoy seamless communication with other users using the Android chat app!

Note: You can run the web application by going in address http://localhost:12345 after youve run the server.