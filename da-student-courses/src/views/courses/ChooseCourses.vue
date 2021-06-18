<template>
    <div class="mt-5 mb-4 h1"> Choosing Course</div>
    
    <p> {{courses.length}} Courses</p>


    <div class="d-flex justify-content-end">
        <div class="custom-control custom-switch">
            <input type="checkbox" class="custom-control-input" id="editSwitch" v-model="editSwitch">
            <label class="custom-control-label" for="editSwitch">Choose Courses Switch</label>
        </div>
    </div>

    
    <div v-if="courses.length">
        <div class="card-deck d-flex justify-content-center">
            <div v-for="course in courses" :key="course.courseId" >
                <ChoosingCourseCard :courseinfo="course" :ableToDelete = "editSwitch"  @delete-a-Course="deleteCourse">
                </ChoosingCourseCard>
            </div>
        </div>
    </div>
    <div v-else>
        <p>Loading page</p>
    </div>

</template>



<script>
import ChoosingCourseCard from './ChoosingCourseCard.vue'

export default {
    components:{ChoosingCourseCard},
    data()
    {
        return{
            courses:[],
            editSwitch:true
        }
    },
    methods: 
    {
        deleteCourse(courseid)
        {
            // if(confirm("Do you really want to disenroll from this course?"))
            // {
                console.log("To delete the course of id"+ courseid)
                axios.get('courseStudent/disenrollCourse/'+ courseid)
                .then(resp => {
                    console.log("The course is disenrolled")
                    console.log(resp.data)
                    this.courses = this.courses.filter(function(course, index, arr)
                    { 
                        return course.courseId != courseid;
                    });
                })
                .catch(error => {
                    console.log(error);
                })
            // }
        }
    }, 
    mounted: function () 
    {
        axios.get('courseStudent/getMyAvailableCourses')
        .then(res => {
            console.log(res.data)
            this.courses = res.data
        })
        .catch(err => console.log(err) )
    }
}
</script>

<style>

</style>