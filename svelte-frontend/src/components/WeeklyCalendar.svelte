<script>
    import { startOfWeek, eachDayOfInterval, format } from 'date-fns';
    import Task from './Task.svelte';

    let currentDate = new Date();
    let startWeek = startOfWeek(currentDate, { weekStartsOn: 1 });
    let endWeek = new Date(startWeek);
    endWeek.setDate(startWeek.getDate() + 6);

    $: weekDays = eachDayOfInterval({
        start: startWeek,
        end: endWeek
    });

    const goToPreviousWeek = () => {
        currentDate = new Date(currentDate.setDate(currentDate.getDate() - 7));
        updateWeek();
    };

    const goToNextWeek = () => {
        currentDate = new Date(currentDate.setDate(currentDate.getDate() + 7));
        updateWeek();
    };

    const updateWeek = () => {
        startWeek = startOfWeek(currentDate, { weekStartsOn: 1 });
        endWeek = new Date(startWeek);
        endWeek.setDate(startWeek.getDate() + 6);
        weekDays = eachDayOfInterval({ start: startWeek, end: endWeek });
    };

    export let tasks = [];
</script>

<div class="weekly-calendar">
    <button on:click={goToPreviousWeek}>Previous Week</button>
    <button on:click={goToNextWeek}>Next Week</button>
    {#each weekDays as day}
        <div class="day">
            <h3>{format(day, 'EEEE')}</h3>
            <div class="tasks">
                {#each tasks.filter(t => isSameDay(new Date(t.dueDate), day)) as task}
                    <Task {task} />
                {/each}
            </div>
        </div>
    {/each}
</div>

<style>
    .weekly-calendar {
        display: grid;
        grid-template-columns: repeat(7, 1fr);
        gap: 1rem;
    }
    .day {
        padding: 10px;
        border: 1px solid #ccc;
        min-height: 100px; /* Adjust as needed */
    }
    .tasks {
        margin-top: 10px;
    }
    /* Add more styles as needed */
</style>
