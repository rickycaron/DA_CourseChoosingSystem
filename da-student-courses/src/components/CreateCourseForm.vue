<template>
    <div class="rol d-flex justify-content-center ">
        <div class="col-sm-8 p-4">
            <form  @submit.prevent="onSubmit">
                <h3>Create a new course</h3>

                <div class="form-group">
                    <label class="d-flex flex-row">Course Name:</label>
                    <input type="text" class="form-control" placeholder="Course Name" v-model="courseName" required>
                </div>

                <div class="form-group">
                    <label class="d-flex flex-row">Week Day:</label>
                    <select class="form-control" v-model="weekDay">
                        <option value=1>Monday</option>
                        <option value=2>Tuesday</option>
                        <option value=3>Wednesday</option>
                        <option value=4>Thurthday</option>
                        <option value=5>Friday</option>
                        <option value=6>Saturday</option>
                        <option value=7>Sunday</option>    
                    </select>
                </div>

                <label class="d-flex flex-row" >Choose a time for the lecture:</label>
                <div class="form-row d-flex justify-content-between">
                    <div class="form-group col-md-5">
                        <input type="time" min="08:00" max="18:00" required  class="form-control" v-model="beginTime">  
                    </div>
                -
                    <div class="form-group col-md-5">
                        <input type="time" :min="beginTime" max="18:00" required  class="form-control" v-model="endTime">
                    </div>
                </div>

                <div class="form-group">
                    <label class="d-flex flex-row">Course Detail:</label>
                    <textarea class="form-control"  rows="3" v-model="courseDetail" required ></textarea>
                </div>

                <div class="submit mt-4">
                    <button class="btn btn-lg btn-block btn-login text-uppercase font-weight-bold text-white mb-2" id="submitButton">
                        Create
                    </button>
                </div>
            </form>
        </div>
    </div>

   <p>{{courseName}}</p>
   <p>{{courseDetail}}</p>
</template>

<script>
 export default {
     data(){
         return{
             courseName: ' ',
             courseDetail: ' ',
             weekDay: 1,
             beginTime: '',
             endTime: ''
         }
     },
     methods: {
         onSubmit(){
             let begin = this.beginTime.split(":")
             let end = this.endTime.split(":")
             if(begin[0] == end[0] && begin[1] > end[1] - 30)
             {
                 alert("Class time should be at least 30 min")
                 return
             }
            let newCourseInfo = {
                courseName: this.courseName,
                courseDetail: this.courseDetail ,
                weekDay: Number(this.weekDay) ,
                beginTime: this.beginTime,
                endTime: this.endTime
            }            
            axios.post('http://localhost:3000/courses/', newCourseInfo)
            .then(res => {
                console.log(res.data)
                if(confirm("New courses added successfully. Add another one?"))
                {
                    window.location.reload()
                    // this.courseName = ''
                    // this.courseDetail = ''
                    // this.weekDay = 1
                    // this.beginTime = ''
                    // this.endTime = ''
                    
                }else{
                     this.$router.push({name:'Courses'})
                }
            })
            .catch(error => {
                console.log(error);
            })
            // this.$emit('create-a-new-course',newCourseInfo) // Use an example for emitting signals
         }
     }
 
 }
</script>

<style>
#submitButton{
    background-color:#3F48CC;
}

</style>


<!-- <form  @submit.prevent="onSubmit">
    <h3>Create a new course</h3>

    <label >Course Name:</label>
    <input type="text" v-model="courseName" required>

    <label >Course Detail:</label>
    <input type="text" v-model="courseDetail" required>

    <label >Week Day:</label>
    <select v-model="weekDay">
        <option value="1">Monday</option>
        <option value="2">Tuesday</option>
        <option value="3">Wednesday</option>
        <option value="4">Thurthday</option>
        <option value="5">Friday</option>
        <option value="6">Saturday</option>
        <option value="7">Sunday</option>           
    </select>


    <label >Choose a time for the lecture:</label>
    <input type="time" min="08:00" max="18:00" required v-model="beginTime">
    -
    <input type="time" min="08:00" max="18:00" required v-model="endTime">
    <small>Office hours are 8am to 6pm</small>

    
    <div class="submit">
        <button>
            Create a course
        </button>
    </div>
</form> -->