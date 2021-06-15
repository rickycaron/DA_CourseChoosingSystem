<template>
    <h4 class="my-4">Week Class List</h4>
    <div class="list-group mx-2">
        <div v-for="course in courses" :key="course.id" class="my-1">
            <TimeTableItem :courseinfo="course" class="my-1">
            </TimeTableItem>
        </div>
    </div>
  
</template>

<script>
import TimeTableItem from './TimeTableItem.vue'

export default {
    components:{TimeTableItem},
    data()
    {
        return{
            courses:[]
        }
    },
    mounted: function () 
    {
        axios.get('http://localhost:3000/courses')
        .then(res => this.courses = res.data.sort(function compareFn(a, b) 
        {  
            if(a.weekDay < b.weekDay){return -1}
            else if(a.weekDay == b.weekDay)
            {
                let abegin = a.beginTime.split(":")
                let bbegin = b.beginTime.split(":")
                if(abegin[0] < bbegin[0])
                {
                    return -1
                }
                else if(abegin[0] == bbegin[0])
                {
                    if( abegin[1] < bbegin[1])
                    {
                    return -1
                    }
                }
            }
            return 0
        }))
        .catch(err => console.log("No courses found") )
    }
}
</script>


<style>

</style>