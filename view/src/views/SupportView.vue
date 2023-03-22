<template>
  <div>
    <div @click="showcontact = !showcontact">contact</div>
    <div v-if="showcontact">:{{ config.contact1 }}</div>
  </div>
  <div>
    <div>support {{ log.id }}</div>
    <div class="form">
      <div style="display: flex; flex-wrap: wrap">
        <div class="col">
          <label> dept: </label>
          <select @change="log.dept = $refs.deptselect.value" ref="deptselect">
            <option v-for="o in depts" :key="o">{{ o }}</option>
          </select>
          <input v-model="log.dept" style="width: 100%" />
        </div>
        <div class="col">
          <label> username: </label>
          <input v-model="log.userName" style="width: 100%" />
        </div>
        <div class="col">
          <label> loginName: </label>
          <input
            v-model="log.loginName"
            style="width: 100%"
            @blur="queryLoginNames(log.loginName)"
          />
          <div v-if="log.loginName">
            <select
              @change="log.loginName = $refs.username.value"
              ref="username"
            >
              <option v-for="o in usernames" :key="o" :value="o.loginName">
                {{ o.loginName }} - {{ o.dept }} - {{ o.tel }} - {{ o.status }}
              </option>
            </select>
            <a @click="released()">release</a>
          </div>
        </div>

        <div class="col">
          <label> tel: </label>
          <input
            v-model="log.tel"
            style="width: 100%"
            @blur="updateInfobyTel()"
          />
        </div>
        <div class="col">
          <label> Area: </label>
          <select v-model="log.area" style="width: 100%">
            <option v-for="a in areas" :key="a">{{ a }}</option>
          </select>
        </div>

        <div class="col">
          <label> Type: </label>
          <select v-model="log.type" style="width: 100%">
            <option v-for="a in types" :key="a">{{ a }}</option>
          </select>
        </div>

        <div class="col">
          <label> Status: </label>
          <select v-model="log.status" style="width: 100%">
            <option v-for="a in statuss" :key="a">{{ a }}</option>
          </select>
        </div>

        <div class="col">
          <label> Call Date: </label>
          <div><calendar v-model="log.callDate" /></div>
        </div>
        <div class="col">
          <label> Close Date: </label>
          <div><calendar v-model="log.closeDate" /></div>
        </div>
        <div class="col">
          <label> Log by: </label>
          <input v-model="log.logBy" style="width: 100%" />
        </div>
      </div>
      <div style="margin-top: 10px">
        <label>problem:</label
        ><textarea style="width: 100%" v-model="log.problem"></textarea>
      </div>
      <div style="margin-top: 10px">
        <label>solution:</label
        ><textarea style="width: 100%" v-model="log.solutionPlan"></textarea>
      </div>

      <div class="bts">
        <a @click="savelog()">Save</a>
        <a @click="exportExcel()">Export Excel</a>
        <a @click="cancel()">Cancel</a>
        <a @click="showDA = !showDA">DA</a>
      </div>

      <div style="display: flex; margin-top: 10px">
        <label>key words:</label
        ><input
          style="flex-grow: 1"
          v-model="log.keyword"
          @focus="log.keyword = ''"
        />
      </div>
    </div>
    <div v-if="showDA">
      <div>{{ config.labelDA }}</div>
      <div>dept name:<input v-model="deptName" /></div>
      <div>
        <table style="width: 100%">
          <tr>
            <td>BD</td>
            <td>LOGIN_NAME</td>
            <td>FULL_NAME</td>
            <td>EMAIL</td>
            <td>TEL</td>
            <td>DEPT_NAME</td>
            <td>DEPT_POST_NAME</td>
            <td>ROLE</td>
            <td>IS_DP_USER</td>
          </tr>

          <tr v-for="da in dassearch" :key="da.id">
            <td>{{ da.bd }}</td>
            <td>{{ da.loginName }}</td>
            <td>{{ da.fullName }}</td>
            <td>{{ da.email }}</td>
            <td>{{ da.tel }}</td>
            <td>{{ da.deptName }}</td>
            <td>{{ da.deptPostName }}</td>
            <td>{{ da.role }}</td>
            <td>{{ da.isDp }}</td>
          </tr>
        </table>
      </div>
    </div>
    <table cellpadding="0" cellspacing="0" style="width: 100%">
      <tr class="trth">
        <th>dept</th>

        <th>tel</th>

        <th>call date</th>
        <th>closeDate</th>
        <th>status</th>
      </tr>
      <template v-for="log in searchlogs" :key="log.id">
        <tr :class="{ cache: !log.id }">
          <td>{{ log.dept }}</td>

          <td>{{ log.tel }}</td>

          <td>{{ log.callDate }}</td>
          <td>{{ log.closeDate }}</td>
          <td class="bts">
            <span>{{ log.status }}</span
            ><span>{{ log.logBy }}</span>
          </td>
        </tr>
        <tr>
          <td>{{ log.loginName }}</td>
          <td>{{ log.userName }}</td>
          <td>{{ log.area }}</td>
          <td>{{ log.type }}</td>
          <td class="bts">
            <a @click="editlog(log)">Edit</a>
            <a @click="rmlog(log)">Delete</a>
          </td>
        </tr>
        <tr class="qa">
          <td colspan="5">
            <div>
              <span>Q#{{ log.id }}:{{ log.problem }}</span>
            </div>
            <div>
              <span>A:{{ log.solutionPlan }}</span>
            </div>
          </td>
        </tr>
      </template>
    </table>
    <pagination
      :current="page"
      :pageSize="pagesize"
      :total="total"
      @change="changePage"
    />
  </div>
