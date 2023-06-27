import pastoralPic from './pastoral_pic.jpg'

function LeftCol() {
    // picture in left side of the sign-in page
    return (
        <div className="col left-col">
            <img alt="" src={pastoralPic} className="photo"></img>
        </div>
    )
}
export default LeftCol