<template>
<div id="app" class="container my-5">
  <h1>My Online Shoe Shop</h1>
  <div class="SearchMenuButton">
    <SearchMenuButton/>
  </div>
  <div class="row">
    <ShoeCardComponent
      v-for="shoes in availableShoes"
      :key = "shoes.id"
      :id = "shoes.id"
      :brand ="shoes.brand"
      :model ="shoes.model"
      :color ="shoes.color"
      :price ="shoes.price"
      :sizes ="shoes.sizes"/>
  </div>
  <div>
      <b-pagination
        v-model="currentPage"
        :total-rows="totalElements"
        :per-page="perPage"
        first-text="⏮"
        prev-text="⏪"
        next-text="⏩"
        last-text="⏭"
        class="mt-4"
        align="center"
        @change ="ChangePageHandler">
      </b-pagination>
  </div>
</div>
</template>

<script>
import ShoeCardComponent from '@/components/ShoesComponent'
import SearchMenuButton from '@/components/SearchMenuButton'
export default {
  name: 'AvailableShoes',
  components: {
    ShoeCardComponent: ShoeCardComponent,
    SearchMenuButton: SearchMenuButton
  },
  computed: {
    currentPage () { return this.$store.state.currentPage },
    availableShoes () { return this.$store.state.availableShoes },
    filter: {
      get () { return this.$store.state.filter },
      set (value) { return this.$store.commit('SET_FILTER', value) }
    },
    perPage () { return this.$store.state.perPage },
    totalElements () { return this.$store.state.totalElements }
  },
  methods: {
    ChangePageHandler (value) {
      this.currentPage = value
    },
    ChangePerPageHandler (event) {
      this.perPage = event.target.value
      this.currentPage = 1
    },
    redirectPage () {
      this.$router.push('/shoe')
    }
  },
  created () {
    this.$store.dispatch('importData')
  }
}
</script>
<style scoped>

</style>
