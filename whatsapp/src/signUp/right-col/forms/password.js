function Password({ value, set, text, setText }) {

    // sets the state using the value that changed
    function setInfo(e) {
        set(e.target.value)
    }

    // removes error text on click of the input
    function changeReq(e) {
        setText("Your password must be 8-20 characters long, must contain a uppercase letter, lower case charecter and a number.")
    }

    // Password field 
    return (
        <>
            <div className="form-group">
                <label htmlFor="exampleInputPassword2">Password</label>
                <input onClick={changeReq} value={value} onChange={setInfo} type="password" className="form-control" id="exampleInputPassword2" placeholder="Password" required></input>
            </div>
            <small id="passwordHelpBlock" className="form-text text-muted small-txt">
                {text}
            </small>
        </>
    )
}
export default Password