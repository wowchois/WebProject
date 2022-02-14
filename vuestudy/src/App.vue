<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
<!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
<!--    <Test testmsg="test1" />-->
    <Test v-bind:testmsg="message" v-bind:changebind="changemsg"/>
<!--    <input v-model="childSendMsg">-->
  </div>
</template>

<script>
//import HelloWorld from './components/HelloWorld.vue'
import Test from "@/components/Test";

export default {
  name: 'App',
  components: {
    //HelloWorld,
    Test
  },
  data: ()=>({
    message : '부모에서 입력한 메시지'
    ,changemsg : 'chagne!!'
    //,childSendMsg : '자식에게 보내는 메시지'
  })
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
