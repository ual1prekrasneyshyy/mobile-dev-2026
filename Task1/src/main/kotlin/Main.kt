fun main() {
    println("U Task Manager")
    println("Version 1.0.0")

    while (true) {
        println("PRESS [1] to view all tasks")
        println("PRESS [2] to view completed tasks")
        println("PRESS [3] to view executing tasks")
        println("PRESS [4] to view not started tasks")
        println("PRESS [5] to view success level")
        println("PRESS [+] to add task")
        println("PRESS [X] to exit")


        val choice = readln()

        when (choice) {
            "1" -> println(tasks)
            "2" -> printCompletedTasks()
            "3" -> printExecutingTasks()
            "4" -> printNotStartedTasks()
            "5" -> println(getSuccessLevel())
            "+" -> addNewTask()
            "X" -> {
                println("Good bye")
                break
            }
            else -> println("Invalid choice")
        }
    }
}

