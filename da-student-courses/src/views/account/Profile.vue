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
                      <!-- <button class="btn purple-button-primary">Add Friend</button> -->
                      <button class="btn purple-button-secondary" @click="toggleMessage" >Message</button> 
                    </div>
                  </div>
                </div>
              </div>
              <SendMessage v-if=" ( (!( id == $store.getters.getUserId )) && showMessagebutton )" :receiverId="id">
              </SendMessage>
            </div>
            <div class="col-md-8 text-left">
              <div class="card mb-3">
                <div class="card-body">
                  <div v-if="successMessage" class="alert alert-success">
                      <strong>Success! </strong>{{successMessage}}
                  </div>
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
                  
                  <!-- <div class="row">
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
                  </div> -->
                  <div v-if="errorMessage" class="alert alert-danger">
                    <hr>
                    <strong>Warning! </strong>{{errorMessage}}
                  </div>
                  <div class="row" v-if=" ( id == $store.getters.getUserId ) && ( (isStudent) == $store.getters.getIsStudent ) " >
                    <div class="col-sm-12">
                      <hr>
                      <button v-if="!editable"  @click="ToggleEdit" class="btn purple-button-primary col-sm-2" > Edit </button>
                      <button v-if="editable"  @click="UpdateUserInfo" class="btn purple-button-secondary col-sm-2" >Save</button>
                    </div>
                  </div>
                </div>
              </div>
                  <!-- {{"The id is " + this.$route.params.id}}
    {{"is a student " + this.$route.params.isStudent}} -->

              <Messages v-if=" ( id == $store.getters.getUserId )"  >
              </Messages>

            </div>
          </div>
        </div>
    </div>


</template>

<script>
import Messages from '../messages/MessageList.vue'
import SendMessage from '../messages/SendMessage.vue'


export default {
  components:{Messages,SendMessage},
  props:['id','isStudent'],
  data()
  {
      return{
        id:this.id,
        isStudent:(this.isStudent == "true"),
        email:'',
        firstName:'',
        lastName:'',
        password:'',
        number:'',
        editable:false,
        showMessagebutton:false,
        successMessage:null,
        errorMessage:null
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
    toggleMessage(){
      this.showMessagebutton = ! this.showMessagebutton
    },
    setUpProfilePage(){
      console.log(this.$route.params)
      console.log("The id in the profile page: " + this.id + "is a student?" + this.isStudent )
      let url = ""
      if(this.isStudent == "true"){
         url = "rest/student/"+ this.id
      }else{
         url = "instructorRest/instructor/"+ this.id
      }
         axios({
            url: url,
            headers: {"Content-Type":"application/json;charset=utf-8"},
            method: 'GET',
            withCredentials: true,
            crossDomain: true
            })
        .then(res => {
            console.log(res)
            console.log("Student of id "+ this.id +" are found here!")
            console.log(res.data)
            this.email = res.data.email,
            this.firstName = res.data.firstName ,
            this.lastName = res.data.lastName ,
            this.number = ( (this.isStudent == "true") ? res.data.studentNumber : res.data.instructorNumber) 
            }).catch(err => console.log(err))
    },
    UpdateUserInfo(){
      //update user profile
      this.editable = !this.editable

      let userInfo = {
          firstName:this.firstName,
          lastName:this.lastName,
          email: this.email,
          }
        if(this.isStudent== 'true'){
            userInfo.studentId=this.id
            userInfo.studentNumber=this.studentNumber
        }else{
            userInfo.instructorId=this.id
            userInfo.instructorNumber=this.instructorNumber
        }
      let apiurl =( this.isStudent == 'true' ? "rest/student":"instructorRest/UpdateInsInfo")
      console.log(this.isStudent)
      console.log(userInfo)
      console.log(apiurl)

         axios({
            url: apiurl,
            headers: {"Content-Type":"application/json;charset=utf-8"},
            data: userInfo,
            method: 'Put',
            withCredentials: true,
            crossDomain: true
            })
        .then(res => {
            console.log(res)
            this.successMessage = "Successfully updated!",
            console.log(res.data)
            this.email = res.data.email,
            this.firstName = res.data.firstName ,
            this.lastName = res.data.lastName ,
            this.number = res.data.studentNumber
            }).catch(err => console.log(err))
    }
  },
  mounted: function () 
  {
    console.log("The mounted function in profile runs")
    this.setUpProfilePage()
  },
  updated:function()
  {
    console.log("The updated function in profile runs")
    // this.setUpProfilePage()
  }
}
</script>

<style>

</style>