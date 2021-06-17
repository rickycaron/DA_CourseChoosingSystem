<template>
<h1>Course Details Page</h1>

<div v-if="course != null">
    <p>Course id is {{id}}</p>
    <p>{{course.name}}</p>  
    <p>{{course.description}}</p> 
    <p>{{course.instructorId}}</p>  
    <p>----------------------------------------------------------</p> 
    <!-- <p>{{course.firstName}}</p>
    <p>{{course.lastName}}</p> -->

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
            // teacher:null
        }
    },
    mounted: function()
    {
        axios.get("courseStudent/getCourseById/" + this.id)
        .then( res => this.course = res.data)
        .catch(err => console.log(err) )
    },
    updated: function()
    {
        axios.get("courseStudent/getCourseById/" + this.id)
        .then( res => this.course = res.data)
        .catch(err => console.log(err) )   
    }
}
</script>

<style>

</style>