<template>
<h1>Course Details Page</h1>

<div v-if="course != null">
    <p>Course id is {{id}}</p>
    <p>{{course.courseName}}</p>  
    <p>{{course.courseDetail}}</p> 
    <p>{{course.teacherID}}</p>  
    <p>----------------------------------------------------------</p> 
    <!-- <p>{{teacher.firstName}}</p>
    <p>{{teacher.lastName}}</p> -->

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
            teacher:null
            
        }
    },
    mounted: function()
    {
        //  fetch('http://localhost:3000/courses/' + this.id)
        // .then((res)=> res.json())
        // .then((data) => this.course = data)
        // .catch(err => console.log(err.message)) 

        axios.get("courseStudent/getCourseById/" + this.id)
        .then( res => this.course = res.data)
        .catch(err => console.log(err) )
        
        //  axios.get("http://localhost:3000/teachers/" + this.id)
        // .then(res => this.teacher = res.data)
        // .catch(err => console.log(err) )  
        
        // const url = require('url');
        // let payload = {"courseID": this.id}
        // const params = new URLSearchParams(payload)
        // axios.get(`http://localhost:3000/courses?${params}`)
        // // .then(res => console.log(res.data))
        // .then(res => this.course = res.data)
        // .catch(err => console.log(err) )  
    },
    updated: function()
    {
        axios.get("http://localhost:3000/courses/" + this.id)
        .then( res => this.course = res.data)
        .catch(err => console.log(err) )   
    }
}
</script>

<style>

</style>