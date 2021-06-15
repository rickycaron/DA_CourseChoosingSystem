<template>
<div class="container">
    <div class="main-body">
          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                    <div class="mt-3">
                      <h4>{{this.firstName}} {{this.lastName}} </h4>
                      <p class="text-secondary mb-1">KU Leuven {{isStudent?"Student":"Instructor"}} </p>
                      <p class="text-muted font-size-sm">{{this.email}}</p>
                      <button class="btn purple-button-primary">Add Friend</button>
                      <button class="btn purple-button-secondary">Message</button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- <div class="card mt-3">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-globe mr-2 icon-inline"><circle cx="12" cy="12" r="10"></circle><line x1="2" y1="12" x2="22" y2="12"></line><path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"></path></svg>Website</h6>
                    <span class="text-secondary">https://bootdey.com</span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-github mr-2 icon-inline"><path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path></svg>Github</h6>
                    <span class="text-secondary">bootdey</span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-twitter mr-2 icon-inline text-info"><path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"></path></svg>Twitter</h6>
                    <span class="text-secondary">@bootdey</span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-instagram mr-2 icon-inline text-danger"><rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>Instagram</h6>
                    <span class="text-secondary">bootdey</span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-facebook mr-2 icon-inline text-primary"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>Facebook</h6>
                    <span class="text-secondary">bootdey</span>
                  </li>
                </ul>
              </div> -->
            </div>
            <div class="col-md-8 text-left">
              <div class="card mb-3">
                <div class="card-body">
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">First Name</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <span v-if="!editable">{{firstName}}</span>
                      <input v-else type="text" class="form-control" v-model="this.firstName" >
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Last Name</h6>
                    </div>
                    <div class="col-sm-9 text-secondary" >
                      <span v-if="!editable">{{lastName}}</span>
                      <input  v-else type="text" class="form-control" v-model="this.lastName" >
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Email</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <span v-if="!editable">{{email}}</span>
                      <input  v-else type="text" class="form-control" v-model="this.email" >
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Number</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <span v-if="!editable">{{number}}</span>
                      <input  v-else type="text" class="form-control" v-model="this.number">
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Mobile</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      (320) 380-4539
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Address</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      Bay Area, San Francisco, CA
                    </div>
                  </div>
                  <div class="row" v-if=" id == $store.getters.getUserId" >
                  
                    <div class="col-sm-12">
                      <hr>
                      <button v-if="!editable"  @click="ToggleEdit" class="btn purple-button-primary col-sm-2" > Edit </button>
                      <button v-if="editable"  @click="UpdateUserInfo" class="btn purple-button-secondary col-sm-2" >Save</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    </div>
    {{"The id is " + this.$route.params.id}}
    {{"is a student " + this.$route.params.isStudent}}

</template>

<script>
export default {
  props:['id','isStudent'],
  data()
  {
      return{
        id:this.id,
        isStudent:this.isStudent,
        email:'',
        firstName:'',
        lastName:'',
        password:'',
        number:'',
        editable:false
      }
  },
  methods:{
    ToggleEdit(){
      if(this.id == this.$store.getters.getUserId){
        this.editable = ! this.editable
      }
      else{
        this.editable=false
      }
    },
    setUpProfilePage(){
      console.log(this.$route.params)
      console.log("The id in the profile page: " + this.id + "is a student?" + this.isStudent )
      let url = "rest/" + ( this.isStudent ? "student":"instructor") +"/"+ this.id
        // axios.get("rest/student/" + this.id)
        axios.get(url)
        .then(res => {
            console.log(res)
            console.log("Student of id "+ this.id +" are found here!")
            console.log(res.data)
            this.email = res.data.email,
            this.firstName = res.data.firstName ,
            this.lastName = res.data.lastName ,
            this.number = res.data.studentNumber
            }).catch(err => console.log(err))
    },
    UpdateUserInfo(){
      //update user profile
      this.editable = !this.editable
    }
  },
  mounted: function () 
  {
    console.log("The mounted function in profile runs")
    this.setUpProfilePage()
  },
  updated:function()
  {
    console.log("The mounted function in profile runs")
    this.setUpProfilePage()
  }
}
</script>

<style>

</style>