function ConfirmPassword({ value, set, text, setText }) {

    // sets the state using the value that changed
    function setInfo(e) {
        set(e.target.value)
    }

    // removes error text on click of the input
    function changeReq(e) {
        setText("Must match entered password.")
    }

    // Confirm password field
    return (
        <>
            <div className="form-group">
                <label htmlFor="exampleInputPassword1">Confirm password</label>
                <input onClick={changeReq} onChange={setInfo} value={value} type="password" className="form-control" id="exampleInputPassword1" placeholder="Password" required></input>
            </div>
            <small id="passwordHelpBlock" className="form-text text-muted small-txt">
                {text}
            </small>
            <br />
        </>
    )
}
export default ConfirmPassword