import { Link } from 'react-router-dom'

function ToRegister() {
    // "not registered?" line
    return (
        <span id="register-button">
            <p id="register-text">
                Not registered?&nbsp;
                <Link to="/signup">
                    Click here
                </Link>&nbsp;to register
            </p>
        </span>
    )
}
export default ToRegister