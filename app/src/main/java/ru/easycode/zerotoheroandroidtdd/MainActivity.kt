package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var count = Count.Base(2, 4, 0)

    lateinit var uiState: UiState
    lateinit var incrementButton: Button
    lateinit var decrementButton: Button
    lateinit var linearLayout: LinearLayout
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)
        textView = findViewById(R.id.countTextView)
        linearLayout = findViewById(R.id.rootLayout)



        incrementButton.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(decrementButton, incrementButton, textView)
        }

        decrementButton.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.apply(decrementButton, incrementButton, textView)
        }

        if (savedInstanceState == null) {
            uiState = count.initial(textView.text.toString())
            uiState.apply(decrementButton, incrementButton, textView)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key", uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = savedInstanceState.getSerializable("key") as UiState
        uiState.apply(decrementButton, incrementButton, textView)
    }
}