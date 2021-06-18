import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import NotFound from '../views/NotFound.vue'
import Courses from '../views/courses/Courses'
import CourseDetails from '../views/courses/CourseDetails'
import CreateCourse from '../components/CreateCourseForm.vue'
import loginForm from '../views/account/login.vue'
import Register from '../views/account/register.vue'
import Profile from '../components/Profile.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: loginForm
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/forgetPassword',
    name: 'forgetPassword',
    component: () => import  ('../views/account/forgetPassword.vue')
  },
  {
    path: '/resetPassword',
    name: 'resetPassword',
    component: () => import  ('../views/account/resetPassword.vue')
  },
  {
    path:'/courses',
    name:'Courses',
    component:Courses
  },
  {
    path:'/chooseCourse',
    name:'ChooseCourses',
    component:() => import  ('../views/courses/ChooseCourses')
  },
  { 
    path:'/courses/:id',
    name:'CourseDetails', 
    component:CourseDetails,
    props:true
  },
  { 
    path:'/createCourse',
    name:'CreateCourse', 
    component:CreateCourse,
  },
  { 
    path:'/editCourse/:id',
    name:'EditCourseForm', 
    component:() => import ('../components/EditCourseForm.vue'),
    props:true
  },
  { 
    path:'/students',
    name:'Students', 
    component: () => import  ('../views/students/Students.vue')
  },
  { 
    path:'/instructors',
    name:'Instructors', 
    component: () => import  ('../views/instructors/Instructors.vue')
  },
  // {
  //   path: '/selfProfile',
  //   name: 'SelfProfile',
  //   component: () => import  ('../components/SelfProfile.vue') 
  // },
  { 
    path:'/profile/:id',
    name:'Profile', 
    component:Profile,
    props:true
  },
  {
    path: '/about',
    name: 'About',
    component: About
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
