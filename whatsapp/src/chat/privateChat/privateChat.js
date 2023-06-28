import Message from "./message"
import SendMessage from "./sendMessage"
import background from "../pictures/whotsApp.png"

function PrivateChat(props) {
    const { contactId, setContacts, contacts, user } = props
    var contact = null;
    if (contacts != null)
        contact = contacts.find(contact => contact.id === contactId)
    // default background when no contact was chosen yet
    if (!contact) {
        return (
            <div className="col-7 right-side whots-container" id='bottom-right'>
                <img alt="" src={background} className="background-img" />
            </div>
        )
    }
    // creates chat log in the chat
    let mappedArray = [];
    if (contact.messages) {
        mappedArray = contact.messages
            .map((message) => (
                <Message
                    key={message.id}
                    content={message.content}
                    side={user.username === message.sender.username ? "right" : "left"}
                />
            )).reverse();
    }
    return (
        <div className="col-7 right-side" id='bottom-right'>
            <div className="page-within">
                <section className="message-rapper">
                    {/* prints the chat log */}
                    {mappedArray}
                </section>
            </div>
            {/* send message area */}
            <SendMessage user={user} setContacts={setContacts} contact={contact} contactId={contactId}></SendMessage>
        </div>
    )
}
export default PrivateChat