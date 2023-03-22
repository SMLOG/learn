<script setup>
import { useConfigStore } from "@/stores/config";
import SignInOut from "@/components/SignInOut.vue";
const config = useConfigStore();
</script>
<template>
  <aside class="sidebar" :class="{ open: config.showSidebar }">
    <SignInOut v-if="!config.username" />
  </aside>
</template>

<style scoped>
.sidebar {
  position: fixed;
  top: 60px;
  bottom: 0;
  left: 0;
  z-index: var(--vp-z-index-sidebar);
  padding: 0 32px 96px;
  width: calc(100vw - 64px);
  max-width: var(--vp-sidebar-width-mobile);
  opacity: 0;
  background-color: var(--vt-c-bg);
  box-shadow: var(--vt-c-shadow-3);
  overflow-x: hidden;
  overflow-y: auto;
  transform: translate(-100%);
  transition: background-color 0.5s, opacity 0.5s, transform 0.3s ease;
}

.open {
  opacity: 1;
  visibility: visible;
  transform: translate(0);
  transition: background-color 0.5s, opacity 0.25s,
    transform 0.5s cubic-bezier(0.19, 1, 0.22, 1);
}
</style>
