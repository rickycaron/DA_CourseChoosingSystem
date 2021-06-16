<template>
    <div class="mt-5 mb-4 h1"> Course Panel</div>
    
    <p> {{courses.length}} Courses</p>


    <div class="d-flex justify-content-end">
        <div class="custom-control custom-switch">
            <input type="checkbox" class="custom-control-input" id="editSwitch" v-model="editSwitch">
            <label class="custom-control-label" for="editSwitch">Edit Courses</label>
        </div>
    </div>

    
    <div v-if="courses.length">
        <div class="card-deck d-flex justify-content-center">
            <div v-for="course in courses" :key="course.courseId" >
                <CourseCard :courseinfo="course" :ableToDelete = "editSwitch"  @delete-a-Course="deleteCourse">
                </CourseCard>
            </div>
        </div>
    </div>
    <div v-else>
        <p>Loading page</p>
    </div>

</template>



<script>
import CourseCard from './CourseCard.vue'
import CreateCourseForm from '../../components/CreateCourseForm.vue'

export default {
    components:{CourseCard,CreateCourseForm},
    data()
    {
        return{
            courses:[],
            editSwitch:false
        }
    },
    methods: 
    {
        // addCourse(newCourseInfo)
        // {
        //     // console.log("I submit!")
        //     axios.post('http://localhost:3000/courses/', newCourseInfo)
        //     .then(res => console.log(res.data))
        //     .catch(error => {
        //         console.log(error);
        //     })
        //     // console.log(newCourseInfo)
        //     // this.courses.push(newCourseInfo)
        // },
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

            //  this.$confirm("Are you sure to delete it ?").then(() => { });//NOT USEABLE
            // for( let i = 0; i < this.courses.length; i++)
            // {                    
            //     if ( this.courses[i].courseID === courseid)
            //     { 
            //         this.courses.splice(i, 1); 
            //         i--; 
            //     }
            // }
        }
    }, 
    mounted: function () 
    {
        axios.get('courseStudent/getMycourses')
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