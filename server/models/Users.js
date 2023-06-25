const mongoose = require('mongoose')

const Schema = mongoose.Schema

const User = new Schema({
    username: {
        type: String,
        required: true,
        nullable: true
    },
    displayName: {
        type: String,
        required: true,
        nullable: true
    },
    profilePic: {
        type: String,
        required: true,
        nullable: true
    }
})

module.exports = mongoose.model('User', User)
