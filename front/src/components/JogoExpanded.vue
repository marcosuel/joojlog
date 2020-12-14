<template>
    <div class="column is-half modal is-active" style="margin: auto;">
        
        <article class="message is-primary">
        <div class="message-header">
            <p>{{nome}}</p>
            <button @click="emitirEventoFechar" class="delete" aria-label="delete"></button>
        </div>
        <div class="message-body" style="min-height: 200px; min-width: 400px;">
            {{descricao}}            
        </div>
        </article>

    </div>
</template>

<script>
export default {
    props: {
        nome: String,
        descricao: String
    },
    methods: {
        emitirEventoFechar: function(){
            this.$emit("fechar");
        }
    }
}
</script>

<style scoped>

</style>