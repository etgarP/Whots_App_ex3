import { Link } from 'react-router-dom'

function ToSign() {
    // click here to register line
    return (
        <span id="register-button">
            <p id="register-text">
                Registered?&nbsp;
                <Link to="/">
                    Click here
                </Link>&nbsp;to sign in.
            </p>
        </span>
    )
}
export default ToSign