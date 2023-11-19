<script>
    import { isLoggedIn, user } from '../stores/AuthStore.js';
    
    let username = '';
    let password = '';
    let loading = false;
    let errorMessage = '';
   


    async function login() {
        loading = true;
        errorMessage = '';
        try {
            // Simulated delay for async login process
            await new Promise(resolve => setTimeout(resolve, 2000));

            // Here, you would typically make a fetch call to your backend service
            // For demonstration, we'll just mock the authentication
            if (username === 'user' && password === 'password') { // Replace with real validation
                user.set({ username });
                isLoggedIn.set(true);
            } else {
                errorMessage = 'Invalid credentials';
            }
        } catch (error) {
            errorMessage = 'Login failed. Please try again later.';
        } finally {
            loading = false;
        }
    }

    
</script>

<form on:submit|preventDefault={login} class="login-form">
    <div>
        <label for="username">Username:</label>
        <input id="username" type="text" bind:value={username} disabled={loading} />
    </div>
    <div>
        <label for="password">Password:</label>
        <input id="password" type="password" bind:value={password} disabled={loading} />
    </div>
    {#if errorMessage}
        <p class="error">{errorMessage}</p>
    {/if}
    <button type="submit" disabled={loading}>{loading ? 'Logging in...' : 'Login'}</button>

    
    
</form>

<style>
    /* This centers the form in the viewport */
    .login-form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        min-height: 100vh; /* Make the form container at least as tall as the viewport */
        padding: 0 20px; /* Add some padding on the sides */
    }

    /* Styles for the inputs and labels */
    .login-form > div {
        width: 100%; /* Full width */
        max-width: 300px; /* Maximum width of the inputs */
        margin-bottom: 15px; /* Space between the inputs */
    }

    /* Styles for the error message */
    .error {
        color: red;
        font-size: 0.9em;
        text-align: center; /* Center the error message */
        margin: 0 0 15px 0; /* Space below the error message */
    }

    /* Styles for the button */
    button {
        padding: 10px 15px;
        font-size: 1em;
        cursor: pointer;
        border: none;
        border-radius: 5px; /* Rounded corners for the button */
        background-color: #04AA6D;
        color: white;
        width: 100%; /* Full width */
        max-width: 300px; /* Maximum width of the button */
    }

    button:hover {
        opacity: 0.8;
    }

    /* The labels */
    label {
        display: block; /* Make labels block-level for better control */
        margin-bottom: 5px; /* Space below the label */
    }

    /* The inputs */
    input {
        width: 100%; /* Full width */
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px; /* Rounded corners for the inputs */
    }

    



</style>