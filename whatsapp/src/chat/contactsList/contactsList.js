import ListedContact from "./listedContact"
import { socket } from "../../sockets/socket"
import { useNavigate } from "react-router-dom"
import { useEffect } from "react"
import { getReq } from "../../getReq"

function ContactsList(props) {
  const { contacts, setContacts, setContactId, contactId, upH, user } = props
  const navigate = useNavigate()

  useEffect(() => {
    // handling add person
    const handleAdd = async ({ sender, receiver }) => {
      if (user.username === receiver) {
        try {
          let upContact = await reGetContact(sender);
          if (!upContact) return;
          setContacts(prevContacts => ([...prevContacts, upContact]));
        } catch (error) {
          window.alert("Please log in again.")
          navigate('/')
        }
      }
    }
    socket.on('usernameAdd', handleAdd)
    return () => {
      // Unregister event listeners and disconnect socket
      socket.off('usernameAdd', handleAdd)
    };
  })

  // update contacts list with new lastMessage
  const reGetContact = async (sender) => {
    try {
      const url = "http://localhost:12345/api/Chats"
      var res = await getReq(url, user.token)
      if (res.status === 401) {
        window.alert("Please log in again.")
      }
      var newContacts = await res.json();
      if (Array.isArray(newContacts)) {
        let contact = newContacts.find((contact) => contact.user.username === sender)
        return contact
      } else {
        // Handle the case where the response is not a valid array
        return null
      }
    } catch (error) {
      throw error
    }
  }


  if (Array.isArray(contacts)) {
    // mapping the contacts list
    const mappedarray = contacts.map((contact) => {

      // checking if has last massage. if it does-handle.
      var timeAndDate
      if (contact.lastMessage != null) {
        var dateString = contact.lastMessage.created;
        const dateObject = new Date(dateString);
        const formattedDate = dateObject.toLocaleDateString();
        const formattedTime = dateObject.toLocaleTimeString();
        timeAndDate = formattedDate + " " + formattedTime;
      }

      // adding contact to mapped array  
      return (
        <ListedContact
          key={contact.id}
          id={contact.id}
          contactId={contactId}
          contactName={contact.user.displayName}
          picture={contact.user.profilePic}
          date={contact.lastMessage ? timeAndDate : null}
          lastMessage={contact.lastMessage ? contact.lastMessage.content : null}
          setContactId={setContactId}
          upH={upH}
          setContacts={setContacts}
          contacts={contacts}
          user={user}
        />
      )
    })

    // the final contacts list
    return <>{mappedarray}</>
  } else {
    // if contacts list is empty
    return null
  }
}
export default ContactsList