<template>

  <div
    @click="goToStudentProfile(id)"
    class="mb-1 list-group-item list-group-item-action flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="my-1">
          &nbsp;
          {{instructorNumber}} 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          {{instructorFirstName}} {{instructorLastName}}</h5>
      <div>
        {{instructorEmail}}
      </div>
    </div>
  </div>

</template>

<script>

export default {
    props: ['instructortinfo'],
    data()
    {
        return{
            id:this.instructortinfo.studentId,// HERE
            instructorFirstName:this.instructortinfo.firstName,
            instructorLastName:this.instructortinfo.lastName,
            instructorEmail:this.instructortinfo.email,
            instructorNumber:this.instructortinfo.studentNumber
        }
    },
     methods:
    {
        goToInstructorProfile(instructorid)
        {
           if(this.$store.getters.isLoggedIn)
           {       
             this.$router.push({ name: 'Profile', params: { id:instructorid, isStudent:false} }) 
           }
        }
    }

}
</script>

<style>

</style>

