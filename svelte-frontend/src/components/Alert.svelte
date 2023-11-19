<script>

    export let success;    
    let alertType;

    if(success){
        alertType = 'success'
    }else{
        alertType = 'error'
    }

</script>

<div class = "alert {alertType}">
    <slot></slot>
</div>

<style>
    .alert{
		border-radius: 4px;
		padding: 10px;
		font-size: 12px;
		font-weight: 700;
		margin: 10px 0;
		letter-spacing: 2px;
	}
    .success{
        background-color: var(--success-bg-color);
        color: var(--success-color);
        border: var(--success-border);
    }
    .error{
        background-color: var(--error-bg-color);
        color: var(--error-color);
        border: var(--error-border);
    }
</style>