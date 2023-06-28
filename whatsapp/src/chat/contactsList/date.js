function Date(props) {
    const { date } = props
    // prints the date
    return (
        <div className={`col align-right`}>
            <p className={`description`}>{date}</p>
        </div>
    )
}
export default Date