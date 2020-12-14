<template>
  <div id="app">
    
    <Cabecalho @pesquisaJogo="pesquisaJogo($event)" @resetaPesquisa="resetaBusca()"  @addJogoModal="abrirAddJogoModal()" />

    <h2 v-if="hasResults" class="title is-4 espacamento"> Resultados para: "{{searchName}}"" </h2>
    <div v-if="jogosFiltrados.length">
      <div v-for="jogo in jogosFiltrados" :key="jogo.id">
        <jogo-small :id="jogo.id" :nome="jogo.nome" :descricao="jogo.descricao" :imgUrl="jogo.imgUrl" :ntComunidade="jogo.nt_comunidade" :ntCritica="jogo.nt_critica" @editJogoModal="abrirEditJogoModal($event)" @rmJogo="rmJogo($event)"/>
      </div>
    </div>
    <div v-else class="has-text-centered">
      <h2 class="title is-5 espacamento"> Não há nenhum resultado. </h2>
    </div>
    
    <JogoForm :jogoId="jogoIdEdit" @addJogo="addJogo($event)" @attJogo="attJogo($event)" :isAdd="isAdd" :isActive="isFormModalOpen" @fechaFormModal="fechaFormModal()" />    

  </div>
</template>

<script>
import axios from 'axios'
import JogoSmall from './components/JogoSmall.vue'
import Cabecalho from './components/Cabecalho.vue'
import JogoForm from './components/JogoForm.vue'


export default {
  name: 'App',
  created(){
    axios.get("http://localhost:8080/api/jogos").then(res => {
      this.jogos = res.data;
      this.jogosFiltrados = res.data;
//      console.log(this.jogos);
      console.log("teste")
    }).catch((err) => {
      console.warn(err);
    });
  },
  data(){
    return{
      jogos: [],
      jogosFiltrados: [],
      hasResults: false,
      searchName: "",
      isAdd: true,
      isFormModalOpen: false,
      jogoIdEdit: -1
    }
  },
  components: {
    JogoSmall,
    Cabecalho,
    JogoForm,
  },
  methods: {
    pesquisaJogo: function($event){
      this.searchName = $event.nomeJogo;
      if(this.searchName === ""){
        this.jogosFiltrados = this.jogos;
        this.hasResults = false;
      } else {
        this.hasResults = true;
        this.jogosFiltrados = this.jogos.filter(jogo => jogo.nome.toUpperCase().includes(this.searchName.toUpperCase()));
      }
    },
    resetaBusca: function(){
      this.jogosFiltrados = this.jogos;
      this.hasResults = false;
    },
    addJogo: function($event){
      axios.post("http://localhost:8080/api/jogos", $event.jogo).then(res => {
        this.fechaFormModal();
        this.jogos.push(res.data);
      }).catch((err) => {
        alert('Ocorreu um erro inesperado');
        console.warn(err);
      });
    },
    abrirAddJogoModal: function(){
      this.isFormModalOpen = true;
      this.isAdd = true;
      this.jogoIdEdit = -1;
    },
    abrirEditJogoModal: function($event){
      this.isFormModalOpen = true;
      this.isAdd = false;
      this.jogoIdEdit = $event.id;
    },
    fechaFormModal: function(){
      this.isFormModalOpen = false;
      this.jogoIdEdit = -1;
    },
    rmJogo: function($event){
      axios.delete("http://localhost:8080/api/jogos/"+$event.id).then(() => {
        this.jogos = this.jogos.filter(j => j.id != $event.id );
        this.jogosFiltrados = this.jogosFiltrados.filter(j => j.id != $event.id );
      }).catch((err) => {
        alert('Ocorreu um erro inesperado');
        alert("erro");
        console.warn(err);
      });
    },
    attJogo: function($event){
      axios.put("http://localhost:8080/api/jogos/"+$event.jogo.id, $event.jogo).then(() => {
        //console.log(res.data);
        //console.log(this.jogos);
        this.jogos = this.jogos.map( j => {
          if(j.id == $event.jogo.id){
            j = $event.jogo;
          }
          return j;
        }); 
        this.jogosFiltrados = this.jogosFiltrados.map( j => {
          if(j.id == $event.jogo.id){
            j = $event.jogo;
          }
          return j;
        }); 
        this.fechaFormModal();
      }).catch(err => {
        console.warn(err)
      });
    },
    atualizaLista: function(){

    }
  }
}
</script>

<style>
#app {

}
.espacamento {
  margin: 2%;
}

</style>
