import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import "./assets/main.css";

// 导入截屏插件
import screenShort from "vue-web-screen-shot";
import $ from 'jquery'
window.$ = $;

const app = createApp(App);

//import components from "@/components/index";
//app.use(components);

app.use(createPinia());
app.use(router);

// 使用截屏插件
app.use(screenShort, { enableWebRtc: false });

app.mount("#app");
