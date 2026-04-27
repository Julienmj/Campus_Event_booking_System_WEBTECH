<template>
  <div class="min-h-screen bg-gray-50">
    <header class="bg-blue-700 text-white shadow">
      <div class="max-w-5xl mx-auto px-4 py-4">
        <h1 class="text-2xl font-bold tracking-tight">AUCA Campus Events</h1>
      </div>
    </header>
    <main class="max-w-5xl mx-auto px-4 py-8">
      <RouterView />
    </main>
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router'
</script>
