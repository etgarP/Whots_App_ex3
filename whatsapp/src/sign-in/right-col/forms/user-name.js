function Username({ setName }) {
    // saves the value change
    function changeVal(e) {
        setName(e.target.value)
    }
    // username field
    return (
        <div className="col mb-3">
            <label htmlFor="validationDefaultUsername">Username</label>
            <div className="input-group">
                <div className="input-group-prepend">
                    <span className="input-group-text" id="inputGroupPrepend2">@</span>
                </div>
                <input type="text" onChange={changeVal} className="form-control" id="validationDefaultUsername" placeholder="hemi hemi" aria-describedby="inputGroupPrepend2" required></input>
            </div>
        </div>
    )
}
export default Username