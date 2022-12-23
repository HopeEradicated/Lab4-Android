package com.example.album

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.album.overview.OverviewViewModel
import com.example.album.overview.OverviewViewModelSecond

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val editID: EditText = findViewById(R.id.enterID)
        val searchID: Button = findViewById(R.id.searchID)
        searchID.setOnClickListener {
            OverviewViewModel.id = editID.text.toString();
            setContentView(R.layout.activity_main)
            val searID: Button = findViewById(R.id.sea)
            searID.setOnClickListener {
                OverviewViewModelSecond.id = OverviewViewModel.id;
                setContentView(R.layout.activity_second)
            }
        }
    }
}