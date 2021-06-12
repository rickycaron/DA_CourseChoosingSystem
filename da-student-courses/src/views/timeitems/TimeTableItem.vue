<template>

<router-link :to = "{ name: 'CourseDetails', params: {id:this.courseID}}">
    <div  class="list-group-item list-group-item-action bg-light " aria-current="true">
        <!-- here it can add an active -->
        <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">{{courseName}} </h5>
            <small>{{weekDayRepresent}} {{timePresent(beginTime)}} - {{timePresent(endTime)}}</small>
        </div>
    </div>   
</router-link>

    <!-- <a href="#" class="list-group-item list-group-item-action bg-light " aria-current="true">
        here it can add an active
        <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">{{courseName}} </h5>
            <small>{{weekDayRepresent}} {{timePresent(beginTime)}} - {{timePresent(endTime)}}</small>
        </div>
        <small></small>
    </a> -->
    
</template>

<script>
export default {
    props: ['courseinfo'],
    data()
    {
        return{
            courseID : this.courseinfo.id,
            courseName : this.courseinfo.courseName,
            courseDetail : this.courseinfo.courseDetail,
            teacherID : this.courseinfo.teacherID,
            teacherName : this.courseinfo.teacherFirstName +' '+ this.courseinfo.teacherLastName,
            // courseImage:require("../assets/programminginc.png"),
            weekDay: this.courseinfo.weekDay,
            beginTime: this.courseinfo.beginTime, 
            endTime: this.courseinfo.endTime
        }
    },
    computed:{
        weekDayRepresent()
        {
            let day = ''
            switch (this.weekDay) {
                case 7:
                    day = "Sun";
                    break;
                case 1:
                    day = "Mon";
                    break;
                case 2:
                    day = "Tue";
                    break;
                case 3:
                    day = "Wed";
                    break;
                case 4:
                    day = "Thu";
                    break;
                case 5:
                    day = "Fri";
                    break;
                case 6:
                    day = "Sat";
            }
            return day
        }
    },
    methods:{
        timePresent(t)
        {
            let time = t.split(":")
            if(time[0] < 12){return (t + "AM")}
            else{return (t+"PM")}
        }
    }
}
</script>

<style>

</style>