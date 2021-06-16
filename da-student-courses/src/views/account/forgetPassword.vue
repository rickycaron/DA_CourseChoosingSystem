<template>
<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
        <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <h3 class="login-heading mb-4">Forgot passward? </h3>
                            <div v-if="successMessage" class="alert alert-success">
                                <strong>Success! </strong>{{successMessage}}
                            </div>
                            <form @submit.prevent="submitForgotPasswordForm">
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
                                    <input type="text" id="number" class="form-control" placeholder="Number" required v-model="number">
                                    <label for="number">Last Name</label>
                                </div>
                                <div v-if="errorMessage" class="alert alert-danger">
                                    <strong>Warning! </strong>{{errorMessage}}
                                </div>
                                <div class="jumbotron bg-light">
                                    <div class="df-switch">
                                        <button type="button" class="btn btn-lg btn-toggle" data-toggle="button" aria-pressed="false" autocomplete="off">
                                        <div class="inner-handle"></div>
                                        <div class="handle"></div>
                                        </button>
                                    </div>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2">Authenticate</button>
                               <div class="d-flex justify-content-between">
                                    <router-link :to="{name:'login'}" class="small" >Login</router-link> 
                                    <router-link :to="{name:'register'}" class="small" >Register</router-link> 
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
            number:'',
            isStudent:true,
            successMessage:null,
            errorMessage:null}
     },
     methods: {
          toggleState()
         {
            this.isStudent = !this.isStudent 
         },
          showPassword()
         {
             let x = document.getElementById("pa");
             let y = document.getElementById("paA");
            if (x.type === "password") {x.type = "text"; y.type = "text";} 
            else { x.type = "password"; y.type = "password";}
         },
         submitForgotPasswordForm(){
              let userInfo = {
                 firstName:this.firstName,
                 lastName:this.lastName,
                 email: this.email,
                 number: this.number,
             }
             console.log(userInfo)
            let apiUrl = 'welcome/forgetPassword' + (this.isStudent?'Student':'Instructor')
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
             console.log("Password authentication in request sent!")
                console.log(res.data)
                if(res.data){
                    this.successMessage = "Successfully forget password authenticated!"
                    this.$store.dispatch('changeLoginState',true)
                    this.$store.dispatch('setIsStudent',this.isStudent)
                    console.log("forget password authenticated!")
                    this.$router.push({name:'resetPassword'})
                }else{
                    this.errorMessage = "Authenticated falied. Please try again!"
                }
            }).catch(error => {console.log(error); })
         }
            
         
     }
}
</script>

<style>
</style>