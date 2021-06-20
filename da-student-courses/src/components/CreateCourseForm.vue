<template>
    <div class="rol d-flex justify-content-center ">
        <div class="col-sm-8 p-4">
            <form  @submit.prevent="onSubmit">
                <h3>Create a new course</h3>

                <div class="form-group">
                    <label class="d-flex flex-row">Course Name:</label>
                    <input type="text" class="form-control" placeholder="Course Name" v-model="name" required>
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

                 <div class="form-group">
                    <label class="d-flex flex-row">Type:</label>
                    <select class="form-control" v-model="type">
                        <option value='common'>Common</option>
                        <option value='specialization'>Specialization</option>
                        <option value='elective'>Elective</option>  
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
                    <textarea class="form-control"  rows="3" v-model="description" required ></textarea>
                </div>

                <div class="submit mt-4">
                    <button class="btn btn-lg btn-block btn-login text-uppercase font-weight-bold text-white mb-2" id="submitButton">
                        Create
                    </button>
                </div>
            </form>
        </div>
    </div>

   <!-- <p>{{name}}</p>
   <p>{{description}}</p> -->
</template>

<script>
 export default {
     data(){
         return{
             name: ' ',
             description: ' ',
             weekDay: 1,
             beginTime: '',
             endTime: '',
             type:''
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
                instructorId: this.$store.getters.getUserId,
                name: this.name,
                description: this.description ,
                weekDay: Number(this.weekDay) ,
                beginTime: this.beginTime,
                endTime: this.endTime,
                type:this.type
            }            
            axios.post('courseInstructor/AddNewCourse', newCourseInfo)
            .then(res => {
                console.log(res.data)
                if(confirm("New courses added successfully. Add another one?"))
                {
                    // window.location.reload()
                    // this.name = ''
                    // this.description = ''
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
         }
     }
 
 }
</script>

<style>
#submitButton{
    background-color:#3F48CC;
}
</style>