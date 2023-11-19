import { writable } from 'svelte/store';

const TodoStore = writable([
  {
    id: 1,
    title: 'Todo Item 1',
    desc: 'This is a todo title one',
    completed: false,
  },
  {
    id: 2,
    title: 'Todo Item 2',
    desc: 'This is a todo title two',
    completed: true,
  },
  {
    id: 3,
    title: 'Todo Item 3',
    desc: 'This is a todo title three',
    completed: false,
  },
]);

export default TodoStore;
