import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import NotFound from '../views/NotFound.vue'
import Courses from '../views/courses/Courses'
import CourseDetails from '../views/courses/CourseDetails'





const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path:'/courses',
    name:'Courses',
    component:Courses
  },
  { 
    path:'/courses/:id',
    name:'CourseDetails', 
    component:CourseDetails,
    props:true
  },
  {
    path:'/all-courses',
    redirect:'/courses'
  },
  //catchall:404, all the cases ahead are not met
  {
    path:'/:catchAll(.*)',
    name:'NotFound',
    component:NotFound
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
