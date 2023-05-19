<script setup>
import { ref, onMounted } from "vue";
import { useConfigStore } from "@/stores/config";
import Media from "@/components/Media.vue";
import { useMediaStore } from "@/stores/media";

const itemsRef = ref(null);
const itemsRef2 = ref(null);
const media = useMediaStore();

onMounted(() => {
  document.body.addEventListener("click", (event) => {
    if (
      showItems.value &&
      itemsRef.value &&
      !itemsRef.value.contains(event.target) &&
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

let curIndex = ref(-1);
let showItems = ref(0);

let curType = ref({});
let curItem = ref({});

function clickType(i) {
  console.log(i);
  curIndex.value = i;
  showItems.value = 0;

  curType.value = types.value[curIndex.value];
}

let showHome = ref(true);
function clickHome() {
  showHome.value = !showHome.value;
}
function clickItem(i, isclick) {
  curType.value.selectIndex = i;
  console.log(curType.items);
  curItem.value = curType.value.items[curType.value.selectIndex];
  showItems.value = 0;
  if (isclick) showHome.value = false;
  if (curType.value.type == "media") media.$patch({ item: curItem.value });
}
function nextItem(isNext) {
  if (isNext) {
    curType.value.selectIndex++;
    if (curType.value.selectIndex > curType.value.items.length - 1)
      curType.value.selectIndex = 0;
  } else {
    curType.value.selectIndex--;
    if (curType.value.selectIndex < 0)
      curType.value.selectIndex = curType.value.items.length - 1;
  }

  clickItem(curType.value.selectIndex);
}

function loadif(cur) {
  if (!cur.items || !cur.items.length) {
    if (!cur.items) cur.items = [];
    cur.selectIndex = -1;
    fetch("/api/" + cur.type + "/list/" + cur.name)
      .then((r) => r.json())
      .then((r) => cur.items.push(...r));
  }
  if (cur.selectIndex > -1) {
    setTimeout(() => {
      //showItems && scroll2el($("#item" + cur.selectIndex), $(itemsRef.value));
      showItems &&
        $("#item" + cur.selectIndex)[0].scrollIntoView({
          behavior: "smooth",
          block: "start",
          inline: "nearest",
        });
    }, 100);
  }
}
</script>
<template>
  <div class="pans">
    <div class="panel" v-show="showHome" style="z-index: 1">
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
          :class="{ cur: curIndex == i }"
        >
          <div>{{ i + 1 }} {{ type.name }}</div>
          <div>{{ type.update }}</div>
          <div>{{ type.items && type.items.length }}</div>
        </div>
      </div>
    </div>
    <div class="panel" v-show="curType.type == 'media'">
      <Media @ended="nextItem(1)" />
    </div>
  </div>
  <div id="bottom">
    <div
      style="display: flex; justify-content: space-between; margin: 0 10px"
      v-if="!showHome"
    >
      <a @click="nextItem(0)">Prev</a>
      <a @click="nextItem(1)">Next</a>
    </div>

    <div
      class="items"
      style="
        margin-left: 10px;
        max-height: calc(100vh - 100px);
        overflow-y: auto;
      "
      v-show="showItems"
      ref="itemsRef"
    >
      <div>
        <div
          v-for="(item, i) in curType.items"
          :key="item.id"
          @click="clickItem(i, 1)"
          :class="{ cur: curType.selectIndex == i }"
          :id="'item' + i"
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
        ref="itemsRef2"
        @click="
          showItems = showItems == 0 ? 1 : 0;
          loadif(curType);
        "
      >
        <span style="margin-right: 5px">{{ curType.name }}</span
        ><span
          v-if="
            curType.items && curType.items.length && curType.selectIndex > -1
          "
          >({{ curType.selectIndex + 1 }}/{{ curType.items.length }})
          {{ curType.items[curType.selectIndex].title }}</span
        >
      </div>
      <div
        v-if="media.item.link"
        @click="media.$patch({ playing: !media.playing })"
      >
        <b v-if="media.playing">pause</b>
        <b v-else>play</b>
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
  overflow: hidden;
}
.pans .panel {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  overflow: auto;
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
  overflow: auto;
}
.list_item {
  min-height: 200px;
  min-width: 200px;
  margin: 10px;
  padding: 10px;
  border: 1px solid green;
  position: relative;
}
.cur {
  font-weight: bold;
}
</style>
