import defaultUserAvatar from './pictures/default-avatar.jpg'

function ContactImage({ picture = defaultUserAvatar }) {
    return (
        // contact's picture
        <div className='picture-col'>
            <div id={`photo-container`}>
                <img src={`${picture}`} id={`photo`} alt={`a person`}></img>
            </div>
        </div>
    )
}
export default ContactImage