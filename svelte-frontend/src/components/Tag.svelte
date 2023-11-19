<script>

     export let completed;

     let status;

    if(completed){
        status = 'completed'
    }
    else{
        status = 'pending'
    }


</script>
<div class="tag {status}">
    <small>{status}</small>
</div>

<style>
    .tag{
        display: flex;
        justify-content: center;
        align-items: center;
        width: fit-content;
        border-radius: 20px;
        padding: 0 10px;
        font-size: 12px;
    }

    .completed{
        background-color: var(--success-bg-color);
        color: var(--success-color);
        border: var(--success-border);
    }
    .pending{
        background-color: var(--error-bg-color);
        color: var(--error-color);
        border: var(--error-border);
    }
</style>