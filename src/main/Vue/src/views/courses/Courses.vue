<template>
    <div>
        <h1 class="purple mt-5 mb-4">Course Panel</h1>

        <!-- <router-link :to = "{ name: 'CourseDetails'}" class="mt-3 mb-3">
            <button class="btn btn-primary " > Details </button>
        </router-link> -->

    </div>

    <div class="">
        <div class="card-deck d-flex justify-content-center">
            <div v-for="course in courses" :key="course.id" class="">
                <CourseCard :courseinfo="course">

                </CourseCard>
            </div>
        </div>
    </div>


</template>

<script>
import CourseCard from '../../components/CourseCard.vue'


export default {
    components:{CourseCard},
    data(){
        return{
            courses:[
                {courseID:1,courseName:"System Software",courseDetail:"programming in C",teacherID:1,teacherFirstName:"Stef",teacherLastName:"Desmet",teacherNumber:"s0000001"},
                {courseID:2,courseName:"MicroController",courseDetail:"programming in C",teacherID:2,teacherFirstName:"Luk",teacherLastName:"Gerts",teacherNumber:"s0000002"},
                {courseID:3,courseName:"Media Processing",courseDetail:"programming in C++",teacherID:1,teacherFirstName:"Stef",teacherLastName:"Desmet",teacherNumber:"s0000001"},
                {courseID:4,courseName:"Machine Learning",courseDetail:"machine learning",teacherID:3,teacherFirstName:"Bart",teacherLastName:"Vanrumste",teacherNumber:"s0000003"},
                {courseID:5,courseName:"UXWD Web Desisn",courseDetail:"designing websites in PHP with Codeignitor",teacherID:4,teacherFirstName:"Vero",teacherLastName:"Vanden Abeele",teacherNumber:"s0000004"},
                {courseID:6,courseName:"Object Oriented Programming",courseDetail:"programming in Java",teacherID:5,teacherFirstName:"Koen",teacherLastName:"Pelsmaekers",teacherNumber:"s0000005"},
                {courseID:7,courseName:"Software Development",courseDetail:"programming in Java",teacherID:5,teacherFirstName:"Koen",teacherLastName:"Pelsmaekers",teacherNumber:"s0000005"}
            ]
        }
    }
}
</script>

<style>

</style>