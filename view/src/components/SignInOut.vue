<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";

import { useConfigStore } from "@/stores/config";

const config = useConfigStore();

const username = ref("");
const password = ref("");
const name = ref("");
const { fnid } = storeToRefs(config);

const islogin = ref(0);

function isLogin() {
  return fetch("/api/user/isLogin")
    .then((resp) => resp.json())
    .then((resp) => {
      islogin.value = resp.code == 200;
      console.log(islogin);
      config.$patch({ username: resp.name, showSidebar: 0 });
    });
}

function login() {
  fetch("/api/user/login")
    .then((r) => r.json())
    .then((r) => {
      return isLogin();
    });
}

function signup() {
  return fetch("/api/user/signup", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      userName: username.value,
      pwd: password.value,
      name: name.value,
    }),
  }).then(() => {
    return isLogin();
  });
}
isLogin();
</script>

<template>
  <div v-if="fnid == 'signin' || fnid == 'signup'">
    <h1 v-if="fnid == 'signin'">Sign In</h1>
    <h1 v-if="fnid == 'signup'">Sign Up</h1>
    <div>
      <div>
        <label for="email">Email</label>
        <div>
          <input
            type="email"
            size="30"
            v-model="username"
            maxlength="100"
            style="width: 100%"
          />
        </div>
      </div>
      <div>
        <div style="display: flex; justify-content: space-between">
          <label for="password">Password</label>

          <a v-if="fnid == 'signin'">Forgot password?</a>
        </div>
        <div>
          <input
            type="password"
            autocomplete="off"
            style="width: 100%"
            v-model="password"
          />
        </div>
      </div>
      <div>
        <button v-if="fnid == 'signin'" @click="login()">Log in</button>
        <button v-if="fnid == 'signup'" @click="signup()">Sign Up</button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
