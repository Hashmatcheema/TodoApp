<script>
    import {createEventDispatcher} from 'svelte';
    import Button from './Button.svelte';
    import Alert from './Alert.svelte'
    import TodoStore from '../stores/TodoStore.js'

    import { v4 as uuid } from 'uuid';
  
    const dispatch = createEventDispatcher();

    let todo = {
        title:'',
        desc:'',
        completed:false
    }

    let showSuccessAlert = false;
    let showErrorAlert = false;
    let errMsg;

    const addNewTodo = (e) => {

        if(todo.title.trim() === '' || todo.desc.trim() === ''){
            showErrorAlert = true;
            errMsg = 'Todo title and description required!'

             // hide alert after 2s
            setTimeout(() => {
                showErrorAlert = false;
            }, 2000);

            return;
        }

        todo.id = uuid();

        TodoStore.update(currentTodos => {
            return [todo, ...currentTodos]
        })

        showSuccessAlert = true;
        errMsg = 'New todo item added successfuly!'

         // hide alert after 2s
        setTimeout(() => {
            showSuccessAlert= false;
        }, 2000);

        // reset inputs
            todo = {
            title:'',
            desc:'',
            completed:false
        }
    }
</script>
<div>
    <p class="title-2">Add new todo item</p>
    {#if showErrorAlert}
        <Alert success={false}>{errMsg}</Alert>
    {:else if showSuccessAlert}
        <Alert success={true}>{errMsg}</Alert>
    {/if}
    <form on:submit|preventDefault = {addNewTodo}>
        <div class="form-control">
            <label for="title">Title</label>
            <input type="text" id="title" bind:value={todo.title}>
        </div>
        <div class="form-control">
            <label for="desc">Description</label>
            <textarea cols="30" rows="10" id="desc" bind:value={todo.desc}></textarea>
        </div>
        <Button isFull=true>add todo</Button>
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
</style>

