<template>
  <div class="about">
    <video
      src="file:///Users/alexwang/Downloads/122519_001-1pon-1080p/rh2048.com@122519_001-1pon-1080p.mp4"
    ></video>
  </div>
</template>
<script setup>
import Editor from "@/components/Editor.vue";
</script>
<script>
export default {
  data() {
    return {
      questions: [],
      types: ["单选题", "多选题", "判断题", "填空题", "主观题"],
    };
  },
  methods: {
    deleteQuestion(i) {
      this.questions.splice(i, 1);
    },
    addQuestion(type) {
      if (type == "判断题") {
        this.questions.push({
          type: type,
          desc: "",
          answer: "参考答案",
          score: 1,
          analysis: "<解析>",
          items: ["对", "错"].map((e) => {
            return { id: 1, label: e, checked: "" };
          }),
        });
      } else
        this.questions.push({
          type: type,
          desc: "",
          answer: "参考答案",
          select: "",
          score: 1,
          analysis: "<解析>",
          items: [{ id: 1, label: "", checked: "" }],
        });

      this.$nextTick(() => {
        document
          .querySelectorAll(".question")
          [this.questions.length - 1].scrollIntoView();
      });
    },
    delOptions(question, qindex, index) {
      if (question.items.length > 1) {
        question.items.splice(index, 1);
        this.$nextTick(function () {
          let el = document.querySelector(
            "#option" + qindex + "_" + (index - 1) + ".div-input"
          );
          console.log(el);
          this.placeCaretAtEnd(el);
        });
      }
    },
    newOption(question, qindex, index) {
      if (question.type == "判断题") return;
      if (question.items.length - 1 <= index) {
        question.items.splice(index + 1, 0, {
          id: 1,
          label: "",
          checked: "",
        });
      }
      this.$nextTick(function () {
        let el = document.querySelector(
          "#option" + qindex + "_" + (index + 1) + ".div-input"
        );
        console.log(el);
        this.placeCaretAtEnd(el);
      });
    },
    placeCaretAtEnd(el) {
      el.focus();
      if (
        typeof window.getSelection != "undefined" &&
        typeof document.createRange != "undefined"
      ) {
        var range = document.createRange();
        range.selectNodeContents(el);
        range.collapse(false);
        var sel = window.getSelection();
        sel.removeAllRanges();
        sel.addRange(range);
      } else if (typeof document.body.createTextRange != "undefined") {
        var textRange = document.body.createTextRange();
        textRange.moveToElementText(el);
        textRange.collapse(false);
        textRange.select();
      }
    },
  },
};
</script>
<style>
.subject {
  display: flex;
  position: relative;
}
.subject .desc {
  flex-grow: 1;
}

.items {
  display: flex;
  line-height: 1.5em;
}
input[type="radio"],
input[type="checkbox"] {
  overflow: hidden;
  margin-right: 5px;
}

.num {
}
.idnum {
  min-width: 35px;
}
.options {
  margin: 10px 0;
}
.question {
  display: flex;
  padding: 15px 0;
  user-select: none;
}
.op {
  position: fixed;
  bottom: 0;
  background: white;
  user-select: none;
  font-weight: bold;
}
.op a {
  display: inline-block;
  cursor: pointer;
  margin: 5px 10px;
  text-decoration: underline;
}
input[type="number"] {
  border: none;
  border-bottom: 1px solid;
  outline: none;
  width: 60px;
  text-align: center;
}
.body {
  flex-grow: 1;
}
.idnum .del {
  display: block;
  color: red;
  font-weight: bold;
}
.option {
  flex-grow: 1;
}
.items .del {
  color: red;
  font-weight: bold;
  flex-shrink: 0;
}
.items label {
  width: 35px;
  display: inline-block;
  flex-shrink: 0;
}
.del {
  cursor: pointer;
  user-select: none;
}
.num {
  display: block;
  user-select: none;
}
.selectAnswer {
  color: blue;
  font-weight: bold;
}
::v-deep .desc .div-input {
  width: 100%;
}
</style>
