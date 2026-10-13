enum class StatusEnum {
    TO_DO,
    IN_PROGRESS,
    COMPLETED
}

data class Task (
    val id: Int,
    val name: String,
    val description: String,
    val assignedAt: String,
    val points: Int,
    var status: StatusEnum,
)