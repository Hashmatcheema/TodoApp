<script>

</script>

<div class="floating-btn primary" on:click>
    <slot></slot>
</div>

<style>	
    .floating-btn{
        position: absolute;
        bottom: 30px;
        right: 30px;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        cursor: pointer;
        z-index: 2;
    }
</style>