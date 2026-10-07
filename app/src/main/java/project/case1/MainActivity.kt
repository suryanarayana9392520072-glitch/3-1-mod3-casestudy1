package project.case1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.case1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
    }
}
