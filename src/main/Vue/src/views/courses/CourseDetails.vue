<template>
<h1>Course Details Page</h1>
<p>Course id is {{id}}</p>


  
</template>

<script>
export default {
    props:['id'],
    data(){
        return{
            // id:this.$route.params.id
        }
    }

}
</script>

<style>

</style>