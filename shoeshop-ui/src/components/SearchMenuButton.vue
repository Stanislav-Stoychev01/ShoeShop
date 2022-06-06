<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group md-7">
        <input
          type="text"
          class="form-control"
          placeholder="Search by title"
          :value="filterOut"
          @input="changedText"
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
  props: {
    filterOut: { type: String, required: true },
    currentPage: { type: Number, required: true }
  },
  data () {
    return {
      filter: ''
    }
  },
  methods: {
    changedText (event) {
      this.filter = event.target.value
      this.$emit('changedText', this.filter)
    },
    ButtonClicked (event) {
      this.$emit('buttonClicked')
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
