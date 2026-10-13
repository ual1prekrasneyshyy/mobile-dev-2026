suspend fun main() {

    while(true) {
        println("PRESS [1] to go to the task manager")
        println("PRESS [2] to view aircraft motion")
        println("PRESS [x] to exit")

        val section = readln()

        when (section) {
            "1" -> {
                while (true) {
                    println("U Task Manager")
                    println("Version 1.0.0")

                    println("PRESS [1] to view all tasks")
                    println("PRESS [2] to view completed tasks")
                    println("PRESS [3] to view executing tasks")
                    println("PRESS [4] to view not started tasks")
                    println("PRESS [5] to view success level")
                    println("PRESS [+] to add task")
                    println("PRESS [<] to go to main menu")

                    val choice = readln()

                    when (choice) {
                        "1" -> printAllTasks()
                        "2" -> printCompletedTasks()
                        "3" -> printExecutingTasks()
                        "4" -> printNotStartedTasks()
                        "5" -> println(getSuccessLevel())
                        "+" -> addNewTask()
                        "<" -> break
                        else -> println("Invalid choice. Try again")
                    }
                }
            }
            "2" -> simulateAircraftMotion()
            "X" -> {
                println("Good bye")
                break
            }
            else -> println("Invalid choice. Try again")
        }
    }
}

