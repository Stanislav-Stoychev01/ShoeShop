import Vue from 'vue'
import Vuex from 'vuex'
import ShoeService from '@/services/shoes-pages service'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    perPage: 5,
    currentPage: 1,
    filter: '',
    availableShoes: [],
    totalPages: '',
    totalElements: '',
    foundShoe: ''
  },
  mutations: {
    SET_AVAILABLE_SHOES (state, data) {
      state.availableShoes = data
    },
    SET_TOTAL_PAGES (state, data) {
      state.totalPages = data
    },
    SET_TOTAL_ELEMENTS (state, data) {
      state.totalElements = data
    },
    SET_FILTER (state, data) {
      state.filter = data
    },
    SET_FOUND_SHOE (state, data) {
      state.foundShoe = data
    }
  },
  actions: {
    async importData ({ commit, getters }) {
      const params = {}
      params.perPage = getters.perPage
      params.currentPage = getters.currentPage
      params.filter = getters.filter
      const res = await ShoeService.getShoePages(params)
      commit('SET_AVAILABLE_SHOES', res.data.availableShoes)
      commit('SET_TOTAL_ELEMENTS', res.data.totalElements)
      commit('SET_TOTAL_PAGES', res.data.totalPages)
    },
    async importFoundShoe ({ commit }, param) {
      const res = await ShoeService.getShoeItem(param)
      commit('SET_FOUND_SHOE', res.data)
    }
  },
  modules: {
  },
  getters: {
    perPage (state) {
      return state.perPage
    },
    currentPage (state) {
      return state.currentPage
    },
    filter (state) {
      return state.filter
    }
  }
})
