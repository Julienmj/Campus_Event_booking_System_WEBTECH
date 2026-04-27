<template>
  <article class="bg-white rounded-xl shadow p-5 flex flex-col gap-3 border border-gray-100 hover:shadow-md transition">
    <div>
      <h2 class="text-lg font-semibold text-gray-800">{{ event.title }}</h2>
      <p class="text-sm text-gray-500 mt-1">📍 {{ event.location }}</p>
      <p class="text-sm text-gray-500">📅 {{ event.date }}</p>
      <p class="text-sm mt-2">
        <span :class="event.availableSeats > 0 ? 'text-green-600' : 'text-red-500'" class="font-medium">
          {{ event.availableSeats > 0 ? `${event.availableSeats} seats available` : 'Fully booked' }}
        </span>
      </p>
    </div>
    <button
      @click="$emit('view', event)"
      class="mt-auto self-start bg-blue-600 hover:bg-blue-700 text-white text-sm font-medium px-4 py-2 rounded-lg transition"
    >
      View & Register
    </button>
  </article>
</template>

<script setup>
defineProps({ event: Object })
defineEmits(['view'])
</script>
