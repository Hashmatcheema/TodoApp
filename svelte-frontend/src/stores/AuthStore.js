// src/stores/AuthStore.js
import { writable } from 'svelte/store';

export const user = writable(null); // This will hold the user object once logged in
export const isLoggedIn = writable(false); // This will be true when the user is logged in
