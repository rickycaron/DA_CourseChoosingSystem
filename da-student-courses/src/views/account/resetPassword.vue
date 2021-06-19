<template>
<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
        <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <h3 class="login-heading ">Reset your password now!</h3>
                            <div v-if="successMessage" class="alert alert-success">
                                <strong>Success! </strong>{{successMessage}}
                            </div>
                            <form @submit.prevent="submitLoginForm">
                                <div class="form-label-group">
                                    <input type="number" id="sid" class="form-control" placeholder="Id" required autofocus v-model="id">
                                    <label for="sid">Id</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="text" id="fname" class="form-control" placeholder="First Name" required autofocus v-model="firstName">
                                    <label for="fname">First Name</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="text" id="lname" class="form-control" placeholder="Last Name" required v-model="lastName">
                                    <label for="lname">Last Name</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="email" id="em" class="form-control" placeholder="Email address" required v-model="email">
                                    <label for="em">Email address</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="password" class="form-control" placeholder="Password" required v-model="password">
                                    <label for="password">Password</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="paA" class="form-control" placeholder="Password" required v-model="passwordAgain">
                                    <label for="paA">Confirm password</label>
                                </div>
                                <div v-if="errorMessage" class="alert alert-danger">
                                    <strong>Wrong! </strong>{{errorMessage}}
                                </div>
                               <div class="jumbotron bg-light">
                                    <div class="df-switch">
                                        <button @click="toggleState" type="button" class="btn btn-lg btn-toggle" data-toggle="button" aria-pressed="false" autocomplete="off">
                                        <div class="inner-handle"></div>
                                        <div class="handle"></div>
                                        </button>
                                    </div>
                                </div>
                                <p  v-if="isStudent" > I am a student！</p>
                                <p  v-else > I am a course instructor!</p>
                                <button  
                                class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" 
                                @click="submitResetPassword"
                                >Reset Password</button>
                            </form>
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
    data(){
         return{
            id:'',
            firstName:'',
            lastName:'',
            email: '',
            password: '',
            passwordAgain:'',
            isStudent:true,
            successMessage:null,
            errorMessage:null
         }
     },
     methods: {
         showPassword()
         {
             let x = document.getElementById("password");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
         },
         toggleState()
         {
            this.isStudent = !this.isStudent 
         },
         submitResetPassword()
         {

            let info = {
                firstName:this.firstName,
                lastName:this.lastName,
                email: this.email,
                password: this.password
            }   
            if(this.isStudent){
                info.studentId = this.id
            }else{
                info.instructorId = this.id
            }

            console.log(info)
            console.log("is a student?"+ this.isStudent)
            let apiUrl = 'welcome/' + (this.isStudent?'resetStudentPassword':'resetInsPassword')

            axios({
            url: apiUrl,
            data: info,
            headers: { "Content-Type":"application/json;charset=utf-8"},
            method: 'POST',
            withCredentials: true,
            crossDomain: true
            }).then(res => {
                console.log("Reset password request sent!")
                console.log(res.data)
                if(res.data){
                    this.successMessage = "Successfully reset password! Loading..."
                    console.log("Successfully reset password!")
                    this.$store.dispatch('changeLoginState',false)
                    this.$store.dispatch('setUserInfo',{ id:'', email:'',firstName:'',lastName:'',password:'', number:''})
                    this.$router.push({name:'login'})
                }else{
                    this.errorMessage = "The reset password failed. Try again!"
                    console.log("the reset password failed. Try again!")
                }
            }).catch(error => {
                console.log(error);
            })
         }



     }
}
</script>

<style>

</style>