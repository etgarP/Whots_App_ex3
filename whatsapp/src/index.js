import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import Site from './site.js'
import image from './chat/pictures/grey.png'

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <React.StrictMode>
    <>
      {/* background */}
      <div id="green-top"></div>
      <img alt="" src={image} className='grey-back' />
      {/* the app */}
      <Site></Site>
    </>
  </React.StrictMode>
)