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
                      <p class="text-secondary mb-1">KU Leuven Student </p>
                      <p class="text-muted font-size-sm">{{this.email}}</p>
                      <button class="btn list-table-head">Add Friend</button>
                      <button class="btn rui-button-style">Message</button>
                    </div>
                  </div>
                </div>
              </div>
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
                  <hr>
                    <div class="col-sm-12">
                      <button v-if="!editable"  @click="ToggleEdit" class="btn btn-primary" >Edit</button>
                      <button v-if="editable"  @click="UpdateUserInfo" class="btn btn-outline-primary" >Save</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    </div>
</template>

<script>
export default {
  props:['id'],
  data()
  {
      return{
        id:this.id,
        email:'',
        firstName:'',
        lastName:'',
        password:'',
        number:'',
        editable:false
      }
  },
  mounted: function () 
  {
    console.log("The mounted function in profile runs")
    console.log(this.id)
      axios.get("rest/student/" + this.id)
      .then(res => {
          console.log(res)
          console.log("Student of id "+ this.id +" are found here!")
          console.log(res.data)
          this.email = res.data.email,
          this.firstName = res.data.firstName ,
          this.lastName = res.data.lastName ,
          this.number = res.data.studentNumber
          })
      .catch(err => console.log(err))
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
    UpdateUserInfo(){
      //update student profile
      this.editable = !this.editable
    },
    setUser(info){
          this.id = info.studentId,
          this.email=info.email,
          this.firstName=info.firstName,
          this.lastName=info.lastName,
          this.password=info.password,
          this.number=info.studentNumber}
  },
  mounted:function()
  {
    //  console.log("The mounted function in the App page is called")
    //   axios.get('welcome/myinfo')
    //   .then(res =>{
    //     console.log("In the begining:" + res.data)
    //      this.setUser(res.data)})
    //   .catch(err => console.log(err))
    axios({url: 'welcome/myinfo',
    headers: { "Content-Type":"application/json;charset=utf-8"},
    method: 'GET',
    withCredentials: true,
    crossDomain: true
    }).then(res => {
        console.log("In the begining:" + res.data)
        this.setUser(res.data)
    }).catch(error => {console.log(error);})
  }


}
</script>

<style>

</style>