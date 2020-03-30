package com.flywith24.demo_lifecycle

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    lateinit var player: PlayManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = PlayManager(this, PlayStateListener())
    }

    inner class PlayStateListener {
        fun onPlayStateChanged(isPlaying: Boolean) {
            if (isPlaying) {
                //播放
                Toast.makeText(this@MainActivity, "播放", Toast.LENGTH_SHORT).show()
            } else {
                //暂停
                Toast.makeText(this@MainActivity, "暂停", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

