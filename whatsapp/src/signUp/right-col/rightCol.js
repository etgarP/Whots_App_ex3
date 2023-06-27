import Form from './forms/form.js'
import Welcome from './welcome.js'
import ToSign from './toSign.js'

function RightCol({ users, setUsers }) {
  // devision of right side to 3 parts - the register area
  return (
    <div className="col right-col">
      <div className="p-5">
        <Welcome></Welcome>
        <Form ></Form>
        <ToSign></ToSign>
      </div>
    </div>
  )
}
export default RightCol