import './signUp.css'
import LeftCol from './leftCol'
import RightCol from './right-col/rightCol'

function SignUp() {
  //page devision - picture on the left and form on the right
  return (
    <div className="row" id="container-sign">
      <LeftCol></LeftCol>
      <RightCol></RightCol>
    </div>
  )
}
export default SignUp