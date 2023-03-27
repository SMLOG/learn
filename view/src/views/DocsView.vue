<script setup>
import { ref } from "vue";

let links = ref([
  {
    url: "https://www.gov.hk/en/about/abouthk/holiday/2023.htm",
    name: "holiday",
  },
  {
    name: "sql developer",
    url: "https://www.oracle.com/webfolder/technetwork/tutorials/obe/db/apex/r51/testing_and_debugging_sqldeveloper/testing_and_debugging_sqldeveloper.html#overview",
  },
]);
</script>

<template>
  <div>
    <div>文件列表</div>
    <div
      v-for="item in links"
      :key="item.name"
      target="_blank"
      rel="noreferrer"
      :href="item.url"
    >
      <a>{{ item.name }} - {{ item.url }}</a>
    </div>
    <div>
      <div>
        search:<input v-model.trim="search" @focus="this.search = ''" />
      </div>
    </div>
    <ul>
      <li v-for="doc in searchdocs" :key="doc.filename">
        <a @click="open(doc)">{{ doc.name }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: "",
      base: "http://127.0.0.1/docs/",
      docs: [],
    };
  },
  methods: {
    open(doc) {
      window.open(this.base + doc.filename, "_blank");
    },
  },
  computed: {
    searchdocs() {
      if (this.search.trim().length > 0) {
        let lc = this.search.trim();

        if (this.ignoreCase) lc = this.search.trim().toLocaleLowerCase();
        return this.docs.filter(
          (e) =>
            (this.ignoreCase
              ? e.filename.toLocaleLowerCase()
              : e.filename
            ).indexOf(lc) > -1
        );
      }
      return this.docs;
    },
  },
};
</script>
<style></style>
