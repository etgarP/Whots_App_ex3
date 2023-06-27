function LastMessage(props) {
    const { lastMessage } = props
    return (
        // prints the last message
        <div className={`row last-message`}>
            <p className={`description`}>{lastMessage}</p>
        </div>
    )
}
export default LastMessage