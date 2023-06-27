export async function deleteReq(data, url, token) {
    try {
        const res = await fetch(url, {
            method: 'delete',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'bearer ' + token
            },
            body: JSON.stringify(data),
        });
        return res;
    } catch (error) {
        throw error; // Rethrow the error to propagate it to the caller
    }
}