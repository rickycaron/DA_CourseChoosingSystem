<template>
  <div class="about">
    <h1>This is the about page</h1>
    <h3>Today logged in users</h3>
    <p>{{loggedinUsers}}</p>
  </div>
</template>

<script>

export default {
    data()
    {
        return{
            loggedinUsers:[]
        }
    },
    mounted: function () 
    {
        let url = 'hello/SingletonGetLoggedInstructors'
        axios.get(url)
        .then(res => this.loggedinUsers = res.data )
        .catch(err => console.log("No courses found") )
    }
}
</script>


