import ContactImage from "../contactImage"
import DisplayName from "./displayName"
import AddContactIcon from "./addContactIcon"
import EscapeIcon from "./escapeIcon"

function TopLeft(props) {
    const { user, token, picture, displayName, setContacts, setUser, contacts } = props
    // top left where the user info and icons are (icons of add person and logout)
    return (
        <div className="col-5 no-padding my-box-bottom">
            <div className="list-group no-rounded my-box-left">
                <div id="person-container">
                    <div className="row">
                        <div className="row">
                            {/* info about the user */}
                            <ContactImage picture={picture}></ContactImage>
                            <DisplayName displayName={displayName}></DisplayName>
                            {/* icons */}
                            <AddContactIcon user={user} token={token} setContacts={setContacts} contacts={contacts} setUser={setUser}></AddContactIcon>
                            <EscapeIcon setUser={setUser}></EscapeIcon>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default TopLeft