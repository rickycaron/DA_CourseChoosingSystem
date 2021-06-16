<template>

<h1 class="my-5">All Students list </h1>
<div class="list-group ml-4 mr-2">
    <div class="mb-2 list-group-item flex-column align-items-start list-table-head">
        <div class="d-flex w-100 justify-content-between">
            <h4 class="my-1">&nbsp;Number &nbsp;&nbsp;&nbsp;&nbsp;  Name</h4>
            <h4 class="my-1">Email Address &nbsp;</h4>
        </div>
    </div>
    <div v-if="students.length > 0">
            <div v-for="student in students" :key="student.studentId" >
                <StudentItem :studentinfo="student" class="my-1" > 
                </StudentItem>
            </div>
        </div>
    <div v-else>
        <p>No students information is found!</p>
    </div>
</div>

  
</template>

<script>
import StudentItem from './StudentItem.vue'

export default {
    components:{StudentItem},
    data()
    {
        return{
            students:[]
        }
    },
    mounted: function () 
    {
        // axios.get("rest/students")
        // .then(res => {
        //     console.log(res)
        //     console.log("Students are found here!")
        //     console.log(res.data)
        //     this.students = res.data
        // }).catch(err => console.log(err))

        axios({
            url: 'rest/students',
            headers: {"Content-Type":"application/json;charset=utf-8"},
            method: 'GET',
            withCredentials: true,
            crossDomain: true
            }).then(res => {
                console.log("Students information Got!")
                console.log(res.data)
                this.students = res.data
            }).catch(error => {
                console.log(error);})

    }
}
</script>

<style>


</style>