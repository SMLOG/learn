<script setup>
import { ref, onMounted } from "vue";
import { useConfigStore } from "@/stores/config";
import Detail from "@/components/Detail.vue";

const itemsRef = ref(null);
const itemsRef2 = ref(null);
const itemsRef3 = ref(null);

onMounted(() => {
  document.body.addEventListener("click", (event) => {
    if (
      showItems.value &&
      !itemsRef.value.contains(event.target) &&
      !itemsRef3.value.contains(event.target) &&
      !itemsRef2.value.contains(event.target)
    ) {
      showItems.value = 0;
    }
  });
});

const config = useConfigStore();

let types = ref([]);
fetch("/api/list")
  .then((r) => r.json())
  .then((r) => types.value.push(...r));
let curIndex = ref(0);
let showItems = ref(0);

let curTab = ref(0);
let cur = ref({});

let selectIndex = ref(0);

function clickIndex(i) {
  console.log(i);
  curIndex.value = i;
  showItems.value = 0;
  selectIndex.value = 0;

  cur.value = types.value[curIndex.value];
}
</script>
<template>
  <div class="pans">
    <div class="panel" v-show="curTab == 0">
      <div style="margin: 10px">
        <a style="display: inline-block; margin-right: 10px"
          >Subscription{{ types.length }}-{{ showItems }}</a
        >
        <a style="display: inline-block">All</a>
      </div>
      <div style="display: flex; position: relative; flex-wrap: wrap">
        <div
          v-for="(type, i) in types"
          :key="type.name"
          class="list_item"
          @click="clickIndex(i)"
        >
          <div>{{ i + 1 }} {{ type.name }}</div>
          <div>{{ type.update }}</div>
          <div>{{ type.items.length }}</div>
        </div>
      </div>
    </div>
    <Detail v-show="curTab == 1" class="tab_detail" />
  </div>
  <div id="bottom">
    <div
      class="items"
      style="margin: 10px; max-height: calc(100vh - 100px); overflow-y: auto"
      v-if="showItems"
      ref="itemsRef"
    >
      <div v-if="showItems == 1">
        <div
          v-for="(item, i) in cur.items"
          :key="item.id"
          @click="(selectIndex = i), (showItems = 0), (curTab = 1)"
        >
          {{ i + 1 }}. {{ item.title }}
        </div>
      </div>
      <div v-if="showItems == 2">
        <div
          v-for="item in ['Home', 'Words']"
          :key="item"
          @click="showItems = 0"
        >
          {{ item }}
        </div>
      </div>
    </div>

    <div style="display: flex; border-top: 1px solid #ccc">
      <div
        style="margin-left: 10px; margin-right: 10px; position: relative"
        @click="(curTab = 0), (showItems = showItems == 0 ? 2 : 0)"
        ref="itemsRef3"
      >
        Home:{{ curIndex }}
      </div>
      <div
        style="flex-grow: 1"
        @click="showItems = showItems == 0 ? 1 : 0"
        ref="itemsRef2"
      >
        <span>{{ cur.name }}</span
        ><span v-if="cur.items">{{ cur.items[selectIndex].title }}</span>
      </div>
      <div
        style="margin-left: 10px; margin-right: 10px; margin-right: 10px"
        @click="
          config.$patch({ showSidebar: !config.showSidebar, fnid: 'signin' })
        "
      >
        Setting
      </div>
    </div>
  </div>
</template>
<style scoped>
.items div:nth-child(even) {
  background: #fcfcfc;
}
.pans {
  position: fixed;
  top: 55px;
  bottom: 25px;
  left: 0;
  right: 0;
  overflow: auto;
}
#bottom {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  z-index: 2;

  box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
  background: white;
}
.tab_detail {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background: whitesmoke;
}
.list_item {
  min-height: 200px;
  min-width: 200px;
  margin: 10px;
  padding: 10px;
  border: 1px solid green;
  position: relative;
}
</style>
