<template>
<Nav></Nav>

<div class="container-fluid bg-light">
  <div class="row align-items-start">
    <div v-bind:class="{ 'col-md-8' : $store.getters.isLoggedIn, 'col-md-12' : !$store.getters.isLoggedIn} ">
       <router-view />
    </div>
    <div v-if="$store.getters.isLoggedIn" class="col-md-4 bg-light">
      <TimeTable>
      </TimeTable>
    </div>
  </div>
</div>
</template>


<script>
import Nav from './components/Nav.vue'
import TimeTable from './views/timeitems/TimeTable.vue'
import Messages from './views/messages/Message.vue'

window.axios = require('axios')

export default {
  name:'App',
  components:{Nav, TimeTable, Messages},
  methods: {
    redirect(){ this.$router.push({name:'Home'})},
    back(){this.$router.go(-1)},
    forward(){this.$router.go(1)},
    setUser(info)
    {
      this.$store.dispatch('changeLoginState',true)
      let userinfo = null
      if(this.$cookies.get('isStudent') === 'true')
      {
        this.$store.dispatch('setIsStudent',true)
        userinfo = {
          id:info.studentId,
          number:info.studentNumber,
          email:info.email,
          firstName:info.firstName,
          lastName:info.lastName,
          password:info.password
          }
      }else
      {
        this.$store.dispatch('setIsStudent',false)
        userinfo = {
          id:info.instructorId,
          number:info.instructorNumber,
          email:info.email,
          firstName:info.firstName,
          lastName:info.lastName,
          password:info.password
          }
      }
      this.$store.dispatch('setUserInfo',userinfo)
    },
     getUser()
    { 
      if(  (this.$cookies.get('isLoggedIn')  ) == 'true' )
      {
        axios.get('welcome/myinfo' +( ( (this.$cookies.get('isStudent') ) == 'true' ? '':'Ins' ) ))
        .then(res =>{
          console.log("The current user is:")
          console.log(res.data)
          this.setUser(res.data)})
        .catch(err => console.log(err))
      }
    }
  },
  mounted:function()
  {
     console.log("The mounted function in the App page is called")
     this.getUser()
  },
  updated: function () 
  {
    console.log("The updated function in the App page is called")
    this.getUser()
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#navbarNavDropdown a {
  color: #2c3e50;
}

#navbarNavDropdown a.router-link-exact-active { 
  color: #3F48CC;
}
</style>
