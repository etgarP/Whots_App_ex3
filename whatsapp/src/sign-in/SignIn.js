import './SignIn.css'
import LeftCol from './leftCol'
import RightCol from './right-col/rightCol'

function SignIn({ setUser, setContacts }) {
  // devision of sign in page to right and left
  return (
    <>
      <div className="row" id="container-sign">
        <LeftCol></LeftCol>
        <RightCol setUser={setUser} setContacts={setContacts}></RightCol>
      </div>
    </>
  )
}

export default SignIn