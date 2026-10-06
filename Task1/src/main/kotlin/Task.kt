enum class StatusEnum {
    TO_DO,
    IN_PROGRESS,
    COMPLETED
}

class Task (
    val id: Int,
    val name: String,
    val description: String,
    val assignedAt: String,
    val status: StatusEnum
)