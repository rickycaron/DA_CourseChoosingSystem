<template>
<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
        <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <h3 class="login-heading ">Welcome to Course System!</h3>
                            <div v-if="successMessage" class="alert alert-success">
                                <strong>Success! </strong>{{successMessage}}
                            </div>
                            <form @submit.prevent="submitLoginForm">
                                <div class="form-label-group">
                                    <input type="text" id="email" class="form-control" placeholder="Email address" required autofocus v-model="email">
                                    <label for="email">Email address</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="password" class="form-control" placeholder="Password" required v-model="password">
                                    <label for="password">Password</label>
                                </div>
                                <div v-if="errorMessage" class="alert alert-danger">
                                    <strong>Logged in failed! </strong>{{errorMessage}}
                                </div>
                                <!-- <input type="checkbox" @click="showPassword">Show Password -->
                                <div class="jumbotron bg-light">
                                    <div class="df-switch">
                                        <button @click="toggleState" type="button" class="btn btn-lg btn-toggle" data-toggle="button" aria-pressed="false" autocomplete="off">
                                        <div class="inner-handle"></div>
                                        <div class="handle"></div>
                                        </button>
                                    </div>
                                </div>
                                <p  v-if="isStudent" > I am a student！</p>
                                <p  v-if="!isStudent" > {{isStudent}}I am a course instructor!</p>
                                <button  class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" >Sign in</button>
                                <div class="d-flex justify-content-between">
                                    <router-link :to="{name:'resetPassword'}" class="small" >Forgot password?</router-link> 
                                    <router-link :to="{name:'register'}" class="small" >Register</router-link> 
                                </div>
                            </form>
                            {{this.$store.state.isloggedin}}
                            {{this.$store.state.isStudent}}
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
             email: ' ',
             password: '',
             isStudent:true,
             successMessage:null,
             errorMessage:null
         }
     },
     methods: {
         toggleState()
         {
            this.isStudent = !this.isStudent 
            // this.email= ' ',
            // this.password=''
         },
         showPassword()
         {
             let x = document.getElementById("password");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
         },
         submitLoginForm()
         {

            let info = {
                email: this.email,
                password: this.password
            }   
            console.log(info)
            let apiUrl = 'welcome/login' + (this.isStudent?'Student':'Instructor')
            
            axios({
            url: apiUrl,
            data: info,
            headers: { "Content-Type":"application/json;charset=utf-8" },
            method: 'POST',
            withCredentials: true,
            crossDomain: true
            }).then(res => {
                console.log("Login in request sent!")
                console.log(res.data)
                if(res.data){
                    this.successMessage = "Successfully logged in! Loading..."
                    this.$cookies.set('isLoggedIn','true')
                    this.$cookies.set('isStudent',this.isStudent)
                    this.$store.dispatch('changeLoginState',true)
                    this.$store.dispatch('setIsStudent',this.isStudent)
                    this.$router.push({name:'Courses'})
                }else{
                    this.errorMessage = "The password does't match the email. Or the email doesn't exist."
                    console.log("Not logged in")
                }
            }).catch(error => {
                console.log(error);
                this.errorMessage = " Are you sure you are " + (this.isStudent ? "a student?":"an Instructor")
            })
         }



     }
}
</script>

<style>

</style>