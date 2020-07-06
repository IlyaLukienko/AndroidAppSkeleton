package com.lukienko.androidappskeleton.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.lukienko.androidappskeleton.R
import com.lukienko.androidappskeleton.core.Utils.Companion.getName
import com.lukienko.androidappskeleton.data.CharacterResult
import com.lukienko.androidappskeleton.presentation.CharacterFragment.Companion.newInstance
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var adapter: CharactersAdapter? = null
    private var listview: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun onSuccessResult(characterResponse: Response<CharacterResult>) {
        listview = findViewById<View>(R.id.characters_list) as ListView
        adapter = CharactersAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            getName(characterResponse)
                .toTypedArray<String?>()
        )
        listview!!.adapter = adapter
        listview!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val name = parent.getItemAtPosition(position) as String
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, newInstance(name))
                .commit()
        }
    }
}