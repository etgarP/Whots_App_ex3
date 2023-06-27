import { useRef, useState } from "react"
import { postReqAuthorized } from "../../postReq"
import { getReq } from "../../getReq"
import { useNavigate } from 'react-router-dom'
import { socket } from "../../sockets/socket"

function AddContactIcon(props) {
    const { user, setContacts, token, contacts, setUser } = props
    const inputRef = useRef(null)
    const [value, setValue] = useState("")
    const navigate = useNavigate()
    // adding contact to contacts list
    async function getNewContacts(username) {
        try {
            const url = "http://localhost:12345/api/Chats"
            var res = await getReq(url, token)
            if (res.status === 401) {
                window.alert("Please log in again.")
            }
            if (res.ok) {
                var newContacts = await res.json()
                const newContact = newContacts.find((contact) => {
                    return contact.user.username === username
                })
                if (newContact) {
                    setContacts((prevContacts) => [...prevContacts, newContact])
                }
            }
        } catch (error) {
            throw error
        }
    }

    // adding person to server
    async function addPersonToServer(input) {
        try {
            const url = "http://localhost:12345/api/Chats"
            const data = { username: input }
            var res = await postReqAuthorized(data, url, token)
            if (res.ok) { }
            else if (res.status === 401) {
                window.alert("Disconnected, please try logging in again.")
                // logging out
                setUser('')
                navigate('/')
            } else if (res.status === 409) {
                window.alert("Chat already exists")
            } else {
                window.alert("Not a valid username")
            }
            return res.ok
        } catch (error) {
            throw error
        }
    }

    // handling press on add button
    const addPersonButtonHandler = async () => {
        try {
            let input = inputRef.current.value
            const found = contacts.find((contact) => {
                return contact.user.username === input
            })
            if (found) {
                window.alert("Username already exist");
                return
            }
            if (input) {
                let ok = await addPersonToServer(input)
                if (ok) {
                    await getNewContacts(input)
                }
                setValue("")
                socket.emit('usernameAdd', { sender: user.username, receiver: input })
            }
        } catch (error) {
            window.alert("Disconnected, please try logging in again.")
            setUser('')
            navigate('/')
        }
    }

    return (
        <div className="col-2 center align-right">
            {/* Add person icon modal */}
            <button className="icons-background no-border" type="button" data-bs-toggle="modal" data-bs-target="#addPersonModal">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" className="bi bi-person-add no-borderFicon" viewBox="0 0 16 16">
                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0Zm-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Z" />
                    <path d="M8.256 14a4.474 4.474 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10c.26 0 .507.009.74.025.226-.341.496-.65.804-.918C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4s1 1 1 1h5.256Z" />
                </svg>
            </button>
            {/* Modal */}
            <div className="modal fade" id="addPersonModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h1 className="modal-title fs-5" id="exampleModalLabel">Add new contact</h1>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        {/* Input */}
                        <div className="modal-body">
                            <input className="full-width" placeholder="Contact's username" ref={inputRef}
                                value={value} onChange={(ev) => { setValue(ev.target.value) }}></input>
                        </div>
                        {/* Add button */}
                        <div className="modal-footer">
                            <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={addPersonButtonHandler}>Add</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default AddContactIcon