import {createStore} from 'vuex'

export default createStore({
    state:{
        isloggedin:false,
        isStudent:true,
        id:'',
        email:'',
        firstName:'',
        lastName:'',
        password:'',
        number:''
    },
    mutations:{
        changeLoginState(state,loginstate){
            state.isloggedin = loginstate
        },
        setIsStudent(state,isStudentorNot){
            state.isStudent = isStudentorNot
        },
        setUserInfo(state,info){
            state.id = info.id
            state.email = info.email
            state.firstName=info.firstName
            state.lastName=info.lastName
            state.password = info.password
            state.number = info.number
        },
        setUserEmail(state,email){
            state.email = email
        },
        setUserName(state,name){
            state.firstName=name.firstName
            state.lastName=name.lastName
        }
    },
    actions:{
        changeLoginState({commit},theloginstate){
            commit('changeLoginState',theloginstate)
        },
        setIsStudent({commit},isStudentorNot){
            commit('setIsStudent',isStudentorNot)
        },
        setUserInfo({commit},info){
            commit('setUserInfo',info)
        },
        setUserEmail({commit},email){
            commit('setUserEmail',email)
        },
        setUserName({commit},name){
            commit('setUserName',name)
        }
    },
    getters:{
        isLoggedIn(state){
            return state.isloggedin
        },
        getIsStudent(state){
            return state.isStudent
        },
        getUserInfo(state){
            return state.id +' '+ state. email +' '+ state.firstName +' '+ state.lastName +' '+ state.number
        },
        getUserId(state){
            return state.id
        },
        getUserFirstName(state){
            return state.firstName
        },
        getUserLastName(state){
            return state.lastName
        },
        getUserFullName(state){
            return state.firstName + ' ' + state.lastName
        },
        getUserEmail(state){
            return state.email
        }
        
    },
    modules:{
    }
})