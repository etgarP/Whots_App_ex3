import ContactImage from "../contactImage"
import ContactName from "../contactName"
import Date from "./date"
import LastMessage from "./lastMassage"
import { applyMessagesListed } from './getMessages.js'
import { useNavigate } from 'react-router-dom'
import { socket } from "../../sockets/socket"
import { useEffect } from "react"
import Badge from './badge.js'
import { useState } from 'react'
import { getReq } from "../../getReq"
import { applyMessages } from "./getMessages.js"

function ListedContact(props) {
    const [badge, setBadge] = useState(0)
    const { contactId, id, picture, contactName, date = "", lastMessage = "", setContactId, upH, setContacts, user } = props
    const navigate = useNavigate()
    useEffect(() => {

        // handling sent messages
        const handleSendSocket = async (sentId) => {
            if (id === sentId && id !== contactId) {
                setBadge(badge + 1)
            }
            if (contactId === sentId) {
                setBadge(0)
            }
            if (sentId === contactId && id === contactId) {
                try {
                    let upContact = await reGetContact(contactId);
                    if (!upContact) return;
                    await applyMessages(user, sentId, setContacts, upContact)
                } catch (error) {
                    window.alert("Please log in again.")
                    navigate('/')
                }
            }
            if (id === sentId && id !== contactId) {
                console.log("were in")
                try {
                    let upContact = await reGetContact(id);
                    if (!upContact) return;
                    await applyMessages(user, sentId, setContacts, upContact)
                } catch (error) {
                    window.alert("Please log in again.")
                    navigate('/')
                }
            }
        }

        // handling delete person
        const handleDelete = async (sentId) => {
            if (sentId === id) {
                try {
                    await reGetContacts()
                } catch (error) {
                    window.alert("Please log in again.")
                    navigate('/')
                }
            }
        }

        socket.on('idDel', handleDelete)
        socket.on('idmsg', handleSendSocket)
        return () => {
            // Unregister event listeners and disconnect socket
            socket.off('idDel', handleDelete)
            socket.off('idmsg', handleSendSocket)
        };
    })

    // update contacts list
    const reGetContacts = async () => {
        try {
            const url = "http://localhost:12345/api/Chats"
            var res = await getReq(url, user.token);
            if (res.status === 401) {
                window.alert("Please log in again.")
                navigate("/")
            }
            var gotten = await res.json();
            if (Array.isArray(gotten)) {
                setContacts(gotten);
            }
        } catch (error) {
            throw error
        }
    }

    // update contacts list with new lastMessage
    const reGetContact = async (id) => {
        try {
            const url = "http://localhost:12345/api/Chats"
            var res = await getReq(url, user.token)
            if (res.status === 401) {
                window.alert("Please log in again.")
            }
            var newContacts = await res.json();
            if (Array.isArray(newContacts)) {
                let contact = newContacts.find((contact) => contact.id === id)
                return contact
            } else {
                // Handle the case where the response is not a valid array
                return null
            }
        } catch (error) {
            throw error
        }
    }

    // change the contact to the contact chosen
    const changeContact = async () => {
        try {
            await applyMessagesListed(user, id, setContacts)
            setContactId(id)
            setBadge(0)
        } catch (error) {
            window.alert("Disconnected, please log in.")
            navigate('/')
        }
    }
    return (
        // the contacted list person which is a button
        <button type={`button`} className={`list-group-item list-group-item-action person no-border-radius`}
            onClick={changeContact} onMouseMove={upH}>
            <div className="row">
                <div className={`row`}>
                    {/* the image of the contact */}
                    <ContactImage picture={picture}></ContactImage>
                    <div className="col desc-col">
                        <div className="row">
                            {/* the name of the contact */}
                            <ContactName contactName={contactName}></ContactName>
                            {/* the date of the last message the contact recieved */}
                            <Date date={date}></Date>
                            <Badge unReadNum={badge} />
                        </div >
                        {/* the last message the contact recieved */}
                        <LastMessage lastMessage={lastMessage}></LastMessage>
                    </div>
                </div>
            </div>
        </button>
    )
}
export default ListedContact