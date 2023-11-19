<script>
    import {createEventDispatcher} from 'svelte';
    
    import Card from './Card.svelte';
    import Button from './Button.svelte'
    import Tag from './Tag.svelte'
    
    import TodoStore from '../stores/TodoStore.js'

    const dispatch = createEventDispatcher();

    export let todoList;
    


    const viewTodo = (id) => {
        dispatch('viewtodo',id);
    }

    const deleteTodo = (id) => {
        const result = todoList.filter(todo => id != todo.id);

        TodoStore.update(() => {return result});
    }

    const updateStatus = (todo, newStatus) => {
        TodoStore.update(todos => {
            return todos.map(t => {
                if (t.id === todo.id) {
                    return { ...t, status: newStatus };
                }
                return t;
            });
        });
    };
</script>
{#each todoList as todo }
    <Card>
        <div class="content">
            <p class="todo-title">{todo.title}</p>
            <div>
                <Button on:click={() => viewTodo(todo.id)}>View</Button>
                <Button btnType='secondary' on:click={() => deleteTodo(todo.id)}>Delete</Button>
                <select on:change={(event) => updateStatus(todo, event.target.value)}>
                    <option value="open" selected={todo.status === 'open'}>Open</option>
                    <option value="in-progress" selected={todo.status === 'in-progress'}>In Progress</option>
                    <option value="completed" selected={todo.status === 'completed'}>Completed</option>
                </select>
            </div>
        </div>
        <Tag status={todo.status}/>
    </Card>
{/each}


<style>
    .content{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .todo-title{
        font-weight: 500;
        text-transform: uppercase;
        margin-bottom: 5px;
    }
</style>
