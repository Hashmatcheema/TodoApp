<script>
	import Header from "./components/Header.svelte";
    import Sidebar from "./components/Sidebar.svelte";
    import Modal from "./components/Modal.svelte";
    import Todo from "./components/Todo.svelte";
    import FloatingButton from './components/FloatingButton.svelte';
    import AddTodoForm from "./components/AddTodoForm.svelte";
    import UpdateTodoForm from './components/UpdateTodoForm.svelte';
    import Alert from './components/Alert.svelte';
    import Login from './components/Login.svelte';
    import Signup from './components/Signup.svelte';
	import TeamLeaderLogin from './components/TeamLeaderLogin.svelte';
    import TodoStore from './stores/TodoStore.js';
    import { user, isLoggedIn } from './stores/AuthStore.js';


const routes = {

    '/TeamLeaderLogin': TeamLeaderLogin,
};
function gotoTeamLeaderLogin() {
    console.log('Navigating to Team Leader Login');
    push('/TeamLeaderLogin');
}

	let showLogin = true; 
    let showSignup = false;
	let isModalActive = false;
	let modalType;
	let showTeamLeaderLogin = false;
	let singleTodo;
	let todoList;
	
	$: todoList= $TodoStore;

	let contentTitle = 'all todo items';

	

	function toggleAuth() {
        showLogin = !showLogin;
    }

	function handleBack() {
        showLogin = true;
        showSignup = false;
        showTeamLeaderLogin = false;
    }

	const toggleTeamLeaderLogin = () => {
        showLogin = false;
        showSignup = false;
        showTeamLeaderLogin = true;
    };

	const toggleLogin = () => {
    showLogin = true;
    showSignup = false;
    showTeamLeaderLogin = false;
};

const toggleUserLogin = () => {
    showLogin = true;
    showSignup = false;
    showTeamLeaderLogin = false;
};

    const toggleSignup = () => {
        showLogin = false;
        showSignup = true;
    };

    const logout = () => {
        user.set(null);
        isLoggedIn.set(false);
    };

	const toggleModal = (type) => {

		modalType = type;
		isModalActive = !isModalActive
	}

	const viewTodoDetails = (e) => {
		toggleModal('viewtodo');
		const id = e.detail;
		singleTodo = todoList.find(todo => todo.id === id);
	}

	const viewCompletedTodo = () => {
		contentTitle = 'completed todo items'
		const completedTodo = $TodoStore.filter(todo => todo.completed === true)

		todoList = completedTodo;

	}

	const viewAllTodo = () => {
		contentTitle = 'all todo items';
		todoList = $TodoStore;
	}
	 
</script>

<main>
    {#if $isLoggedIn}
	{#if isModalActive}
	{#if modalType === 'addtodo'}
		<Modal on:click={toggleModal}>
			<AddTodoForm />
		</Modal>
	{:else if modalType === 'viewtodo'}
		<Modal on:click={toggleModal}>
			<UpdateTodoForm {...singleTodo}/>
		</Modal>
	{/if}
	{/if}

        <Header on:logout={logout}/>
        <section class="content-box">
			<Sidebar on:viewcompletedtodo={viewCompletedTodo} on:viewalltodo={viewAllTodo} />
			<div class="todo-content util">
				{#if todoList.length === 0}
					<h2>There are no todo items found!</h2>
				{:else}	
					<p class="title-2">{contentTitle}</p>		
					<Todo on:viewtodo={viewTodoDetails} {todoList}/>		
				{/if}
			</div>
		<FloatingButton on:click={() => {toggleModal('addtodo')}}>
				<h1>+</h1>
			</FloatingButton>
		</section>
		{:else}
        {#if showLogin}
            <Login />
            <div class="toggle-auth">
                <p>Don't have an account? <button on:click={toggleSignup}>Sign up</button></p>
                <p>Are you a Team Leader? <button on:click={toggleTeamLeaderLogin}>Team Leader Login</button></p>
            </div>
        {:else if showSignup}
            <Signup />
            <div class="toggle-auth">
                <p>Already have an account? <button on:click={toggleLogin}>Login</button></p>
            </div>
        {:else if showTeamLeaderLogin}
		<div class="team-leader-button">
			<TeamLeaderLogin on:back={handleBack} />
        <div class="back"><button on:click={handleBack}>Back</button></div>
		</div>
        
    {/if}
    {/if}
</main>


<style>
	
.back{
	padding: 10px 5px;
        font-size: 1em;
        cursor: pointer;
        border: none;
        border-radius: 5px; /* Rounded corners for the button */
        background-color: #04AA6D;
        color: white;
        width: 50%; /* Full width */
        max-width: 100px; 
		text-align: center;
}

button:hover {
        opacity: 0.8;
    }
.team-leader-button {
    margin-top: 15px; /* Space above the button */
    text-align: center; /* Center the button */
}
.team-leader-button button {
    background-color: #007bff; /* Different color for distinction */
    color: white;
}

.team-leader-button button:hover {
    background-color: #0056b3; /* Darker shade on hover */
}
	.content-box{
		display: grid;
		grid-template-areas: 'sidebar content content content';
		gap: 10px;
		margin-top: 10px;
		height: calc(100vh - 101px );
	}	

	.todo-content{
		grid-area: content;
		height: 100%;
        overflow: hidden;
	}

	.todo-content h2{
		text-align: center;
		margin-top: 20%;
	}
    	
	.toggle-auth {
        text-align: center; /* Center the toggle message and button */
        margin-top: 1rem; /* Space above the toggle message */
        margin-bottom: 10rem; /* Space below the toggle message */
    }

    .toggle-auth p {
        font-size: 0.9rem; /* Slightly smaller font size for the toggle message */
        color: #666; /* A more subtle color for the text */
    }

    .toggle-auth button {
        background-color: transparent; /* Transparent background to blend with any background */
        border: none; /* No border for a text-like button */
        color: #007bff; /* Interactive color for the button */
        padding: 0.5rem; /* Padding for better clickability */
        font-weight: bold; /* Bold font weight for emphasis */
        text-decoration: underline; /* Underline to signify interactiveness */
        cursor: pointer; /* Pointer cursor on hover */
        font-size: 0.9rem; /* Match the font size of the message */
        margin-top: 0.5rem; /* Space above the button */
    }

    .toggle-auth button:hover,
    .toggle-auth button:focus {
        color: #0056b3; /* Darker color on hover/focus for better user feedback */
        outline: none; /* No outline on focus to keep custom styles */
        text-decoration: none; /* Removing underline on hover for a clean look */
    }
</style>