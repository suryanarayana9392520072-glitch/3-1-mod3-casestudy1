package project.case1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import project.case1.models.ChatMessage
import project.case1.network.RetrofitClient

class ChatViewModel : ViewModel() {

    private val _messages = MutableLiveData<MutableList<ChatMessage>>(mutableListOf())
    val messages: LiveData<MutableList<ChatMessage>> = _messages

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val api = RetrofitClient.api

    fun addUserMessage(text: String) {
        append(ChatMessage(text, true))
    }

    fun addBotMessage(text: String) {
        append(ChatMessage(text, false))
    }

    private fun append(message: ChatMessage) {
        val updated = (_messages.value ?: mutableListOf()).toMutableList()
        updated.add(message)
        _messages.value = updated
    }

    fun clearError() {
        _error.value = null
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                val response = api.sendMessage(
                    message = message,
                    sessionId = "case1-android-session"
                )

                if (!response.error.isNullOrBlank()) {
                    _error.value = response.error
                } else {
                    addBotMessage(
                        response.response.ifBlank {
                            "The Python server returned an empty response."
                        }
                    )
                }
            } catch (e: Exception) {
                _error.value =
                    "Cannot connect to Python backend. Start the server on port 5000."
            }
        }
    }
}
