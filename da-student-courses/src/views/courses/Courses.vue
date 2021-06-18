<template>
    <div class="mt-5 mb-4 h1"> Course Panel</div>
    
    <p> {{courses.length}} Courses</p>


    <div class="d-flex justify-content-end">
        <div class="custom-control custom-switch">
            <input type="checkbox" class="custom-control-input" id="editSwitch" v-model="editSwitch">
            <label class="custom-control-label" for="editSwitch">Delete Course</label>
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

export default {
    components:{CourseCard},
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
        disenrollCoursebyStudent(courseid)
        {
            // if(confirm("Do you really want to disenroll from this course?"))
            // {
                console.log("To disenroll the course of id"+ courseid)
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
        },
        deleteCoursebyIns(courseid)
        {
            // if(confirm("Do you really want to delete this course?"))
            // {
                console.log("To delete the course of id"+ courseid)
                axios.get('courseInstructor/DeleteCourse/'+ courseid)
                .then(resp => {
                    console.log("The course is deleted")
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
        },
        deleteCourse(courseid)
        {
            if(this.$store.getters.getIsStudent)
            {
                this.disenrollCoursebyStudent(courseid)
            }else
            {
                this.deleteCoursebyIns(courseid)
            }
        }
    }, 
    mounted: function () 
    {
        console.log("The mounted function in courses"+ (this.$store.getters.getIsStudent))
        let url = 'course' + (  (this.$cookies.get('isStudent') == 'true') ? 'Student': 'Instructor' ) + '/getMycourses'
        axios.get(url)
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