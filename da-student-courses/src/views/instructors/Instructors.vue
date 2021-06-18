<template>

<h1 class="my-5">All Instructors</h1>
<div class="list-group ml-4 mr-2">
    <div class="mb-2 list-group-item flex-column align-items-start list-table-head">
        <div class="d-flex w-100 justify-content-between">
            <h4 class="my-1">&nbsp;Number &nbsp;&nbsp;&nbsp;&nbsp;  Name</h4>
            <h4 class="my-1">Email Address &nbsp;</h4>
        </div>
    </div>
    <div v-if="instructors.length > 0">
            <div v-for="instructor in instructors" :key="instructor.instructorId" >
                <InstructorItem :instructortinfo="instructor" class="my-1" > 
                </InstructorItem>
            </div>
        </div>
    <div v-else>
        <p>No instructors information is found!</p>
    </div>
</div>

  
</template>

<script>
import InstructorItem from './InstructorItem.vue'

export default {
    components:{InstructorItem},
    data()
    {
        return{
            instructors:[]
        }
    },
    mounted: function () 
    {
        axios.get("instructorRest/instructors")
        .then(res => {
            console.log("Instructors information Got!!")
                console.log(res.data)
                this.instructors = res.data
            })
        .catch(err => console.log(err))
    }

}
</script>

<style>


</style>