<template>
<div id="app" class="container my-5">
  <h1>My Online Shoe Shop</h1>
  <div class="SearchMenuButton">
    <SearchMenuButton
    :current-page="currentPage"
    :filter-out="filter"
    @changedText = "filter = $event"
    @buttonClicked = "axiosGet(); currentPage = 1"/>
  </div>
  <div class="row">
    <ShoeCardComponent
      v-for="shoes in availableShoes"
      :key = "shoes.id"
      :brand ="shoes.brand"
      :model ="shoes.model"
      :color ="shoes.color"
      :price ="shoes.price"/>
  </div>
  <div class="justify-content-center">
      <b-pagination
        v-model="currentPage"
        :total-rows="totalElements"
        :per-page="perPage"
        first-text="⏮"
        prev-text="⏪"
        next-text="⏩"
        last-text="⏭"
        class="mt-4"
        @change ="ChangePageHandler">
      </b-pagination>
  </div>
</div>
</template>

<script>
import ShoeCardComponent from '@/components/ShoesComponent'
import ShoesPagesService from '@/services/shoes-pages service'
import SearchMenuButton from '@/components/SearchMenuButton'
export default {
  name: 'AvailableShoes',
  data () {
    return {
      totalPages: '',
      availableShoes: [],
      totalElements: '',
      currentPage: 1,
      filter: '',
      perPage: 2
    }
  },
  components: {
    ShoeCardComponent: ShoeCardComponent,
    SearchMenuButton: SearchMenuButton
  },
  methods: {
    getRequestParameters (perPage, currentPage, filter) {
      const parameters = {}
      if (perPage) {
        parameters.perPage = perPage
      }
      if (currentPage) {
        parameters.currentPage = currentPage
      }
      if (filter) {
        parameters.filter = filter
      }
      return parameters
    },
    ChangePageHandler (value) {
      this.currentPage = value
      this.axiosGet()
    },
    ChangePerPageHandler (event) {
      this.perPage = event.target.value
      this.currentPage = 1
    },
    async axiosGet () {
      try {
        const params = this.getRequestParameters(this.perPage, this.currentPage, this.filter)
        const res = await ShoesPagesService.getShoePages(params)
        this.availableShoes = res.data.availableShoes
        this.totalElements = res.data.totalElements
        this.totalPages = res.data.totalPages
        this.currentPage = res.data.currentPage
        console.log(res.data)
      } catch (e) {
        console.log(e)
      }
    }
  },
  created () {
    this.axiosGet()
  }
}
</script>
<style scoped>

</style>
