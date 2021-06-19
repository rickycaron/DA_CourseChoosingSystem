<template>
    <div  class="list-group-item list-group-item-action bg-white " aria-current="true">
        <!-- here it can add an active -->
        <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">{{textMessage}} </h5>
            <small>{{getDate}}</small>
        </div>
    </div>   
</template>

<script>
export default {
    props: ['message'],
    data()
    {
        return{
            senderId: this.message.senderId,
            receiverId:this.message.receiverId,
            textMessage: this.message.textMessage,
            timestamp:this.message.date,
        }
    }, 
    computed:{
        getDate()
        {
            let date = new Date(this.timestamp)
            return( date.getDate() + "/" + (date.getMonth()+1) + "/"+date.getFullYear());
        }
    }

}
</script>

<style>

</style>