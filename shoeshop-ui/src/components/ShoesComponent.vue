<template>
  <div class="col-md-3">
    <div class="card">
      <img src="../images/blank_image.png" :alt="brand + '' + model + '' + color" class="card-img-top">
        <div class="card-body">
          <h2 class="card-title">{{ brand }} {{ model }}</h2>
          <div class="card-text">${{ price }}</div>
          <div class="row justify-content-end">
            <button class="btn btn-primary">Add to cart</button>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ShoeCardComponent',
  props: {
    brand: { type: String, required: true },
    model: { type: String, required: true },
    color: { type: String, required: true },
    price: { type: Number, required: true }
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

</style>
