<script setup>
import { toRefs } from "vue";
const props = defineProps({
  item: {
    type: Object,
    required: true,
  },
});
const { item } = toRefs(props);
</script>
<template>
  <div>
    <div>content{{ item }}</div>
    <div>id: {{ item.id }}</div>
    <div>title: {{ item.title }}</div>

    <div>ddd</div>
  </div>
</template>
<style scoped></style>
