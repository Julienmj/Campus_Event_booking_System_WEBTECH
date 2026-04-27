<template>
  <div class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
    <div class="bg-white rounded-xl shadow-xl p-6 w-full max-w-md mx-4">
      <h2 class="text-xl font-semibold text-gray-800 mb-1">Register for Event</h2>
      <p class="text-sm text-gray-500 mb-4">{{ event.title }}</p>

      <form @submit.prevent="submit">
        <label class="block text-sm font-medium text-gray-700 mb-1" for="name">Your Name</label>
        <input
          id="name"
          v-model="name"
          type="text"
          required
          placeholder="Enter your full name"
          class="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        />

        <p v-if="error" class="text-red-500 text-sm mt-2">{{ error }}</p>
        <p v-if="success" class="text-green-600 text-sm mt-2">Registration successful!</p>

        <div class="flex gap-3 mt-5">
          <button
            type="submit"
            :disabled="loading"
            class="flex-1 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-300 text-white font-medium py-2 rounded-lg transition text-sm"
          >
            {{ loading ? 'Submitting...' : 'Confirm' }}
          </button>
          <button
            type="button"
            @click="$emit('close')"
            class="flex-1 border border-gray-300 hover:bg-gray-50 text-gray-700 font-medium py-2 rounded-lg transition text-sm"
          >
            Cancel
          </button>
        </div>
      </form>
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
    setTimeout(() => emit('close'), 1200)
  } catch (e) {
    error.value = e.message
  } finally {
    loading.value = false
  }
}
</script>
