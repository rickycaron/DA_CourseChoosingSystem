<template>

    <h4 class="my-4">Message List</h4>
    <div class="list-group mx-2">
        <div v-for="message in messages" :key="message.messageID" >
            <Message :message="message" class="my-1">
            </Message>
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
            messages:[
                {messageID:1, sender:"t000001", receiver:"s000001",content:"Is this course difficult?"},
            ]
        }
    }

}
</script>

<style>

</style>