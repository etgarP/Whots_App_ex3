function Picture({ value, set, text, setText }) {

    // sets the state using the value that changed
    function setInfo(e) {
        const file = e.target.files[0]
        set(file)
    }

    // removes error text on click of the input
    function changeReq(e) {
        setText("You must upload a picture of the following formats: .jpg, .jpeg, .png, with a max size of 5MB.")
    }

    // Picture field
    return (
        <div className="mb-3">
            <label htmlFor="validationDefaultUsername">Picture</label>
            <input type="file" onClick={changeReq} onChange={setInfo} className="form-control" accept="image/*" id="formFile" required></input>
            <small id="passwordHelpBlock" className="form-text text-muted small-txt">
                {text}
            </small>
        </div>
    )
}
export default Picture