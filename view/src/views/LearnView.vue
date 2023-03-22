<script setup>
import { ref } from "vue";

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
    name: "SQL",
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
];
let curIndex = ref(0);
let showItems = ref(false);

function cur() {
  return types[curIndex.value];
}
function clickIndex(i) {
  console.log(i);
  curIndex.value = i;
  showItems.value = false;
}
</script>
<template>
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

  <div
    style="
      position: fixed;
      bottom: 0;
      left: 0;
      width: 100%;

      box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
      background: white;
    "
  >
    <div style="margin: 10px" v-if="showItems">
      <div v-for="item in cur().items" :key="item.id">{{ item.title }}</div>
    </div>
    <div style="display: flex">
      <div style="margin-left: 10px; margin-right: 10px">O:{{ curIndex }}</div>
      <div style="flex-grow: 1" @click="showItems = !showItems">
        <span>{{ cur()["name"] }}</span
        ><span>{{ cur()["items"][0].title }}</span>
      </div>
      <div style="margin-left: 10px; margin-right: 10px; margin-right: 10px">
        M
      </div>
    </div>
  </div>
</template>
