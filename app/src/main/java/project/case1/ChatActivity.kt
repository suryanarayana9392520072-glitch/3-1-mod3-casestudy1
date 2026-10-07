package project.case1

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import project.case1.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var viewModel: ChatViewModel
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        adapter = ChatAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.sendButton.setOnClickListener { sendMessage() }

        binding.inputEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage()
                true
            } else {
                false
            }
        }

        viewModel.messages.observe(this) { messages ->
            adapter.submitList(messages.toList())
            if (messages.isNotEmpty()) {
                binding.recyclerView.post {
                    binding.recyclerView.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }

        viewModel.error.observe(this) { message ->
            if (!message.isNullOrBlank()) {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                viewModel.clearError()
            }
        }

        if (viewModel.messages.value.isNullOrEmpty()) {
            viewModel.addBotMessage(getString(R.string.welcome_message))
        }
    }

    private fun sendMessage() {
        val message = binding.inputEditText.text?.toString()?.trim().orEmpty()
        if (message.isEmpty()) return

        binding.inputEditText.text?.clear()
        viewModel.addUserMessage(message)
        viewModel.sendMessage(message)
    }
}
