import { createRouter, createWebHistory } from 'vue-router'
import EventsView from '../views/EventsView.vue'

const routes = [
  { path: '/', redirect: '/events' },
  { path: '/events', component: EventsView },
]

export default createRouter({
  history: createWebHistory(),
  routes,
})
