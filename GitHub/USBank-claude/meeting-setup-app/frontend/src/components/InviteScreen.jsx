import { useState } from 'react'
import USBankLogo from './USBankLogo'

const EMPTY = { agenda: '', date: '', starttime: '', duration: '30', agendaitems: '', restrictions: '' }

export default function InviteScreen({ onSubmit }) {
  const [form, setForm] = useState(EMPTY)
  const [submitting, setSubmitting] = useState(false)
  const [error, setError] = useState(null)

  function handleChange(e) {
    setForm(prev => ({ ...prev, [e.target.name]: e.target.value }))
  }

  function handleCancel() {
    setForm(EMPTY)
    setError(null)
  }

  function handleSubmit(e) {
    e.preventDefault()
    setSubmitting(true)
    setError(null)

    const promise = fetch('/api/meetings', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ ...form, duration: parseInt(form.duration, 10) }),
    })
      .then(res => {
        if (!res.ok) throw new Error('Server error')
        return res.json()
      })
      .catch(() => ({ status: 'FAILED' }))

    onSubmit(promise)
  }

  return (
    <div className="page">
      <header className="header">
        <USBankLogo />
      </header>
      <main className="card">
        <h1 className="screen-title">Schedule a Meeting</h1>
        {error && <div className="error-banner">{error}</div>}
        <form onSubmit={handleSubmit} className="form">
          <div className="field">
            <label htmlFor="agenda">Agenda</label>
            <input id="agenda" name="agenda" type="text" value={form.agenda}
              onChange={handleChange} required />
          </div>

          <div className="field-row">
            <div className="field">
              <label htmlFor="date">Date</label>
              <input id="date" name="date" type="date" value={form.date}
                onChange={handleChange} required />
            </div>
            <div className="field">
              <label htmlFor="starttime">Start Time</label>
              <input id="starttime" name="starttime" type="time" value={form.starttime}
                onChange={handleChange} required />
            </div>
          </div>

          <div className="field">
            <label htmlFor="duration">Duration (minutes)</label>
            <select id="duration" name="duration" value={form.duration} onChange={handleChange}>
              <option value="15">15</option>
              <option value="30">30</option>
              <option value="45">45</option>
              <option value="60">60</option>
            </select>
          </div>

          <div className="field">
            <label htmlFor="agendaitems">Agenda Items</label>
            <textarea id="agendaitems" name="agendaitems" rows={3}
              value={form.agendaitems} onChange={handleChange} />
          </div>

          <div className="field">
            <label htmlFor="restrictions">Restrictions</label>
            <textarea id="restrictions" name="restrictions" rows={2}
              value={form.restrictions} onChange={handleChange} />
          </div>

          <div className="button-row">
            <button type="button" className="btn-cancel" onClick={handleCancel} disabled={submitting}>
              <svg width="18" height="18" viewBox="0 0 18 18" fill="none" aria-hidden="true">
                <circle cx="9" cy="9" r="8" stroke="currentColor" strokeWidth="1.5"/>
                <path d="M5.5 5.5l7 7M12.5 5.5l-7 7" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round"/>
              </svg>
              Cancel
            </button>
            <button type="submit" className="btn-submit" disabled={submitting}>
              <svg width="18" height="18" viewBox="0 0 18 18" fill="none" aria-hidden="true">
                <circle cx="9" cy="9" r="8" stroke="currentColor" strokeWidth="1.5"/>
                <path d="M5.5 9l3 3 4-5" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round"/>
              </svg>
              {submitting ? 'Submitting…' : 'Submit'}
            </button>
          </div>
        </form>
      </main>
    </div>
  )
}
