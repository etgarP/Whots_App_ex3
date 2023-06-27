function DisplayName(props) {
    const { displayName } = props
    return (
        // display name of user
        <div className={`col center`}>
            <p id="name" className={`margin-left-3`}>{displayName}</p>
        </div>
    )
}
export default DisplayName