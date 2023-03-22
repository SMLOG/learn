<template>
  <div>
    <div>support {{ log.id }}</div>
    <div>
      <div style="display: flex">
        <label> dept:<input v-model="log.dept" /> </label>
        <label> username:<input v-model="log.username" /> </label>
        <label> contact:<input v-model="log.contact" /> </label>
      </div>
      <div style="display: flex; margin-top: 10px">
        <span>detail:</span
        ><textarea style="flex-grow: 1" v-model="log.detail"></textarea>
      </div>
      <div><a @click="savelog()">Save</a></div>
    </div>
    <ul>
      <li v-for="log in logs" :key="log.id">
        <div style="display: flex; justify-content: space-between">
          <span>{{ log.dept }}</span>
          <span>{{ log.username }}</span>
          <span>{{ log.contact }}</span>
          <span>{{ log.date }}</span>
          <a @click="editlog(log)">Edit</a>
          <a @click="rmlog(log)">Delete</a>
        </div>
        <div
          style="
            margin-top: 10px;
            margin-bottom: 10px;
            border-bottom: 1px dashed gray;
          "
        >
          <span>{{ log.detail }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      log: {
        id: "",
        dept: "",
        contact: "",
        username: "",
        detail: "",
        date: null,
      },
      logs: localStorage.getItem("logs")
        ? JSON.parse(localStorage.getItem("logs"))
        : [],
    };
  },
  methods: {
    rmlog(log) {
      if (!window.confirm("continue to remove " + log.id)) return;
      let i = this.logs.indexOf(log);
      this.logs.splice(i, 1);
      localStorage.setItem("logs", JSON.stringify(this.logs));
    },
    editlog(log) {
      Object.assign(this.log, log);
      localStorage.setItem("logs", JSON.stringify(this.logs));
    },
    savelog() {
      if (!this.log.date) this.log.date = new Date().getTime();
      if (!this.log.id) {
        this.log.id = new Date().getTime();
        let copy = Object.assign({}, this.log);
        this.logs.unshift(copy);
      } else {
        let clog = this.logs.filter((e) => e.id == this.log.id)[0];
        Object.assign(clog, this.log);
      }

      localStorage.setItem("logs", JSON.stringify(this.logs));
      this.log.id =
        this.log.date =
        this.log.dept =
        this.log.contact =
        this.log.username =
        this.log.detail =
          "";
    },
  },
  computed: {
    searchlogs() {},
  },
};
</script>
<style></style>
