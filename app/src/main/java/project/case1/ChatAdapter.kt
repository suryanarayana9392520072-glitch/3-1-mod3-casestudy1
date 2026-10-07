package project.case1

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.case1.models.ChatMessage

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.MessageHolder>() {

    private var items: List<ChatMessage> = emptyList()

    fun submitList(messages: List<ChatMessage>) {
        items = messages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val textView = TextView(parent.context).apply {
            textSize = 16f
            setPadding(24, 18, 24, 18)
            layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return MessageHolder(textView)
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val item = items[position]
        holder.textView.text =
            if (item.isUser) "You: ${item.text}" else "AI: ${item.text}"
        holder.textView.setTypeface(null, if (item.isUser) Typeface.BOLD else Typeface.NORMAL)
    }

    override fun getItemCount(): Int = items.size

    class MessageHolder(val textView: TextView) :
        RecyclerView.ViewHolder(textView)
}
