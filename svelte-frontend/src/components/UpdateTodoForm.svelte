<script>
    import {createEventDispatcher} from 'svelte';
    import Button from './Button.svelte';
    import Tag from './Tag.svelte';
    import Alert from './Alert.svelte';
    import TodoStore from '../stores/TodoStore.js';

    export let id;
    export let title;
    export let desc;
    export let completed;

    let showSuccessAlert = false;
    let showErrorAlert = false;
    let errMsg;

    let todo = {
        id,
        title,
        desc,
        completed
    }

    const updateTodo = () => {

        if(todo.title === title && todo.desc === desc && todo.completed === completed){
            showErrorAlert = true;
            errMsg = 'There are no values changed!';

            // hide alert after 2s
            setTimeout(() => {
                showErrorAlert = false;
            }, 2000);

            return;
        }

        const result = $TodoStore.filter(currentTodo => id != currentTodo.id);

        TodoStore.update(() => {
            return [todo, ...result]
        })
        
        showSuccessAlert = true;
        errMsg = 'Todo item updated successfuly!'

        // hide alert after 2s
        setTimeout(() => {
            showSuccessAlert= false;
        }, 2000);

    }
</script>
<div>
    <p class="title-2">Update todo item</p>
    {#if showErrorAlert}
        <Alert success={false}>{errMsg}</Alert>
    {:else if showSuccessAlert}
        <Alert success={true}>{errMsg}</Alert>
    {/if}
    <form on:submit|preventDefault = {updateTodo}>
        <div class="form-control">
            <label for="title">Title</label>
            <input type="text" id="title" bind:value={todo.title}>
        </div>
        <div class="form-control">
            <label for="desc">Description</label>
            <textarea cols="30" rows="10" id="desc" bind:value={todo.desc}></textarea>
        </div>

        <div class="form-control ">
            <label for="is-completed">Status</label>
            <div>
                <Tag complted={todo.completed}/>
                <input type="checkbox" id="is-completed" bind:checked={todo.completed} />
            </div>
        </div>

        <Button isFull=true>Update todo</Button>
    </form>
</div>

<style>

    form{
        margin-top: 10px;
    }

    .form-control{
         margin-bottom: 10px;
    }

    .form-control label{
        display: inline-block;
        font-weight: 500;
        margin-bottom: 10px;
    }

    input,textarea{
        width:100%;
        padding: 10px;
        border: 2px solid var(--border-color);
        border-radius: var(--radius);
        font-family: 'Montserrat', sans-serif;
        font-size: 14px;
        letter-spacing: 1px;
    }

    input[type='text']:focus,textarea:focus{
        outline: none;
        border:2px solid var(--primary-color);
    }

    input[type='checkbox']{
       cursor: pointer;
    }

    .form-control div{
        display: flex;
    }

</style>

