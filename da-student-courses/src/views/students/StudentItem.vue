<template>
  <!-- <router-link 
    :to = "{ name: 'Profile', params: {id:this.id}}" 
    class="mb-1 list-group-item list-group-item-action flex-column align-items-start">
  </router-link> -->
  <div
    @click="goToStudentProfile(id)"
    class="mb-1 list-group-item list-group-item-action flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="my-1">
          &nbsp;
          {{studentNumber}} 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          {{studentFirstName}} {{studentLastName}}</h5>
      <div>
        {{studentEmail}}
      </div>
    </div>
  </div>
</template>

<script>

export default {
    props: ['studentinfo'],
    data()
    {
        return{
            id:this.studentinfo.studentId,
            studentFirstName:this.studentinfo.firstName,
            studentLastName:this.studentinfo.lastName,
            studentEmail:this.studentinfo.email,
            studentNumber:this.studentinfo.studentNumber
        }
    },
    methods:
    {
        goToStudentProfile(studetid)
        {
           if(this.$store.getters.isLoggedIn)
           {       
             this.$router.push({ name: 'Profile', params: { id:studetid, isStudent:true} }) 
           }
        }
    }

}
</script>

<style>

</style>