</template>
<script setup>
import calendar from "../components/global/calendar.vue";
import pagination from "../components/pagination.vue";
</script>

<script>
export default {
  data() {
    return {
      config: {},
      showcontact: 0,
      showDA: 0,
      deptName: "",
      das: [],
      usernames: [],
      page: 1,
      total: 0,
      pagesize: 0,
      areas: ["Login", "Application", "Generating Reports", "Others"],
      statuss: ["", "Closed", "Assigned"],
      types: ["Enquiry", "Problem", "Usage Error", "User Request"],
      log: {
        id: "",
        dept: "",
        tel: "",
        userName: "",
        loginName: "",
        problem: "",
        callDate: null,
        area: "Application",
        type: "",
        logBy: "",
        status: "",
        closeDate: "",
        solutionPlan: "",
        logNo: "",
        keyword: "",
      },
      logs: [],
      depts: [],
    };
  },
  mounted() {
    fetch("/api/supportlog/config")
      .then((r) => r.json())
      .then((r) => {
        Object.assign(this.config, r);

        this.depts.length = 0;
        this.depts.push(...r.depts.split(/\n/g));
      });
    this.changePage({ page: this.page });

    fetch("/api/daList")
      .then((resp) => resp.json())
      .then((data) => {
        this.das.length = 0;
        this.das.push(...data);
      });
  },
  methods: {
    released() {
      fetch("/api/supportlog/releaseAc?name=" + this.log.loginName)
        .then((r) => r.text())
        .then((r) => {
          alert(r);
        });
    },
    queryLoginNames(name) {
      fetch("/api/supportlog/searchLoginNames?name=" + name)
        .then((r) => r.json())
        .then((r) => {
          this.usernames.length = 0;
          this.usernames.push(...r);
        });
    },
    updateInfobyTel() {
      if (this.log.tel.trim() && this.searchlogs.length > 0) {
        this.searchlogs.forEach((l) => {
          let ps = ["dept", "loginName", "userName"];
          for (let p of ps) {
            if (!this.log[p] && l[p]) {
              this.log[p] = l[p];
            }
          }
        });
      }
    },
    exportExcel() {
      open("/api/supportlog/exportExcel");
    },
    async rmlog(log) {
      if (!window.confirm("continue to remove " + log.id)) return;

      let result = await fetch("/api/supportlog/delete", {
        body: JSON.stringify(log),
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (result) {
        let i = this.logs.indexOf(log);
        this.logs.splice(i, 1);
      }

      // localStorage.setItem("logs", JSON.stringify(this.logs));
    },
    editlog(log) {
      log.updated = 1;
      Object.assign(this.log, log);

      // localStorage.setItem("logs", JSON.stringify(this.logs.filter(e=>e.updated)));
    },
    async savelog() {
      let id = this.log.id;
      if (!log.logBy) log.logBy = this.config.logBy;
      let result = await fetch("/api/supportlog/save", {
        body: JSON.stringify(this.log),
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((r) => r.json())
        .then((r) => {
          if (!r.id) return 0;
          if (id) {
            for (let i = 0; i < this.logs.length; i++) {
              if (this.logs[i].id == id) {
                this.logs.splice(i, 1);
                break;
              }
            }
          }
          this.logs.unshift(r);
          return 1;
        });

      if (!result) return;
      for (let p in this.log) {
        this.log[p] = "";
      }
      //  await this.changePage({ page: 1 });
    },
    cancel() {
      for (let p in this.log) {
        this.log[p] = "";
      }
    },
    changePage(p) {
      this.page = p.page;
      fetch("/api/supportlog?page=" + this.page)
        .then((r) => r.json())
        .then((r) => {
          console.log(r);
          this.page = r.pageable.pageNumber + 1;
          this.total = r.totalElements;
          this.pagesize = r.size;
          this.logs.length = 0;
          this.logs.push(...r.content);
        });
    },
  },
  computed: {
    dassearch() {
      console.log(this.deptName);
      if (this.deptName && this.deptName.trim())
        return this.das
          .filter((e) => e.deptName.toLowerCase().indexOf(this.deptName) > -1)
          .sort(function (a, b) {
            return a.deptName.localeCompare(b.deptName);
          });
      else return this.das;
    },
    searchlogs() {
      let ret = this.logs;
      if (this.log.tel) {
        ret = ret.filter(
          (e) => e.tel && e.tel.replace(/\s/g, "").indexOf(this.log.tel) > -1
        );
      }
      if (this.log.keyword.trim()) {
        let kws = this.log.keyword.split(",");
        for (let kw of kws) {
          if (!kw) continue;
          ret = ret.filter((e) => e.problem && e.problem.indexOf(kw) > -1);
        }
      }
      return ret;
    },
  },
};
</script>
<style>
.trth {
  position: sticky;
  top: var(--vt-nav-height);
  z-index: 1;
}
.trth th {
  background: black;
  font-weight: bold;
  color: white;
}

.qa div:last-child {
  border-bottom: 1px solid;
}
.cache td {
  background: gray;
}

.bts {
  display: flex;
  justify-content: space-between;
}
th {
  text-align: left;
}
.form {
  padding: 15px;
  border: 2px dashed #ddd;
}
</style>
