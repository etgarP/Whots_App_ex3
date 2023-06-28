// checks if the username is valid, if so returns true
export function validateUsername(name, setText) {
    // checks the if the input is correct
    // only letters numbers '_' and length 5 to 20
    const regex = /^[a-zA-Z0-9_]{5,20}$/
    if (regex.test(name)) {
        return true
    } else {
        // if the input isnt correct sets error text
        setText("Please enter a valid username.")
    }
    return false
}

// checks if the password is valid, if so returns true
export function validatePassword(password, setText) {
    // needs to have lower and higher case letter and be length 8-20
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,20}$/
    if (regex.test(password)) {
        return true
    }
    else {
        // if the input isnt correct sets error text
        setText("Please enter a valid password.")
        return false
    }
}

// checks if the confirm password is valid, if so returns true
export function validateConfirmPass(password, conPass, setText) {
    // checks if the confirm doesnt match or empty
    if (!(password === conPass) || conPass === '') {
        // if the input isnt correct sets error text
        setText("Please fill this field correctly.")
        return false
    }
    return true
}

// checks if the display name is valid, if so returns true
export function validateDisplayName(name, setText) {

    /* needs to have only higher and lower case letters or numbers or space
    and be betwe length 3 to 20 */
    const regex = /^[a-zA-Z0-9 ]{3,20}$/
    if (regex.test(name)) {
        return true
    } else {
        // if the input isnt correct sets error text
        setText("Please enter a valid displayname.")
        return false
    }
}

// checks if the picture is valid, if so returns true
export function validatePicture(picture, setText) {
    const MAXSIZE = 5 * 1024 * 1024
    if (picture !== '' && picture.size > MAXSIZE) {
        // if the input isnt correct sets error text
        setText("Max size of picture is 5MB!")
        return false
    }
    if (picture === '') {
        // if the input isnt correct sets error text
        setText("Please enter an image.")
        return false
    }
    return true
}