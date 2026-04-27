<template>
  <div class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
    <div class="bg-white rounded-xl shadow-xl p-6 w-full max-w-md mx-4">

      <!-- Event Details -->
      <h2 class="text-xl font-bold text-gray-800 mb-1">{{ event.title }}</h2>
      <div class="text-sm text-gray-500 space-y-1 mb-4">
        <p>📍 {{ event.location }}</p>
        <p>📅 {{ event.date }}</p>
        <p>
          <span :class="event.availableSeats > 0 ? 'text-green-600' : 'text-red-500'" class="font-medium">
            {{ event.availableSeats > 0 ? `${event.availableSeats} seats available` : 'Fully booked' }}
          </span>
        </p>
      </div>

      <hr class="mb-4" />

      <!-- Registration Form -->
      <template v-if="event.availableSeats > 0">
        <h3 class="text-base font-semibold text-gray-700 mb-3">Register for this event</h3>
        <form @submit.prevent="submit">
          <label class="block text-sm font-medium text-gray-700 mb-1" for="name">Full Name</label>
          <input
            id="name"
            v-model="name"
            type="text"
            required
            placeholder="Enter your full name"
            class="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 mb-3"
          />

          <p v-if="error" class="text-red-500 text-sm mb-2">{{ error }}</p>
          <p v-if="success" class="text-green-600 text-sm mb-2">✅ Registration successful!</p>

          <div class="flex gap-3">
            <button
              type="submit"
              :disabled="loading || success"
              class="flex-1 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-300 text-white font-medium py-2 rounded-lg transition text-sm"
            >
              {{ loading ? 'Submitting...' : 'Confirm Registration' }}
            </button>
            <button
              type="button"
              @click="$emit('close')"
              class="flex-1 border border-gray-300 hover:bg-gray-50 text-gray-700 font-medium py-2 rounded-lg transition text-sm"
            >
              Close
            </button>
          </div>
        </form>
      </template>

      <button
        v-else
        @click="$emit('close')"
        class="w-full border border-gray-300 hover:bg-gray-50 text-gray-700 font-medium py-2 rounded-lg transition text-sm"
      >
        Close
      </button>

    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { registerForEvent } from '../services/api.js'

const props = defineProps({ event: Object })
const emit = defineEmits(['close', 'registered'])

const name = ref('')
const loading = ref(false)
const error = ref('')
const success = ref(false)

async function submit() {
  error.value = ''
  loading.value = true
  try {
    await registerForEvent(name.value, props.event.eventId)
    success.value = true
    emit('registered', props.event.eventId)
    setTimeout(() => emit('close'), 1500)
  } catch (e) {
    error.value = e.message
  } finally {
    loading.value = false
  }
}
</script>
