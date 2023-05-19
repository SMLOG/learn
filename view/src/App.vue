<script setup>
import Sidebar from "./components/Sidebar.vue";
import Dropback from "./components/Dropback.vue";
import TopHeader from "./components/TopHeader.vue";
import { useConfigStore } from "@/stores/config";

const config = useConfigStore();
</script>
<template>
  <Dropback />
  <TopHeader />
  <Sidebar />

  <div id="content" class="has-sideba">
    <div class="body">
      <RouterView />
    </div>
  </div>
</template>

<style scoped></style>
