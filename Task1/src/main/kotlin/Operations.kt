fun printCompletedTasks() {
    getTasksByStatus(StatusEnum.COMPLETED).forEach { task -> println(task) }
}

fun printNotStartedTasks() {
    getTasksByStatus(StatusEnum.TO_DO).forEach { task -> println(task) }

    while (true) {
        println("PRESS [1] to start task")
        println("PRESS [<] to go to main menu")

        val input = readln()

        when (input) {
            "1" -> {
                println("Choose task ID:")
                val taskId = readln().toInt()
                val result = changeStatus(taskId)
                println(result)
                break
            }
            "<" -> break
            else -> println ("Invalid command")
        }
    }
}

fun printExecutingTasks() {
    getTasksByStatus(StatusEnum.IN_PROGRESS).forEach { task -> println(task) }

    while (true) {
        println("PRESS [1] to complete task")
        println("PRESS [<] to go to main menu")

        val input = readln()

        when (input) {
            "1" -> {
                println("Choose task ID:")
                val taskId = readln().toInt()
                val result = changeStatus(taskId)
                println(result)
                break
            }
            "<" -> break
            else -> println ("Invalid command")
        }
    }
}

fun addNewTask() {
    println("Enter task name: ")
    val name = readln()

    print("Enter task description: ")
    val description = readln()

    print("Enter task weight: ")
    val points = readln().toInt()

    tasks.add(Task(id = tasks.size + 1, name = name, description = description, points = points, status = StatusEnum.TO_DO, assignedAt = "2026-10-13"))

    println("Task has been added successfully")
}