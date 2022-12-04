import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home/Home.vue";
import Login from '@/views/Login.vue'
import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080/'
Vue.prototype.$axios = axios
Vue.use(VueRouter);
axios.interceptors.request.use(config=>{
  config.headers.token = window.sessionStorage.getItem("token")
  console.log(config)
  return config
})

const routes = [
  {
    path: "/",
    redirect:"/login"
  },
  {
    path:'/login',
    component: Login
  },
  {
    path:"/home",
    component:Home
  }
];

const router = new VueRouter({
  routes,
});
router.beforeEach((to, from, next) => {
  if (to.path == "/login") return next();
  let i = window.sessionStorage.getItem("token");
  if (!i) return next("/login");
  next();
});
export default router;
