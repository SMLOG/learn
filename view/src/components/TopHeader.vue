<script setup>
import { useConfigStore } from "@/stores/config";
import { onMounted, ref } from "vue";

const config = useConfigStore();

function logout() {
  fetch("/api/user/logout")
    .then((r) => r.json())
    .then((r) => {
      config.$patch({ username: "" });
    });
}

onMounted(() => {});
</script>
<template>
  <header id="topbar">
    <div class="navBar">
      <div class="container">
        <nav>
          <RouterLink to="/support" class="menuLink">Home</RouterLink>
          <RouterLink to="/support" class="menuLink">support</RouterLink>
          <RouterLink to="/docs" class="menuLink">Docs</RouterLink>
          <RouterLink to="/learn" class="menuLink">Learn</RouterLink>
        </nav>
        <div class="sign" v-if="!config.username">
          <a>Sign Up</a
          ><a
            style="margin-left: 10px"
            @click="
              config.$patch({
                showSidebar: !config.showSidebar,
                fnid: 'signin',
              })
            "
            >Sign In</a
          >
        </div>
        <div v-else>
          <span>Welcome {{ config.username }} </span>
          <a style="margin-left: 10px" @click="logout()">Logout</a>
        </div>
      </div>
    </div>
  </header>
</template>

<style scoped>
.menuLink {
  display: block;
  padding: 0 12px;
  line-height: calc(var(--vt-nav-height) - 1px);
  font-size: 13px;
  font-weight: 500;
  color: var(--vt-c-text-1);
  transition: color 0.25s;
  white-space: nowrap;
}

.top-link {
  padding: 0 24px;
  line-height: 47px;
  font-size: 12px;
  font-weight: 500;
  color: var(--vt-c-text-2);
  transition: color 0.5s;
}

#topbar {
  position: sticky;
  top: 0;
  left: 0;
  z-index: 1111;
}
.navBar {
  position: relative;
  border-bottom: 1px solid var(--vt-c-divider-light);
  padding: 0 12px 0 24px;
  height: var(--vt-nav-height);
  background-color: var(--vt-c-bg);
  white-space: nowrap;
  transition: border-color 0.5s, background-color 0.5s;
}
.bartitle {
  display: flex;
  align-items: center;
  padding-top: 1px;
  height: var(--vt-nav-height);
  transition: opacity 0.25s;
}

.navBar {
  position: relative;
  border-bottom: 1px solid var(--vt-c-divider-light);
  padding: 0 12px 0 24px;
  background-color: var(--vt-c-bg);
  white-space: nowrap;
  transition: border-color 0.5s, background-color 0.5s;
}
.container {
  display: flex;
  justify-content: space-between;
}

.menu-icon {
  margin-right: 8px;
  width: 16px;
  height: 16px;
  fill: currentColor;
}
.menu {
  padding: 0;
  background-color: transparent;
  background-image: none;
  border: 0;
}
.menu {
  display: flex;
  align-items: center;
  padding: 0 24px;
  line-height: 47px;
  font-size: 12px;
  font-weight: 500;
  color: var(--vt-c-text-2);
  transition: color 0.5s;
}

#topbar nav {
  display: flex;
}
</style>
