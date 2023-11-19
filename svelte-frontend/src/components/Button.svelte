<script>
    export let btnType = 'primary';
    export let isFull = false;
</script>

<button type="submit" class="btn {btnType}" class:full={isFull} on:click>
    <slot></slot>
</button>

<style>
    button{
        margin-top: 10px;
    }

    .full{
        width: 100%;
        padding: 10px 0;
        text-transform: uppercase;
    }

</style>