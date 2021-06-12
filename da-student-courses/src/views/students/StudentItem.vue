<template>
  <router-link 
    :to = "{ name: 'Profile', params: {id:this.id}}" 
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
  </router-link>
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

}
</script>

<style>

</style>

