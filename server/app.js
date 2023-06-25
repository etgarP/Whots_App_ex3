const express = require('express');
const bodyParser = require('body-parser');
var app = express();
const http = require('http')
const cors = require('cors');
const customEnv = require('custom-env');
const mongoose = require('mongoose')

const server = http.createServer(app)
const { Server } = require("socket.io")

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.json());

app.use(cors());

customEnv.env(process.env.NODE_ENV, './config');
console.log(process.env.CONNECTION_STRING)
console.log(process.env.PORT)

const io = new Server(server, {
    cors: {
        origin: 'http://localhost:3000',
        methods: ["GET", "POST", "DELETE"],
        credentials: true
    }
})

mongoose.connect(process.env.CONNECTION_STRING, {
    useNewUrlParser: true,
    useUnifiedTopology: true
})

app.use(express.static('public'))

const Chats = require('./routes/Chats')
app.use('/api/Chats', Chats);
const Users = require('./routes/Users')
app.use('/api/Users', Users);
const Tokens = require('./routes/Tokens')
app.use('/api/Tokens', Tokens);

io.on('connection', (socket) => {
    socket.on('idmsg', (id) => {
        socket.broadcast.emit('idmsg', id)
    })
    socket.on('idDel', (id) => {
        socket.broadcast.emit('idDel', id)
    })
    socket.on('usernameAdd', ({ sender, receiver }) => {
        socket.broadcast.emit('usernameAdd', { sender, receiver })
    })
    socket.on('disconnect', () => {
        console.log('disconntected')
    })
})

server.listen(process.env.PORT, () => {
    console.log(`app is listening on port ${process.env.PORT}`);
})