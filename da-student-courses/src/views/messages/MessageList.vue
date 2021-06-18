<template>
<div class="d-flex justify-content-between mt-5">
    <div class=" mb-3 h4"> Message Board</div>
    <p> {{messages.length}} Messages <i class="far fa-envelope"></i></p>
</div>    
    <div v-if="messages.length">
        <div class="list-group mx-2">
            <div v-for="message in messages" :key="message.messageID" >
                <Message :message="message" class="my-1">
                </Message>
            </div>
        </div>
    </div>
</template>

<script>
import Message from './Message.vue'

export default {
    components:{Message},
    data()
    {
        return{
            messages:[]
        }
    },
    mounted: function () 
    {
        console.log("The mounted function in messages")
        axios.get('message/getMyMessages')
        .then(res => {
            console.log(res.data)
            this.messages = res.data
        })
        .catch(err => console.log(err) )
    }

}
</script>

<style>
</style>