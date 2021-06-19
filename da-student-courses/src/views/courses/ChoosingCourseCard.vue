<template>

  <div class="card my-4" style="width: 17rem;">
        <img :src = "courseImage" class="card-img-top" alt="A photo for a course">

        <div class="card-body overflow-hidden"  style="height: 13rem" >
          <h5 class="card-title">{{courseName}}</h5>
          <br>
          <p class="card-subtitle">{{teacherName}}</p>
          Course Id: {{courseID}}
          <div class="overflow-hidden" style="height: 10rem">
            <p class="card-text">{{courseDetail}}</p>
          </div>
        </div>

        <router-link :to = "{ name: 'CourseDetails', params: {id:this.courseID}}" class="mt-3 mb-3">
            <button class="btn purple-button-primary " > Details </button>
        </router-link>

        <button 
          class="btn purple-button-secondary"
          :hidden="!ableToDelete"
         @click="enrollACourse"> 
         Enroll
         </button>

      </div>

</template>


<script>
export default {
    props: ['courseinfo','ableToDelete'],
    data(){
        return{
            courseID : this.courseinfo.courseId,
            courseName : this.courseinfo.name,
            courseDetail : this.courseinfo.description,
            instructorId : this.courseinfo.instructorId,
            teacherName : this.courseinfo.teacherFirstName +' '+ this.courseinfo.teacherLastName,
            courseImage:require("../../assets/programminginc.png")
        }
    },     
    mounted: function () 
    {
        console.log("The mounted function in course card runs the instrudtor id is "+ (this.courseinfo.instructorId))
        let url = 'instructorRest/instructor/' + (this.courseinfo.instructorId)
        axios.get(url)
        .then(res => {
            console.log(res.data)
            this.teacherName = res.data.firstName +' '+ res.data.lastName
        })
        .catch(err => console.log(err) )
    },
    methods: {
      enrollACourse(){
        if(confirm("Do you really want to disenroll from this course?"))
            {
                console.log("To enroll the course of id"+ this.courseID)
                axios.post('courseStudent/subscribeCourse/'+ this.courseID)
                .then(resp => {
                    console.log("The course is enrolled")
                    console.log(resp.data)
                    this.$router.go(this.$router.currentRoute)
                })
                .catch(error => {console.log(error);})
            }
      }
    }


}
</script>

<style>
</style>