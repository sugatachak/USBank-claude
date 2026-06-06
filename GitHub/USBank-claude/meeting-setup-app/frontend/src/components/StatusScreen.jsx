import { useState, useEffect } from 'react'
import USBankLogo from './USBankLogo'

export default function StatusScreen({ statusPromise, onBack }) {
  const [status, setStatus] = useState(null)

  useEffect(() => {
    statusPromise
      .then(data => setStatus(data.status))
      .catch(() => setStatus('FAILED'))
  }, [statusPromise])

  const isSuccess = status === 'SCHEDULED'

  return (
    <div className="page">
      <header className="header">
        <USBankLogo />
      </header>
      <main className="card status-card">
        {status === null ? (
          <>
            <div className="spinner" aria-label="Processing request" />
            <p className="status-message">Processing your request…</p>
          </>
        ) : isSuccess ? (
          <>
            <svg width="64" height="64" viewBox="0 0 64 64" fill="none" aria-hidden="true">
              <circle cx="32" cy="32" r="29" stroke="#2E7D32" strokeWidth="3"/>
              <path d="M18 32l10 10 18-20" stroke="#2E7D32" strokeWidth="3"
                strokeLinecap="round" strokeLinejoin="round"/>
            </svg>
            <p className="status-message success">Successfully scheduled</p>
          </>
        ) : (
          <>
            <svg width="64" height="64" viewBox="0 0 64 64" fill="none" aria-hidden="true">
              <circle cx="32" cy="32" r="29" stroke="#CC0000" strokeWidth="3"/>
              <path d="M20 20l24 24M44 20L20 44" stroke="#CC0000" strokeWidth="3" strokeLinecap="round"/>
            </svg>
            <p className="status-message error">Error while Scheduling</p>
          </>
        )}
        {status !== null && (
          <button className="btn-back" onClick={onBack}>Schedule Another Meeting</button>
        )}
      </main>
    </div>
  )
}
