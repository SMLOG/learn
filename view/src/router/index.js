import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
    {
      path: "/docs",
      name: "docs",

      component: () => import("../views/DocsView.vue"),
    },
    {
      path: "/learn",
      name: "learn",

      component: () => import("../views/LearnView.vue"),
    },
    {
      path: "/support",
      name: "support",

      component: () => import("../views/SupportView.vue"),
    },
    {
      path: "/logs",
      name: "logs",

      component: () => import("../views/LogsView.vue"),
    },
  ],
});

export default router;
