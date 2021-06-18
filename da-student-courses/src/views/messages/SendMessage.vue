<template>
<div class="rol d-flex justify-content-center ">
        <div class="col-sm-8 p-4">
            <form  @submit.prevent="onSubmit">
                <h3>Send a Message</h3>

                <div class="form-group">
                    <!-- <label class="d-flex flex-row">Text:</label> -->
                    <textarea class="form-control"  rows="4" v-model="text" required ></textarea>
                </div>

                <div class="submit mt-4">
                    <button class="btn btn-lg btn-block btn-login text-uppercase font-weight-bold text-white mb-2" id="submitButton">
                        Send
                    </button>
                    {{receiverId}}
                </div>
            </form>
        </div>
    </div>
   <p>{{text}}</p>
</template>

<script>
export default {
    props:['receiverId'],
    data(){
         return{
             text: ' ',
             receiverId:this.receiverId
         }
     },
     methods: {
         onSubmit(){
            let message = {
                senderId: this.$store.getters.getUserId,
                receiverId:this.receiverId,
                textMessage: this.text,
            }            
            console.log(message)
            axios.post('message/sendMyMessage', message)
            .then(res => {
                console.log(res.data)
                alert("The message is sent!")
            })
            .catch(error => {console.log(error);})
         }
     }
 
 }
</script>

<style>

</style>