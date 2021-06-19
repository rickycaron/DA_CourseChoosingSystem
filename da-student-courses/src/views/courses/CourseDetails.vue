<template>
<h1>Course Details Page</h1>

<div v-if="course != null"> 
    <!-- <p>{{course.firstName}}</p>
    <p>{{course.lastName}}</p> -->

    <div class="rol d-flex justify-content-center ">
        <div class="col-sm-8 p-4">
            <div class="form-group">
                <label class="d-flex flex-row">Course Name:</label>
                <input type="text" class="form-control" v-model="course.name" disabled>
            </div>
            <div class="form-group">
                <label class="d-flex flex-row">Teacher Name:</label>
                <input type="text" class="form-control"  :value="teacherName" disabled>
            </div>
            <div class="form-group">
                <label class="d-flex flex-row">Email:</label>
                <input type="text" class="form-control" :value="teacher.email" disabled>
            </div>
            <div class="form-group">
                <label class="d-flex flex-row">Week Day:</label>
                <select class="form-control" v-model="course.weekDay" disabled>
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
                <input type="text" class="form-control" v-model="course.type" disabled>  
            </div>
            <label class="d-flex flex-row" >Time for the lecture:</label>
            <div class="form-row d-flex justify-content-between">
                <div class="form-group col-md-5">
                    <input type="text" class="form-control" v-model="course.beginTime" disabled>  
                </div>
            -
                <div class="form-group col-md-5">
                    <input type="text" class="form-control" v-model="course.endTime" disabled>
                </div>
            </div>
            <div class="form-group">
                <label class="d-flex flex-row">Course Detail:</label>
                <textarea class="form-control"  rows="3" v-model="course.description" disabled ></textarea>
            </div>
        </div>
    </div>
</div>
<div v-else>
    <p>Loading course detail</p>
</div>


  
</template>

<script>
export default {
    props:['id'],
    data(){
        return{
            course:null,
            teacher:null,
            teacherName: ''
        }
    },
    mounted: async function()
    {
        await axios.get("courseStudent/getCourseById/" + this.id)
        .then( res => {
            this.course = res.data
            console.log( res.data)
            })
        .catch(err => console.log(err) )

        await axios.get('instructorRest/instructor/' + (this.course.instructorId))
        .then(res => {
            console.log(res.data)
            this.teacher = res.data
            this.teacherName = res.data.firstName +' ' + res.data.lastName
        }).catch(err => console.log(err) )
    },
    computed:{
        getTeacherName(){
            return (this.teacher.firstName + ' ' + this.teacher.lastName)
        }
    }
    // updated: function()
    // {
    //     axios.get("courseStudent/getCourseById/" + this.id)
    //     .then( res => this.course = res.data)
    //     .catch(err => console.log(err) )   
    // }
}
</script>

<style>

</style>