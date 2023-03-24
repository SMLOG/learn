<script setup>
import { ref, onMounted } from "vue";
import { useConfigStore } from "@/stores/config";

const itemsRef = ref(null);
const itemsRef2 = ref(null);

onMounted(() => {
  document.body.addEventListener("click", (event) => {
    if (
      showItems.value &&
      !itemsRef.value.contains(event.target) &&
      !itemsRef2.value.contains(event.target)
    ) {
      showItems.value = false;
    }
  });
});

const config = useConfigStore();

let types = [
  {
    name: "Java-8-11",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
      {
        id: "efg",
        title: "efg",
        date: "2023",
      },
    ],
  },
  {
    name: "Spring-Springboot",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "WebServie(Soap,Rest)",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "DB-Oracle-MSSql",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "OWB-OBIEE-BI",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "SQL(Dev&Tuning)",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "Linux",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "windows",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "Documents",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "Javascript(Vue)",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "Comunication(Teamwork)",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "Tools(Git,bash,eclipse,sqldeveloper,docker)",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "Mindset()",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
  {
    name: "Health()",
    update: "2023-01-01",
    lang: "en",
    items: [
      {
        id: "abc",
        title: "abcd",
        date: "2023",
      },
    ],
  },
];
let curIndex = ref(0);
let showItems = ref(false);

let curTab = ref(0);

let selectIndex = ref(0);
function cur() {
  return types[curIndex.value];
}
function clickIndex(i) {
  console.log(i);
  curIndex.value = i;
  showItems.value = false;
  selectIndex.value = 0;
}
</script>
<template>
  <div class="pans">
    <div class="panel" v-show="curTab == 0">
      <div style="margin: 10px">
        <a style="display: inline-block; margin-right: 10px">Subscription</a>
        <a style="display: inline-block">All</a>
      </div>
      <div style="display: flex; position: relative; flex-wrap: wrap">
        <div
          v-for="(type, i) in types"
          :key="type.name"
          style="
            min-height: 200px;
            min-width: 200px;
            margin: 10px;
            padding: 10px;
            border: 1px solid green;
            position: relative;
          "
          @click="clickIndex(i)"
        >
          <div>{{ i + 1 }} {{ type.name }}</div>
          <div>{{ type.update }}</div>
          <div>{{ type.items.length }}</div>
          <div
            style="
              display: flex;
              justify-content: end;
              position: absolute;
              bottom: 0;
              right: 0;
            "
          >
            <a style="margin-right: 10px">button</a>
          </div>
        </div>
      </div>
    </div>
    <div
      class="panel"
      v-show="curTab == 1"
      style="
        position: absolute;
        top: 0;
        bottom: 0;
        right: 0;
        left: 0;
        background: black;
      "
    >
      {{ curTab }}
      content
    </div>
  </div>
  <div id="bottom">
    <div class="items" style="margin: 10px" v-if="showItems" ref="itemsRef">
      <div
        v-for="(item, i) in cur().items"
        :key="item.id"
        @click="(selectIndex = i), (showItems = false), (curTab = 1)"
      >
        {{ item.title }}
      </div>
    </div>
    <div style="display: flex">
      <div style="margin-left: 10px; margin-right: 10px" @click="curTab = 0">
        Home:{{ curIndex }}
      </div>
      <div style="flex-grow: 1" @click="showItems = !showItems" ref="itemsRef2">
        <span>{{ cur()["name"] }}</span
        ><span>{{ cur()["items"][selectIndex].title }}</span>
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
  background: #f0f0f0;
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
</style>
