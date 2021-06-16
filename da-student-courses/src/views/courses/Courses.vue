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
            <div v-for="course in courses" :key="course.id" >
                <CourseCard :courseinfo="course" :ableToDelete = "editSwitch"  @delete-a-Course="deleteCourse">
                </CourseCard>
            </div>
        </div>
    </div>
    <div v-else>
        <p>Loading page</p>
    </div>
    

    <!-- <CreateCourseForm @create-a-new-course ="addCourse">
    </CreateCourseForm> -->

</template>



<script>
import CourseCard from '../../components/CourseCard.vue'
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
            if(confirm("Do you really want to delete this course?"))
            {
                axios.delete('http://localhost:3000/courses/'+ courseid + '/')
                .then(resp => {
                    this.courses = this.courses.filter(function(course, index, arr)
                    { 
                        return course.id != courseid;
                    });
                })
                .catch(error => {
                    console.log(error);
                })
            }
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
        // fetch('http://localhost:3000/courses')
        // .then((res)=> res.json())
        // .then((data) => this.courses = data)
        // .catch(err => console.log(err.message)) 

        axios.get('courseStudent/getMycourses')
        .then(res => this.courses = res.data)
        .catch(err => console.log(err) )
    }
}
</script>

<style>

</style>