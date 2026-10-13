val tasks = mutableListOf<Task>(
    Task(1, "Presentation for Smart System", "Do presentation for the Smart System course for the midterm", "2026-10-06", 100, StatusEnum.TO_DO),
    Task(2, "TSIS for AI", "Do TSIS assignment for the Artificial Intelligence course as a midterm", "2026-09-25", 100, StatusEnum.TO_DO),
    Task(3, "Upload research paper to journal", "Send the result of scientific work of the mastery to the journal in order to complete master degree", "2026-09-07", 1000, StatusEnum.TO_DO),
    Task(4, "HW for Game programming course course", "Complete all three homeworks assigned for the Game programming patterns course", "2026-10-01", 100, StatusEnum.IN_PROGRESS),
    Task(5, "Integrate WhatsApp messenger to the Web Project", "Integrate sending verification code to the user of Web Project per employment", "2026-09-28", 20, StatusEnum.IN_PROGRESS),
    Task(6, "HW 2 for Web Dev", "Do 2nd homework assigned for the Web Development course", "2026-10-03", 50, StatusEnum.IN_PROGRESS),
    Task(7, "AI HW", "Do homework assignment fo the Artificial Intelligence course", "2026-10-05", 50, StatusEnum.IN_PROGRESS),
    Task(8, "HW for Mobile Development", "Do homework assignment for the Mobile Development courses", "2026-09-22", 50, StatusEnum.COMPLETED),
    Task(9, "Update design on Web Project", "Update design and user interface on several pages of Web project per employment", "2026-09-21", 20, StatusEnum.COMPLETED),
    Task(10, "Make call with customer", "Make a Zoom call with customer to discuss their preferences for further development", "2026-10-08", 20, StatusEnum.COMPLETED)
)

val getTasksByStatus: (StatusEnum) -> List<Task> = {status -> tasks.filter { it.status == status }}

val successLevelLowerBoundaries = mapOf(
    0 to "Low",
    100 to "Medium",
    300 to "Well",
    500 to "Great",
    800 to "Perfect"
)

fun getSuccessLevel(): String {
    val totalPoints = getTasksByStatus(StatusEnum.COMPLETED).map { it -> it.points }.reduce { acc, points ->  acc + points }

    successLevelLowerBoundaries.keys.sortedDescending().forEach { point ->
        if(totalPoints >= point) {
            return successLevelLowerBoundaries[point] ?: "No data"
        }
    }

    return "No data"
}

fun changeStatus(taskId: Int): String {
    val task = tasks.find { it.id == taskId }

    if(task == null) {
        return "Task not found!"
    }

    if(task.status == StatusEnum.TO_DO) {
        task.status = StatusEnum.IN_PROGRESS
        return "Task has been started"
    }

    if(task.status == StatusEnum.IN_PROGRESS) {
        task.status = StatusEnum.COMPLETED
        return "Task has been completed"
    }

    return "Task is already completed"
}
