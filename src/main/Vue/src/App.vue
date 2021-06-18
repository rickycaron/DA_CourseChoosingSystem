<template>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
  <router-link to="/" class="navbar-brand" >CourseSystem</router-link>

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
      <li class="nav-item">
        <router-link :to="{name:'About'}" class="nav-link" >About</router-link> 
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
    </ul>
 
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search">
      <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
    </form>

    <div class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"> Hello, {{userinformation.firstName}}</a>
      <div class="dropdown-menu dropdown-menu-right">
        <a class="dropdown-item" href="#">Profile</a>
        <a class="dropdown-item" href="#">Friends</a>
        <a class="dropdown-item" href="#">Setting</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#">Log out</a>
      </div>
    </div>
  </div>
</nav>

<div class="container-fluid">
  <div class="row align-items-start">
    <div class="col-md-9 ">
    <CreateCourseForm />

       <router-view />
    </div>
    <div class="col-md-3 bg-secondary">
      Lorem ipsum dolor sit, amet consectetur adipisicing elit. Incidunt nobis nostrum laudantium pariatur, repellendus inventore. Distinctio obcaecati ratione alias vero rerum aliquam accusamus unde quidem. Possimus similique blanditiis iusto perferendis!


    </div>
  </div>
</div>

  <!-- <button @click="redirect">Home</button>
  <button @click="back">Go back</button>
  <button @click="forward">Go forward</button> -->

  <!-- <SignupForm /> -->

  <!-- <router-view :userInfo="userinformation"/> -->
 



</template>

<script>
import SignupForm from './components/SignupForm.vue'
import CreateCourseForm from './components/CreateCourseForm.vue'

export default {
  name:'App',
  components:{SignupForm,CreateCourseForm},
  data() {
    return {
      userinformation:{
        id: 1,
        firstName:"Raphael",
        lastName: "Cousineau",
        email: "raphael.cousineau@student.kuleuven.be",
        isTeacher:false
      }
    }
  },
  methods: {
    redirect(){
      this.$router.push({name:'Home'})
    },
    back(){
      this.$router.go(-1)
    },
    forward(){
      this.$router.go(1)
    }
    
  },
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
