<template>

  <div class="card my-4" style="width: 17rem;">
        <!-- <img src="../assets/programminginc.png" class="card-img-top" alt="A photo for a course"> -->
        <img :src = "courseImage" class="card-img-top" alt="A photo for a course">

        <div class="card-body overflow-hidden"  style="height: 13rem" >
          <h5 class="card-title">{{courseName}}</h5>
          <p class="card-subtitle">{{teacherName}}</p>
          course id is: {{courseID}}
          <div class="overflow-hidden" style="height: 10rem">
            <p class="card-text">{{courseDetail}}</p>
            <p class="card-text">Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore sunt optio suscipit nihil, ab quisquam. Dignissimos asperiores, et fugit velit libero aperiam eaque repellendus, ipsa non rem laborum temporibus dolorum.</p>
          </div>

          <!-- <p class="card-text">{{getPhotoPath}}</p> -->
          
        </div>

        <router-link :to = "{ name: 'CourseDetails', params: {id:this.courseID}}" class="mt-3 mb-3">
            <button class="btn btn-primary " > Details </button>
        </router-link>

        <button 
          class="btn btn-danger"
          :hidden="!ableToDelete"
         @click="deleteACourse"> 
         Delete
         </button>

      </div>

</template>


<script>
export default {
    props: ['courseinfo','ableToDelete'],
    data(){
        return{
            // courseID : this.courseinfo.courseID,
            courseID : this.courseinfo.courseId,
            courseName : this.courseinfo.name,
            courseDetail : this.courseinfo.courseDetail,
            teacherID : this.courseinfo.teacherID,
            teacherName : this.courseinfo.teacherFirstName +' '+ this.courseinfo.teacherLastName,
            courseImage:require("../assets/programminginc.png")
        }
    },
    methods: {
      deleteACourse(){
        console.log('Delete course', this.courseID)

        this.$emit('delete-a-Course', this.courseID)
      }
    },
    computed: {
      getPhotoPath()
      {
        let coursedetail = this.courseDetail.toLowerCase()
        let photopath = "../assets/"
        if(coursedetail.includes(' c++')){
          return String(photopath.concat("C++.jpg"))
        }
        else if(coursedetail.includes(" c") ){
          return photopath.concat("programminginc.png")
        }
        else if(coursedetail.includes(' andriod') ){
          return photopath.concat('Android.jpg')
        }
        else if(coursedetail.includes(' java') ){
          return photopath.concat('Java.jpg')
        }
        else{
          return photopath.concat("default.png")
      }
    }
  }


}
</script>

<style>
</style>