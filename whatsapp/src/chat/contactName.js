function ContactName(props) {
    const { contactName } = props
    return (
        // contact's name
        <div className={`col`}>
            <p id={`name`}>{contactName}</p>
        </div>
    )
}
export default ContactName