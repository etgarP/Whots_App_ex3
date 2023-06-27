import { useState } from "react"
import Username from "./user-name"
import Password from "./password"
import { useNavigate } from 'react-router-dom'
import { postReq } from '../../../postReq.js'
import { getReq } from '../../../getReq.js'

function Form({ setUser, setContacts }) {
    // needed to use navigate in and inner function context
    const navigate = useNavigate()
    // saves the name password and error
    const [name, setName] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')

    // checks if the details are correct and if so connects to chat page and saves user info
    async function handleSubmit(e) {
        e.preventDefault()

        // get chats from server and updates contact
        async function getChats() {
            try {
                const url = "http://localhost:12345/api/Chats"
                var res = await getReq(url, user.token);
                if (res.status === 401) {
                    setError("Please try again.")
                }
                var gotten = await res.json();
                if (Array.isArray(gotten)) {
                    setContacts(gotten);
                }
            } catch (error) {
                throw error
            }
        }

        const data = { username: name, password: password }
        try {
            setUser("")
            const url = "http://localhost:12345/api/Tokens"
            var res = await postReq(data, url)
            if (!res.ok) {
                setError("Wrong username or password.")
            }
            const token = (await res.text()).trim()
            const url2 = `http://localhost:12345/api/Users/${name}`
            var res2 = await getReq(url2, token)
            if (!res2.ok) {
                setError("Please try again in a few minutes.")
                return;
            }
            var user = await res2.json()
            user = { ...user, token: token };
            if (res2.ok) {
                setUser(user)
                navigate('/chat')
                await getChats();
                return;
            } else {
                setError("Wrong Credentials")
                return;
            }
        } catch (error) {
            setError("Oops! Our server seems to be taking a coffee break ☕️. We're working hard to fix it and get things back on track. Please bear with us and try again shortly. Thank you for your patience!")
        }
    }

    // the form itself
    return (
        <form onSubmit={handleSubmit}>
            <Username setName={setName}></Username>
            <Password setPassword={setPassword}></Password>
            <button type="submit" className="btn btn-primary">Login</button>
            <br />
            <small id="passwordHelpBlock" className="form-text text-danger small-txt">{error}</small>
        </form>
    )
}
export default Form