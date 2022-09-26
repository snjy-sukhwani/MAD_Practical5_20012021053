package com.example.mad_practical5_20012021053

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.mad_practical5_20012021053.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        var playerRunning =false
        var playPauseButtonCLicks =0
        var playlistMode = "S"
        var favouriteSong = false

        fun play(){
            Intent(applicationContext,MyService::class.java).putExtra(MyService.DATA_KEY,MyService.DATA_VALUE).apply { startService(this) }
        }

        fun stop() {
            Intent(applicationContext, MyService::class.java).apply { stopService(this) }
        }

        binding.playlistType.setOnClickListener{
            if(playerRunning){
                binding.playPauseSongBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_pause_24))
                play()
            }

            else{
                binding.playPauseSongBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_play_arrow_24))
            }
        }

        binding.previousSongBtn.setOnClickListener{

            Toast.makeText(this,"Player Status : $playerRunning",Toast.LENGTH_LONG).show()

            if(playerRunning){
                binding.playPauseSongBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_pause_24))
                stop()
                play()
            }

            else{
                binding.playPauseSongBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_play_arrow_24))
            }

            Toast.makeText(this,"Previous Song is Playing !!",Toast.LENGTH_LONG).show()
        }

        binding.playPauseSongBtn.setOnClickListener{
            if(playPauseButtonCLicks%2==0){
                playerRunning=true
                binding.playPauseSongBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_pause_24))
                Toast.makeText(this,"Song is Playing",Toast.LENGTH_LONG).show()
                play()
            }

            else{
                playerRunning=false
                binding.playPauseSongBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_play_arrow_24))
                Toast.makeText(this,"Song is Stopped",Toast.LENGTH_LONG).show()
                stop()
            }
            playPauseButtonCLicks++
        }

        binding.nextSongBtn.setOnClickListener{
            Toast.makeText(this,"Player Status : $playerRunning",Toast.LENGTH_LONG).show()

            if(playerRunning){
                binding.playPauseSongBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_pause_24))
                stop()
                play()
            }

            else{
                binding.playPauseSongBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_play_arrow_24))
            }
            Toast.makeText(this,"Next Song is Playing !!",Toast.LENGTH_LONG).show()
        }

        binding.playlist.setOnClickListener {
            Toast.makeText(this,"Playlist is Empty !!",Toast.LENGTH_LONG).show()
        }

        binding.favorite.setOnClickListener {
            favouriteSong = when(favouriteSong){
                true -> {
                    Toast.makeText(this,"Removed from Favorites",Toast.LENGTH_LONG).show()
                    binding.favorite.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24))
                    false
                }
                false ->{
                    Toast.makeText(this,"Added to Favorites",Toast.LENGTH_LONG).show()
                    binding.favorite.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24))
                    true
                }
            }
        }

        binding.playlistType.setOnClickListener {

            when (playlistMode) {
                "S" -> {
                    val msg = "You have Changed the Playlist Type : \nShuffle ---> Repeat"
                    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
                    playlistMode="R"
                    binding.playlistType.setImageDrawable(getDrawable(R.drawable.ic_baseline_repeat_24))
                }
                "R" -> {
                    val msg = "You have Changed the Playlist Type : \nRepeat ---> Repeat One"
                    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
                    playlistMode="R1"
                    binding.playlistType.setImageDrawable(getDrawable(R.drawable.ic_baseline_repeat_one_24))
                }
                "R1" -> {
                    val msg = "You have Changed the Playlist Type : \nRepeat One ---> Shuffle"
                    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
                    playlistMode="S"
                    binding.playlistType.setImageDrawable(getDrawable(R.drawable.ic_baseline_shuffle_24))
                }
            }
        }
    }
}