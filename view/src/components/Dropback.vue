<script setup>
import { useConfigStore } from "@/stores/config";
import { onMounted, ref } from "vue";

const config = useConfigStore();

const backdrop = ref(null);

onMounted(() => {
  document.body.addEventListener("click", (event) => {
    if (event.target == backdrop.value) {
      config.$patch({ showSidebar: 0 });
    }
  });
});
</script>
<template>
  <div
    class="vt-backdrop backdrop"
    v-if="config.showSidebar"
    ref="backdrop"
  ></div>
</template>

<style scoped>
.vt-backdrop {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.6);
  transition: opacity 0.5s;
  z-index: var(--vp-z-index-backdrop);
}
</style>
