<template>
  <div class="col-md-3">
    <div class="card">
      <img src="../images/blank_image.png" :alt="brand + '' + model + '' + color" class="card-img-top">
        <div class="card-body">
          <h1 class="card-title">{{ brand }} {{ model }} {{ color }}</h1>
          <div class="card-text">${{ price }}</div>
          <div class="row justify-content-end">
            <router-link :to="`/availableShoes/${id}`">
              <button class="btn btn-primary" @click="buttonViewItemClicked" >View Item</button>
            </router-link>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ShoeCardComponent',
  props: {
    id: { type: Number, required: true },
    brand: { type: String, required: true },
    model: { type: String, required: true },
    color: { type: String, required: true },
    price: { type: Number, required: true },
    sizes: { type: Array, required: true }
  },
  computed: {
    filter: {
      get () {
        return this.$store.state.filter
      },
      set (value) {
        return this.$store.commit('SET_FILTER', value)
      }
    }
  },
  methods: {
    buttonViewItemClicked () {
      this.filter = ''
    }
  },
  mounted () {
    this.$store.dispatch('importData')
  }
}
</script>
<style scoped>
.col-md-3{
  padding:  10px;
}
.card
{
  justify-content: center;
}

.card-img-top
{
  padding: 8%;
}

</style>
