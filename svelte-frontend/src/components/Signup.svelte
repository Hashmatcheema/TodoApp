<script>
    import { user, isLoggedIn } from '../stores/AuthStore.js';
    
    let username = '';
    let email = '';
    let password = '';
    let confirmPassword = '';
    let loading = false;
    let errorMessage = '';

    let usernameError = '';
    let emailError = '';
    let passwordError = '';
    let confirmPasswordError = '';

    // Reactive statement to check if all fields are filled
    $: isFormValid = username.trim() && email.trim() && password.trim() && confirmPassword.trim() && password === confirmPassword;

    async function signUp() {
        // Reset error messages
        errorMessage = '';
        usernameError = '';
        emailError = '';
        passwordError = '';
        confirmPasswordError = '';

        // Check if all fields are filled
        if (!username.trim()) {
            usernameError = 'Username is required.';
        }
        if (!email.trim()) {
            emailError = 'Email is required.';
        }
        if (!password.trim()) {
            passwordError = 'Password is required.';
        }
        if (!confirmPassword.trim()) {
            confirmPasswordError = 'Confirm password is required.';
        }
        if (password !== confirmPassword) {
            errorMessage = 'Passwords do not match.';
            return;
        }

        if (!usernameError && !emailError && !passwordError && !confirmPasswordError) {
            loading = true;

            try {
                // Simulated delay for async sign-up process
                await new Promise(resolve => setTimeout(resolve, 2000));

                try {
    const response = await fetch('https://yourapi.com/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            email: email,
            password: password
        })
    });

    const data = await response.json();

    if (response.ok) {
        // Handle successful sign-up
        // e.g., updating user state, redirecting, etc.
        user.set({ username, email });
        isLoggedIn.set(true);
    } else {
        // Handle errors (e.g., user already exists, server error, etc.)
        errorMessage = data.message || 'Sign up failed. Please try again later.';
    }
} catch (error) {
    errorMessage = 'There was a problem with the sign-up process.';
}


                user.set({ username, email });
                isLoggedIn.set(true);
            } catch (error) {
                errorMessage = 'Sign up failed. Please try again later.';
            } finally {
                loading = false;
            }
        }
    }
</script>

<body>
<form on:submit|preventDefault={signUp}>
    <div class="form-group">
        <label for="username">Username:</label>
        <input id="username" type="text" bind:value={username} disabled={loading} />
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input id="email" type="email" bind:value={email} disabled={loading} />
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input id="password" type="password" bind:value={password} disabled={loading} />
    </div>
    <div class="form-group">
        <label for="confirmPassword">Confirm Password:</label>
        <input id="confirmPassword" type="password" bind:value={confirmPassword} disabled={loading} />
    </div>
    {#if errorMessage}
        <p class="error">{errorMessage}</p>
    {/if}
    <div class=signup><button type="submit" disabled={loading}>{loading ? 'Signing Up...' : 'Sign Up'}</button></div>
</form></body>
<style>
    .form-group {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 1em;
    }

    label {
        margin-bottom: 0.5em;
    }

    input {
        padding: 0.5em;
        font-size: 1em;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: calc(100% - 1em); /* Adjust input width considering padding */
    }

    .error {
        color: red;
        font-size: 0.9em;
        margin-top: 0.5em;
        text-align: center;
    }

    .signup {
        display: flex;
        justify-content: center; /* This will center the button horizontally */
        width: 100%; /* Set width to 100% of its parent */
    }

    button {
        padding: 0.5em 1em; /* Adjust padding to make button larger */
        font-size: 1em;
        cursor: pointer;
        border: none;
        border-radius: 4px;
        background-color: #04AA6D; /* A nice shade of green */
        color: white;
        transition: background-color 0.3s; /* Smooth transition for hover effect */
        outline: none; /* Remove the default focus outline */
    }

    button:hover {
        background-color: #037d56; /* Darker shade on hover */
    }

    /* Ensure the form itself is centered in the page */
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        margin: 0; /* Remove default body margin */
    }

    form {
        width: 100%;
        max-width: 320px; /* Maximum form width */
        padding: 20px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* Subtle shadow for depth */
        background: white; /* White background for the form */
        border-radius: 8px; /* Rounded corners for the form */
    }
</style>
