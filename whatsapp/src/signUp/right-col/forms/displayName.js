function DisplayName({ value, set, text, setText }) {

    // sets the state using the value that changed
    function setInfo(e) {
        set(e.target.value)
    }

    // removes error text on click of the input
    function changeReq(e) {
        setText("Your Displayname must be 3-20 characters long, and may contain letters, numbers and spaces.")
    }

    // Display name 
    return (
        <>
            <label htmlFor="validationDefaultUsername">Display name</label>
            <div className="input-group">
                <div className="input-group-prepend">
                    <span className="input-group-text" id="inputGroupPrepend2">@</span>
                </div>
                <input onClick={changeReq} value={value} onChange={setInfo} type="text" className="form-control" id="validationDefaultUsername" placeholder="hemi" aria-describedby="inputGroupPrepend2" required></input>
            </div>
            <small id="passwordHelpBlock" className="form-text text-muted small-txt">
                {text}
            </small>
        </>
    )
}
export default DisplayName