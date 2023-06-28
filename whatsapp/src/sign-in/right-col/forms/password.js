function Password({ setPassword }) {
    // saves the password change
    function updatePassword(e) {
        setPassword(e.target.value)
    }
    // password field
    return (
        <>
            <div className="form-group">
                <label htmlFor="exampleInputPassword1">Password</label>
                <input onChange={updatePassword} type="password" className="form-control" id="exampleInputPassword1" placeholder="Password" required></input>
            </div>
            <div className="form-check"></div>
        </>
    )
}
export default Password