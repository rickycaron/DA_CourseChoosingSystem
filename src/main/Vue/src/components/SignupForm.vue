 <template>
   <form>
       <label >Email:</label>
       <input type="email" required v-model="email">

       <label >Password:</label>
       <input type="password" required v-model="password">

       <label >Role:</label>
       <select v-model="role">
           <option value="developer">Web Developer</option>
           <option value="designer">Web Designer</option>
       </select>

       <div>
           <input type="checkbox" required v-model="terms">
           <label>Accept terms and conditions</label>
       </div>


   </form>
 </template>
 
 <script>
 export default {
     data(){
         return{
             email: ' ',
             password :' ',
             role: 'developer',
             terms:false
         }
     }
 
 }
 </script>
 
 <style>
 
 </style>