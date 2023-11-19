<script>
    import { isLoggedIn, user } from '../stores/AuthStore.js'; // Import the auth-related stores
    import Button from './Button.svelte';

    // This function will be called when the user clicks the "Sign Out" button
    function handleLogout() {
        user.set(null); // Clear the user store
        isLoggedIn.set(false); // Update the isLoggedIn store to reflect that the user is logged out
        // Add any other logout logic here, like redirecting to the login page or clearing local storage
    }
</script>

<nav class="navbar util">
    <h1 class="title-1">todo</h1>
    <div>
        <p class="username">{$user?.name || 'Guest'}</p>
        <!-- Add the on:click event handler to the button -->
        <Button btnType='secondary' on:click={handleLogout}>sign out</Button>
    </div>
</nav>

<style>
    .navbar{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .username{
        display: inline;
        color: var(--text-color);
    }

</style>