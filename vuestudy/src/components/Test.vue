<template>
  <div id="testDiv">
    1. message 표기 테스트 : {{ msg }} <br/>
    2. v-bind message 표기 테스트 :
    <span v-bind:title="bindmsg">
      bind test
    </span> <br/>
    3. v-if 테스트 :  <p v-if="seeFlag">보이나요</p>
    4-1. v-for string 테스트 :
    <ol>
      <li v-for="(data,idx) in datas" v-bind:key="{data,idx}">
        {{data}},{{idx}}
      </li>
    </ol><br/>
    4-2. v-for object 테스트 :
    <ol>
      <li v-for="(item,idx) in items" v-bind:key="{item,idx}">
        {{item.name}},{{idx}}
      </li>
    </ol><br/>
    5. 사용자 evnet 테스트 :
    <p>{{reverseTargetMsg}}</p>
    <button v-on:click="reverseFn">ReverseButton</button><br/>
    6. v-model 양방향 바인딩 테스트 :
    <input v-model="reverseTargetMsg" /><br/>
<!--    7. msg : {{parentMsg}}-->
    7. component 생성 :
    <ul>
      <todo-item v-for="item in items" v-bind:todo="item" v-bind:key="item"></todo-item>
    </ul>
  </div>
</template>

<script>
import Vue from "vue";

Vue.component('todo-item',{
  props : ['todo']
  ,template : '<li>{{item.name}}}</li>'
})

export default {
  name: "Test",
  props : ['testmsg','changebind'],
  data(){
    return{
      msg : this.testmsg
      ,bindmsg :this.changebind != null ? this.changebind : 'bind message1!!'
      ,seeFlag : true
      ,datas : ['data1','data2','data3']
      ,items : [
        {name : 'name1'}
          ,{name : 'name2'}
          ,{name : 'name2'}
      ]
      ,reverseTargetMsg : '뒤바뀔메시지'
      //,parentMsg : this.childSendMsg
    }
  },
  methods : {
    reverseFn : function (){ //reverseTargetMsg 데이터 리버스 함수
      this.reverseTargetMsg = this.reverseTargetMsg.split('').reverse().join('')
    }
  }
}
</script>

<style scoped>

</style>