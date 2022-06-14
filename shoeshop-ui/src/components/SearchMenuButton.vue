<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group md-7">
        <input
          v-model="filter"
          type="text"
          class="form-control"
          placeholder="Search by title"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="ButtonClicked">
            Search
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchMenuButton',
  computed: {
    filter: {
      get () { return this.$store.state.filter },
      set (value) { return this.$store.commit('SET_FILTER', value) }
    },
    currentPage () { return this.$store.state.currentPage }
  },
  methods: {
    ButtonClicked () {
      this.$store.dispatch('importData')
    }
  }
}
</script>

<style scoped>
.col-md-8{
  padding: 25px;
}
.input-group-append{
  padding-left: 7px;
}
.row
{
  justify-content: center;
}
</style>
