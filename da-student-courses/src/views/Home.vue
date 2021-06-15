<template>
  <div class="home">
    <h1>Welcome to the course system {{firstName}} {{lastName}}</h1>
    
  </div>
</template>

<script>

export default {
  name: 'Home',
  props:[],
  data(){
    return{
      // id: userInfo.id,
      // firstName: userInfo.firstName,
      // lastName: userInfo.lastName,
      // email: userInfo.email,
      // isTeacher:false
    }
  },
  components: {
    
  }
}
</script>
