import io from "socket.io-client"
const socket = io("http://localhost:12345")

socket.on("connect", () => {
  console.log("connected");
})

socket.on("connect_error", (err) => {
  console.log(`connect_error due to ${err.message}`);
})

export { socket }