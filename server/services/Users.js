const User = require('../models/Users')

/*
gets a valid username, displayName, profilePic
returns a new user with these details and saves it
*/
const createUser = async (username, displayName, profilePic) => {
    try {
        const user = new User({ username, displayName, profilePic })
        await user.save()
        return
    } catch (error) {
        throw error
    }
}

/*
gets a valid username
returns that user from the database or null
*/
const getUser = async (username) => {
    try {
        const user = await User.findOne({ "username": username }).exec()
        return user
    } catch (error) {
        throw error
    }
};

module.exports = { getUser, createUser }