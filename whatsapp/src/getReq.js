export async function getReq(url, token) {
    try {
        const res = await fetch(url, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'bearer ' + token // attach the token
            }
        })
        return res;
    } catch (error) {
        throw error; // Rethrow the error to propagate it to the caller
    }
}