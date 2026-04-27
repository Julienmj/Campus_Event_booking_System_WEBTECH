<template>
  <section>
    <h2 class="text-xl font-semibold text-gray-700 mb-6">Upcoming Events</h2>

    <p v-if="loading" class="text-gray-500">Loading events...</p>

    <p v-else-if="events.length === 0" class="text-gray-400 text-center mt-10">
      No events yet. Create one via Swagger UI at
      <a href="http://localhost:8080/swagger-ui.html" target="_blank" class="text-blue-500 underline">
        http://localhost:8080/swagger-ui.html
      </a>
    </p>

    <div v-else class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5">
      <EventCard
        v-for="event in events"
        :key="event.eventId"
        :event="event"
        @view="openDetail"
      />
    </div>

    <EventDetailModal
      v-if="selectedEvent"
      :event="selectedEvent"
      @close="selectedEvent = null"
      @registered="onRegistered"
    />
  </section>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import EventCard from '../components/EventCard.vue'
import EventDetailModal from '../components/EventDetailModal.vue'
import { fetchEvents } from '../services/api.js'

const events = ref([])
const loading = ref(true)
const selectedEvent = ref(null)

onMounted(async () => {
  try {
    events.value = await fetchEvents()
  } finally {
    loading.value = false
  }
})

function openDetail(event) {
  selectedEvent.value = event
}

function onRegistered(eventId) {
  const ev = events.value.find(e => e.eventId === eventId)
  if (ev) ev.availableSeats--
}
</script>
