<template>
  <div class="home">
    <h1>Welcome to the course system </h1>
    <h2>{{$store.getters.getUserFullName}}</h2>
    
  </div>
</template>

<script>

export default {
  name: 'Home',
  props:[],
  data(){
    return{
    }
  },
  components: {
    
  }
}
</script>
