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
      !itemsRef2.value.contains(event.target)
    ) {
      showItems.value = 0;
    }
  });
});

const config = useConfigStore();

let types = ref([]);

types.value.push({ id: 0, title: "Words", items: [] });

fetch("/api/word/list")
  .then((r) => r.json())
  .then((r) =>
    types.value[0].items.push(...r.map((e) => ((e.title = e.en), e)))
  );

fetch("/api/list")
  .then((r) => r.json())
  .then((r) => types.value.push(...r));

let curIndex = ref(0);
let showItems = ref(0);

let cur = ref({});
let curItem = ref({});

let selectIndex = ref(0);

function clickType(i) {
  console.log(i);
  curIndex.value = i;
  showItems.value = 0;
  selectIndex.value = 0;

  cur.value = types.value[curIndex.value];
}
let curTab = ref("Home");

let showHome = ref(true);
function clickHome() {
  showHome.value = !showHome.value;
}
function clickItem(i) {
  showHome.value = false;
  selectIndex.value = i;
  console.log(cur.items);
  curItem.value = cur.value.items[selectIndex.value];
  showItems.value = 0;
  curTab.value = "Detail";
}
function nextItem() {
  if (selectIndex.value >= cur.value.items.length - 1) return;
  selectIndex.value++;
  curItem.value = cur.value.items[selectIndex.value];
}

function prevItem() {
  if (selectIndex.value <= 0) return;
  selectIndex.value--;
  curItem.value = cur.value.items[selectIndex.value];
}
</script>
<template>
  <div class="pans">
    <div class="panel home" v-show="showHome">
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
          @click="clickType(i)"
        >
          <div>{{ i + 1 }} {{ type.name }}</div>
          <div>{{ type.update }}</div>
          <div>{{ type.items.length }}</div>
        </div>
      </div>
    </div>

    <Detail
      v-show="curTab == 'Detail'"
      style="padding: 20px"
      :item="curItem"
      class="tab_detail"
    />
  </div>
  <div id="bottom">
    <div
      style="display: flex; justify-content: space-between; margin: 0 10px"
      v-if="curTab == 'Detail' && !showHome"
    >
      <a @click="prevItem()">Prev</a>
      <a @click="nextItem()">Next</a>
    </div>

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
          @click="clickItem(i)"
        >
          {{ i + 1 }}. {{ item.title }}
        </div>
      </div>
    </div>

    <div style="display: flex; border-top: 1px solid #ccc">
      <div
        style="margin-left: 10px; margin-right: 10px; position: relative"
        @click="clickHome()"
      >
        Home
      </div>
      <div
        style="flex-grow: 1"
        @click="showItems = showItems == 0 ? 1 : 0"
        ref="itemsRef2"
      >
        <span>{{ cur.name }}</span
        ><span v-if="cur.items && cur.items.length">{{
          cur.items[selectIndex].title
        }}</span>
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
.home {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  background: white;
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
