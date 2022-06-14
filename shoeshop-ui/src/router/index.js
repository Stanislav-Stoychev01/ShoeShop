import Vue from 'vue'
import VueRouter from 'vue-router'
import AvailableShoes from '../views/AvailableShoes'
import ViewShoe from '../views/ViewShoe'

Vue.use(VueRouter)

const routes = [
  {
    path: '/availableShoes',
    name: 'AvailableShoes',
    component: AvailableShoes
  },
  {
    path: '/availableShoes/:id',
    name: 'ViewShoe',
    component: ViewShoe
  }
]

const router = new VueRouter({
  routes
})

export default router
