<script>
    import { onMount } from 'svelte';
    import { userRolesStore } from '../stores.js';
    import { user } from './stores/AuthStore.js';
    import { push } from 'svelte-spa-router';    
    
    let users = [];
   

    onMount(() => {
        $user.subscribe(value => {
            if (!value || value.role !== 'teamLeader') {
                push('/'); // Redirect to home if not a team leader
            }
        });
    });
   
    async function updateRole(userId, newRole) {
        const response = await fetch(`/api/users/${userId}/role`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ role: newRole })
        });

        if (response.ok) {
            // Update the local state to reflect the change
            const updatedUser = users.find(user => user.id === userId);
            updatedUser.role = newRole;
        } else {
            // Handle error
        }
    }
</script>

<style>
    .dashboard {
        max-width: 800px;
        margin: auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2);
    }

    .user {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 10px;
        border-bottom: 1px solid #eee;
    }

    .user:last-child {
        border-bottom: none;
    }

    .user > span {
        font-size: 1rem;
        color: #333;
    }

    select {
        padding: 8px;
        border-radius: 4px;
        border: 1px solid #ccc;
    }

    /* Add other styles here to match the theme of your app */
</style>

<div class="dashboard">
    <h2>Team Leader Dashboard</h2>
    {#each users as user}
        <div class="user">
            <span>{user.name}</span>
            <select bind:value={user.role} on:change={() => updateRole(user.id, user.role)}>
                {#each Object.keys(userRolesStore) as role}
                    <option value={role}>{role}</option>
                {/each}
            </select>
        </div>
    {/each}
</div>
