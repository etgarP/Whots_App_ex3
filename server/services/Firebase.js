let messaging

const getMessaging = (gotten) => {
    messaging = gotten
}

const sendMessage = (theMessage, sender, token) => {
    const message = {
        token: token, 
        notification: {
          title: sender,
          body: theMessage,
        }
      };
      
      // Send the message to the device token
      messaging
        .send(message)
}
const addContact = (friend, token) => {
  const message = {
      token: token, 
      notification: {
        title: "Friend Added!",
        body: friend,
      }
    };
    
    // Send the message to the device token
    messaging
      .send(message)
}
const removeContact = (friend, token) => {
  const message = {
      token: token, 
      notification: {
        title: "Friend removed :(",
        body: friend,
      }
    };
    
    // Send the message to the device token
    messaging
      .send(message)
}

module.exports = { removeContact, addContact, sendMessage, getMessaging }