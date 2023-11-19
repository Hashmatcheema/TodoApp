<script>
    import {createEventDispatcher} from 'svelte'
    import TodoStore from '../stores/TodoStore.js'

    const dispatch = createEventDispatcher();

     const allTodoCount = $TodoStore.length

     const completedTodoCount = $TodoStore.filter( todo => todo.completed === true).length;

    const viewCompletedTodo = () =>{
        dispatch('viewcompletedtodo');
     }
    const viewAllTodo = () =>{
        dispatch('viewalltodo');
     }

</script>
<aside class="util">
    <div>
        <i class="fas fa-list fa-lg"></i>
        <p class="title-2">dashboard</p>

        <div class="categories">
            <div class="category" on:click={viewAllTodo}>
                <p>all todos</p>
                <div class="count">{allTodoCount}</div>
            </div>
            <div class="category" on:click={viewCompletedTodo}>
                <p>completed todos</p>
                <div class="count">{completedTodoCount}</div>
            </div>
        </div>
    </div>
</aside>
<style>
    aside{
        grid-area: sidebar;
        height: 100%;
    }

    .categories{
        margin-top: 10px;
        margin-left: 10px;
    }

    .category{
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-weight: 500;
        text-transform: uppercase;
        padding: 8px;
        cursor: pointer;
    }

    .category:hover{
        background-color: var(--bg-color);
    }

    .count{
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: var(--primary-color);
        border-radius: var(--radius);
        color: var(--white-color);
        font-size: 12px;
        height: 24px;
        width: 34px;
        padding: 0 12px;
    }

</style>