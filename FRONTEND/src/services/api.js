const BASE_URL = 'http://localhost:8080/api'

export async function fetchEvents() {
  const res = await fetch(`${BASE_URL}/events`)
  if (!res.ok) throw new Error('Failed to fetch events')
  return res.json()
}

export async function registerForEvent(name, eventId) {
  const res = await fetch(`${BASE_URL}/registrations`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name, eventId }),
  })
  if (!res.ok) {
    const err = await res.text()
    throw new Error(err || 'Registration failed')
  }
  return res.json()
}
