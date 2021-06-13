<template>
<p>The use is currenlty loggedin: {{$store.getters.isLoggedIn}} -- 
The user is a student {{$store.getters.getIsStudent}} -- 
User Information: {{$store.getters.getUserInfo}}</p>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
  <router-link to="/" class="navbar-brand" > CourseSystem </router-link>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item">
        <router-link to="/" class="nav-link" >Home <span class="sr-only">(current)</span> </router-link>
      </li>
      <li class="nav-item">
        <router-link :to="{name:'Courses'}" class="nav-link" >Courses</router-link>
      </li>
      <li class="nav-item" v-if="! $store.getters.getIsStudent">
        <router-link :to="{name:'CreateCourse'}" class="nav-link" >Create a Course</router-link> 
      </li>
      <li class="nav-item">
        <router-link :to="{name:'Students'}" class="nav-link" >Students</router-link> 
      </li>
      <li class="nav-item">
        <router-link :to="{name:'Instructors'}" class="nav-link" >Instructors</router-link> 
      </li>
      <li class="nav-item">
        <!-- <router-link v-if=" $store.getters.getIsStudent" :to ="{ name: 'SelfProfile'}" class="nav-link" >Profile{{this.userId}}</router-link> -->
        <a v-if=" $store.getters.getIsStudent" class="nav-link" @click="goToSelfProfile">Profile</a>
      </li>
      <li class="nav-item">
        <router-link :to="{name:'About'}" class="nav-link" >About</router-link> 
      </li>
      <!-- <li class="nav-item">
        <a class="nav-link disabled" aria-disabled="true">Disabled</a>
      </li> -->
    </ul>
 
    <div class="form-inline my-2 my-lg-0 mr-4">
      <input class="form-control mr-sm-2" type="search" placeholder="Search">
      <button class="btn purple-button-secondary my-2 my-sm-0" type="submit">Search</button>
    </div>

    <div v-if="$store.getters.isLoggedIn" class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"> 
        Hello, {{$store.getters.getUserFirstName}}
      </a>
      <div class="dropdown-menu dropdown-menu-right">
        <a class="dropdown-item" href="#">Profile</a>
        <a class="dropdown-item" href="#">Friends</a>
        <a class="dropdown-item" href="#">Setting</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" @click="logOut">Log out</a>
      </div>
    </div>
    <div v-else>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link :to="{name:'login'}" class="nav-link" >Login</router-link> 
        </li>
        <li class="nav-item">
          <router-link :to="{name:'register'}" class="nav-link" >Register</router-link> 
        </li>
      </ul>
    </div>
    

  </div>
</nav>
</template>

<script>
export default {
    name:"Nav",
    methods:{
        logOut()
        {
            axios({
            url: 'welcome/logout',
            headers: { "Content-Type":"application/json;charset=utf-8"},
            method: 'GET',
            withCredentials: true,
            crossDomain: true
            }).then(res => {
              if(res.data){
                console.log("Logout!")
                this.$store.dispatch('changeLoginState',false)
                this.$store.dispatch('setUserInfo',{ id:'', email:'',firstName:'',lastName:'',password:'', number:''})
                this.$router.push({name:'login'})
              }else{
                alert("Log out failed. Please try again!")
              }
            }).catch(error => {console.log(error);})
         },
         goToSelfProfile(){
           if(this.$store.getters.isLoggedIn)
           {
            if(this.$store.getters.getIsStudent)
            {
             this.$router.push({ name: 'Profile', params: { id:this.$store.getters.getUserId,isStudent:this.$store.getters.getIsStudent} }) 
            }else
            {
             this.$router.push({ name: 'Profile', params: { id:this.$store.getters.getUserId } }) 
            }
           }
         }
    }
}
</script>

<style>

</style>