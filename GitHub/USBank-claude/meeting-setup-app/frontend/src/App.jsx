import { useState } from 'react'
import InviteScreen from './components/InviteScreen'
import StatusScreen from './components/StatusScreen'

export default function App() {
  const [screen, setScreen] = useState('invite')
  const [statusPromise, setStatusPromise] = useState(null)

  function handleSubmit(promise) {
    setStatusPromise(promise)
    setScreen('status')
  }

  function handleBack() {
    setScreen('invite')
    setStatusPromise(null)
  }

  return (
    <>
      {screen === 'invite' && <InviteScreen onSubmit={handleSubmit} />}
      {screen === 'status' && <StatusScreen statusPromise={statusPromise} onBack={handleBack} />}
    </>
  )
}
