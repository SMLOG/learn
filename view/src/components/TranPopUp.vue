<script setup>
defineProps({
  msg: {
    type: String,
    required: true,
  },
});
</script>

<template>
  <div ref="hello" style="padding: 5px; background: white">
    <div class="wrap">
      <div class="row">
        {{ curItem.q }}
        <b v-if="curItem.am"> [{{ curItem.am }}]</b>
        <b v-if="curItem.en != curItem.am"> [{{ curItem.en }}]</b>
      </div>

      <div v-for="to in _toList" :key="to.src" class="row">
        <span
          @click.prevent="selectItem(to)"
          :class="{ cur: to.to == curItem.to }"
          class="to"
          >{{ to.to }}</span
        >
        <span class="src">{{ to.src }}</span>
      </div>
      <div class="row" v-if="curItem.error">{{ curItem.error }}</div>
      <div class="row" v-if="curItem.errorUrl">
        <a target="_blank" :href="curItem.errorUrl">{{ curItem.errorUrl }}</a>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
