import pastoralPic from './pastoral_pic.jpg'

function LeftCol() {
    // the picture on the left side
    return (
        <div className="col-4 left-col">
            <img src={pastoralPic} alt="pastoral background img" className="photo"></img>
        </div>
    )
}
export default LeftCol