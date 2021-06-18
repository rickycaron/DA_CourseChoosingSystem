<template>
<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
        <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <h3 class="login-heading mb-4">Welcome to Course System!</h3>
                            <div v-if="successMessage" class="alert alert-success">
                                <strong>Success! </strong>{{successMessage}}
                            </div>
                            <form @submit.prevent="submitRegisterForm">
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
                                    <input type="password" id="pa" class="form-control" placeholder="Password" required v-model="password">
                                    <label for="pa">Password</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="paA" class="form-control" placeholder="Password" required v-model="passwordAgain">
                                    <label for="paA">Confirm password</label>
                                </div>
                                <div v-if="errorMessage" class="alert alert-danger">
                                    <strong>Warning! </strong>{{errorMessage}}
                                </div>
                            <p> I am a student: {{isStudent}}</p> 
                            <p>{{firstName}}</p>

                                <!-- <div class="custom-control custom-checkbox mb-3 d-flex justify-content-end">
                                    <input type="checkbox" class="custom-control-input" id="customCheck1" @click="showPassword">
                                    <label class="custom-control-label" for="customCheck1">Show password</label>
                                </div> -->
                                <div class="jumbotron bg-light">
                                    <div class="df-switch">
                                        <button  @click="toggleState" type="button" class="btn btn-lg btn-toggle" data-toggle="button" aria-pressed="false" autocomplete="off">
                                        <div class="inner-handle"></div>
                                        <div class="handle"></div>
                                        </button>
                                    </div>
                                </div>

                                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2">Get started!</button>
                                <!-- <a class="d-block text-center mt-2 small" href="login">Already have an account?</a> -->
                                <div class="d-flex justify-content-between mt-4">
                                    <router-link :to="{name:'forgetPassword'}" class="small" >Forgot password?</router-link> 
                                    <router-link :to="{name:'login'}" class="small" >Already have an account?</router-link> 
                                </div>
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
            firstName:'',
            lastName:'',
            email: '',
            password: '',
            passwordAgain:'',
            isStudent:true,
            successMessage:null,
            errorMessage:null}
     },
     methods: {
          toggleState()
         {
            this.isStudent = !this.isStudent 
            this.firstName = ''
            this.lastName  = ''
            this.email = ''
            this.password = ''
            this.passwordAgain = ''
         },
          showPassword()
         {
             let x = document.getElementById("pa");
             let y = document.getElementById("paA");
            if (x.type === "password") {x.type = "text"; y.type = "text";} 
            else { x.type = "password"; y.type = "password";}
         },
         submitRegisterForm(){
            if( ! (this.password === this.passwordAgain) )
            {
                this.errorMessage = "The passwords don't match"
            }else
            {
                let userInfo = {
                    firstName:this.firstName,
                    lastName:this.lastName,
                    email: this.email,
                    password: this.password
                    }
                console.log(userInfo)
                let apiUrl = 'welcome/' + (this.isStudent?'registerStudent':'instructor') //
                axios({
                url: apiUrl,
                data: userInfo,
                headers: {
                    "Content-Type":"application/json;charset=utf-8"
                },
                method: 'POST',
                withCredentials: true,
                crossDomain: true
                }).then(res => {
                console.log("Register in request sent!")
                    console.log(res.data)
                    if(res.data){
                        this.successMessage = "Successfully registered!",
                        console.log("Successfully Registered!")
                        this.$router.push({name:'login'})
                    }else{
                        console.log("Register failed please try again")
                        this.errorMessage = "Registration falied. The email may have been taken. Please try again!"
                    }
                }).catch(error => {console.log(error);})
         }
            
         }
     }
}
</script>

<style>
</style>