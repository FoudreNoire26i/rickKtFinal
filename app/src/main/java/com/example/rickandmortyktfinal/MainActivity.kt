package com.example.rickandmortyktfinal

import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyktfinal.fragment.CharacterListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .addToBackStack("CharacterListFragment")
            .add(R.id.fragment_container, CharacterListFragment.newInstance())
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        println("pop")
    }


}