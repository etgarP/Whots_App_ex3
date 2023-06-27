export function Username({ value, set, text, setText }) {
    // sets the state using the value that changed
    function setInfo(e) {
        set(e.target.value)
    }
    // removes error text
    function changeReq(e) {
        setText("Your username must be 5-20 characters long, and may contain letters, numbers and '_'.")
    }

    return (
        <>
            {/* takes Username  */}
            <label htmlFor="validationDefaultUsername2">Username</label>
            <div className="input-group">
                <div className="input-group-prepend">
                    <span className="input-group-text" id="inputGroupPrepend2">@</span>
                </div>
                <input onClick={changeReq} value={value} onChange={setInfo} type="text" className="form-control" id="validationDefaultUsername2" placeholder="hemi_hemi" aria-describedby="inputGroupPrepend2" required></input>
            </div>
            <small id="passwordHelpBlock" className="form-text text-muted small-txt">
                {text}
            </small>
        </>
    )
}
export default Username