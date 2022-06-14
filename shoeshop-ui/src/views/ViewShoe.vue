<template>

  <div>
  <div class="split left">
    <div class="centered">
      <div class="card">
      <img src="../images/blank_image.png" class="card-img-top">
        <div class="card-body">
        <div class="row justify-content-end">
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="split right">
    <div class="centered">
      <h1>{{ brand }}  {{ model }} {{color}}</h1>
      <p>Some text here too.</p>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'ViewShoe',
  computed: {
    id () { return this.$route.params.id },
    brand () { return this.$store.state.foundShoe.brand },
    model () { return this.$store.state.foundShoe.model },
    color () { return this.$store.state.foundShoe.color },
    price () { return this.$store.state.foundShoe.price },
    sizes () { return this.$store.state.foundShoe.sizes }
  },
  created () {
    this.$store.dispatch('importFoundShoe', { id: this.id })
  }
}
</script>

<style scoped>
.split {
  height: 100%;
  width: 50%;
  position: fixed;
  z-index: 1;
  top: 0;
  overflow-x: hidden;
  padding-top: 20px;
}

.left {
  left: 0;
}

.right {
  right: 0;
}

.centered {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
.card{
  width: 400px;
}
</style>
