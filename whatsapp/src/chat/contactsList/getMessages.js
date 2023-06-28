import { getReq } from "../../getReq"

// get to messages
async function getMesseges(id, user) {
    try {
        const url = `http://localhost:12345/api/Chats/${id}/Messages`
        const res = await getReq(url, user.token);
        if (!res.ok) return null
        const data = await res.json();
        if (Array.isArray(data)) {
            return data;
        }
    } catch (error) {
        throw error
    }
}

// setting contacts with new messeages on chat
export async function applyMessages(user, id, setContacts, upContact) {
    try {
        var messages = await getMesseges(id, user)
        if (!messages) return
        setContacts(contacts => {
            const index = contacts.findIndex(contact => contact.id === id);
            if (index !== -1) {
                const updatedContact = {
                    ...upContact,
                    messages: messages
                };
                const updatedContactsList = [
                    ...contacts.slice(0, index),
                    updatedContact,
                    ...contacts.slice(index + 1)
                ];
                return updatedContactsList;
            }
            return contacts;
        });
    } catch (error) {
        throw error
    }
}

// setting contacts with new messages in listed contacts
export async function applyMessagesListed(user, id, setContacts) {
    try {
        var messages = await getMesseges(id, user)
        setContacts(contacts => {
            const index = contacts.findIndex(contact => contact.id === id);
            if (index !== -1) {
                const updatedContact = {
                    ...contacts[index],
                    messages: messages,
                };
                const updatedContactsList = [
                    ...contacts.slice(0, index),
                    updatedContact,
                    ...contacts.slice(index + 1)
                ];
                return updatedContactsList;
            }
            return contacts;
        });
    } catch (error) {
        throw error
    }
}