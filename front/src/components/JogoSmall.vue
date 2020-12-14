<template>
    
    <div id="jogo-small" class="column is-four-fifths">
        <div class="card">
        <div class="card-content" style="padding: 1%;">
            <div class="media">
            <div class="media-left">
                <figure class="image is-100x100">
                <img class="jogo-capa-small" :src="imgUrl" alt="Not found">
                </figure>
            </div>
            <div class="media-content is-left">
                <a href="#" @click="abrirExpanded" class="jogo-link"><p class="title is-4">{{nome}}</p></a>
                <br>
                <div class="has-text-left"><p class="subtitle is-6"><i>Nota da comunidade:</i>  <b>{{ntComunidade}}</b></p></div>
                <div class="has-text-left"><p class="subtitle is-6"><i>Nota da Crítica:</i>  <b>{{ntCritica}}</b></p></div>
            </div>
            <div class="column is-one-fifth is-right has-text-right btn-container">
                <button type="button" class="button btn is-danger is-small" @click="emitirEventoDeletarJogo">Excluir</button>
                <button type="button" class="button btn is-primary is-small" @click="emitirEventoAbrirEditModal">Editar</button>
            </div>
            </div>
        </div>
        </div>

        <div v-if="isExpanded">
            <jogo-expanded :nome="nome" :descricao="descricao" @fechar="fecharExpanded" />
        </div>

    </div>

</template>

<script>
import JogoExpanded from './JogoExpanded.vue';
export default {
    data(){
        return {
            isExpanded: false
        }
    },
    components: {
        JogoExpanded,
    },
    props: {
        id: Number,
        nome: String,
        ntComunidade: Number,
        ntCritica: Number,
        descricao: String,
        imgUrl: String
    },
    methods: {
        emitirEventoAbrirEditModal: function(){
            this.$emit("editJogoModal", {id: this.id});
        },
        emitirEventoDeletarJogo: function(){

            if( window.confirm("Deseja realmente excluir este jogo?") )
                this.$emit("rmJogo", {id: this.id});
        },
        abrirExpanded: function(){
            this.isExpanded = true;
        },
        fecharExpanded: function(){
            this.isExpanded = false;
        }
    }
}
</script>

<style scoped>

#jogo-small {
    margin: auto;
}

.jogo-link:hover{
    text-decoration: underline;
}

.jogo-capa-small {
    height: 110px;
    width: 90px;
    border-radius: 5%;
}

.btn-container {
    height: 80px;
}

.btn {
    margin: 2%;
    width: 60px;
}

</style>