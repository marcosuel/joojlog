<template>
    
<div>
    <div :class="isActive ? 'modal is-active' : 'modal' ">
        <div class="modal-background"></div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">{{ isAdd ? 'Novo Jogo' : 'Atualizar Jogo' }}</p>
                <button class="delete" aria-label="close" @click="fecharModal"></button>
            </header>
            <section class="modal-card-body">
                <div v-show="erros.length" class="column is-fullwidth error-container" >
                    <div v-for="erro in erros" :key="erro.nome">
                        <p class="error-text" >{{erro.nome}}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label espaco-top">Imagem</label>
                    <input class="input" type="text" placeholder="Digite o link da imagem" v-model="jogo.imgUrl">

                    <label class="label espaco-top">Nome</label>
                    <input class="input" type="text" placeholder="Digite o nome" v-model="jogo.nome">
                    
                    <label class="label espaco-top">Descrição</label>
                    <textarea class="textarea" placeholder="Digite a descrição" v-model="jogo.descricao"></textarea>

                    <label class="label espaco-top">Nota da crítica</label>
                    <input class="input" type="text" placeholder="Digite uma nota entre 0 e 100" v-model.number="jogo.nt_critica">

                    <label class="label espaco-top">Nota da comunidade</label>
                    <input class="input" type="text" placeholder="Digite uma nota entre 0 e 100" v-model.number="jogo.nt_comunidade">
                </div>
            </section>
            <footer class="modal-card-foot">
                <button class="button is-success" @click="validarForm">{{ isAdd ? 'Adicionar' : 'Salvar mudanças' }}</button>
                <button class="button is-danger" @click="fecharModal">Cancelar</button>
            </footer>
        </div>
    </div>
    

</div>

</template>

<script>
import axios from 'axios';

export default {
    watch: {
        isActive: function(){
            this.erros = [];
            if(this.jogoId != -1) {
                axios.get("http://localhost:8080/api/jogos/"+this.jogoId).then(res => {
                    this.jogo = res.data;
                }).catch(err => {
                    console.warn(err);
                })
            }
        }
    },
    data(){
        return{
            jogo: {},
            erros: []
            //jogo: {id: "", nome: "", descricao: "", nt_critica: "", nt_comunidade: ""},
        }
    },
    props: {
        jogoId: Number,
        isActive: Boolean,
        isAdd: Boolean
    },
    methods: {
        fecharModal: function(){
            this.$emit("fechaFormModal");
            this.jogo = {};
        },
        validarForm: function(){
            this.erros = [];

            if(!this.jogo.imgUrl.trim())    this.erros.push({nome: "O link não pode estar vazio."});
            if(!this.jogo.nome.trim())    this.erros.push({nome: "O nome não pode estar vazio."});
            if(!this.jogo.descricao.trim())    this.erros.push({nome: "A descrição não pode estar vazia."});

            if(     !this.jogo.nt_critica.toString().trim()
                    || isNaN(this.jogo.nt_critica) 
                    || this.jogo.nt_critica < 0 
                    || this.jogo.nt_critica > 100
                    || !this.jogo.nt_comunidade.toString().trim()
                    || isNaN(this.jogo.nt_comunidade) 
                    || this.jogo.nt_comunidade < 0 
                    || this.jogo.nt_comunidade > 100)
            {
                this.erros.push({nome: "A nota deve ser um número de 0 a 100."});
            }            

            if(!this.erros.length)   this.emitirEvento();
        },
        emitirEvento: function(){
            if(this.isAdd) {
                this.$emit("addJogo", {jogo: this.jogo});
            } else {
                this.jogo.id = this.jogoId;
                this.$emit("attJogo", {jogo: this.jogo});
            }
        },
    }
}
</script>

<style scoped>

.espaco-top {
    margin-top: 2%;
}

.error-container {
    background-color: #f1466862; 
    border-radius: 5px;
}

.error-text {
    font-size: 18px;
}

</style>