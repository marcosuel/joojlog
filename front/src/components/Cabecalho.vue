<template>

    <div class="" style="padding: 0;">

        <nav class="navbar is-light is-four-fifths" role="navigation" aria-label="main navigation">
        <div class="navbar-brand">
            <a id="logo" @click="emitirEventoResetarBusca">
            <img src="../assets/logo.png" width="128" height="128">
            </a>

             <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            </a>
        </div>

        <div id="navbarBasicExample" class="navbar-menu">
            <div class="navbar-start">

            <div class="navbar-item" style="">
                <input class="input is-left" type="text"  v-model="pesquisarField" @keydown.enter="emitirEventoPesquisa"  placeholder="Pesquisar">
            </div>

            </div>

            <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                <a class="button is-primary" @click="emitirEventoAddJogoModal">
                    <strong>+ Adicionar Jogo</strong>
                </a>
                </div>
            </div>
            
            </div>
        </div>
    </nav>

    </div>
    
</template>

<script>
document.addEventListener('DOMContentLoaded', () => {

  // Get all "navbar-burger" elements
  const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

  // Check if there are any navbar burgers
  if ($navbarBurgers.length > 0) {

    // Add a click event on each of them
    $navbarBurgers.forEach( el => {
      el.addEventListener('click', () => {

        // Get the target from the "data-target" attribute
        const target = el.dataset.target;
        const $target = document.getElementById(target);

        // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
        el.classList.toggle('is-active');
        $target.classList.toggle('is-active');

      });
    });
  }

});
export default {
    data(){
        return {
            pesquisarField: "",
        }
    },
    methods:{
        emitirEventoPesquisa: function(){
            this.$emit("pesquisaJogo", {nomeJogo: this.pesquisarField});
            this.pesquisarField = "";
        },
        emitirEventoResetarBusca: function(){
            this.$emit("resetaPesquisa");
        },
        emitirEventoAddJogoModal: function(){
            this.$emit("addJogoModal");
        }
    }
}
</script>

<style scoped>

#logo:hover {
    background-color: rgb(248, 248, 248);
}


</style>