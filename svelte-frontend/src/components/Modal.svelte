<script></script>
    
<div class="modal-backdrop">
    <div class="modal">
        <div class="close-modal btn primary" on:click>
            <h3>x</h3>
        </div>
        <slot></slot>   
    </div>
</div>

<style>
    .modal-backdrop {
		background-color: rgba(0, 0, 0, 0.5);
		display: grid;
		place-items: center;
		position: fixed;
		top: 0;
		left: 0;
		width: 100vw;
		height: 100vh;
        z-index: 3;
	}

	.modal {
        position: relative;
        width: 540px;
		padding: 30px 40px;
		background-color: var(--white-color);
		box-shadow: 1px 1px 4px rgba(0,0,0,0.3);
		border-radius: var(--radius);
        z-index: 5;
	}

    .close-modal{
        position: absolute;
        top:0;
        right: 0;
        width: fit-content;
        padding: 5px 15px;
        border-radius: 0;
        border-top-right-radius: var(--radius);
        border-bottom-left-radius:  var(--radius);
    }
</style>