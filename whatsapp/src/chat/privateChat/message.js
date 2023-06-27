function Message(props) {
    const { content, side } = props
    return (
        // a message in the chat
        <div className={`d-inline p-2 ${side} message`}>{content}</div>
    )
}
export default Message