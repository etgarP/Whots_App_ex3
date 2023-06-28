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
        .then((response) => {
          console.log('Successfully sent notification:', response);
        })
        .catch((error) => {
          console.error('Error sending notification:', error);
        });
}

module.exports = { sendMessage, getMessaging }