package project.case1.models

data class ChatResponse(
    val response: String = "",
    val session_id: String? = null,
    val error: String? = null
)
