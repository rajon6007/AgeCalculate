package com.mrpaul.agecalculation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var birthDateEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var ageResultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        birthDateEditText = findViewById(R.id.birthDateEditText)
        calculateButton = findViewById(R.id.calculateButton)
        ageResultText = findViewById(R.id.ageResultText)

        calculateButton.setOnClickListener {
            val birthDateString = birthDateEditText.text.toString()

            // Check if input is valid
            if (birthDateString.isNotEmpty()) {
                val age = calculateAge(birthDateString)
                ageResultText.text = "Your age is: $age years"
            } else {
                ageResultText.text = "Please enter a valid birthdate."
            }
        }
    }

    private fun calculateAge(birthDateString: String): Int {
        // Define the format for the birthdate
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val birthDate = format.parse(birthDateString)

        // Get the current date
        val currentDate = Calendar.getInstance().time

        // Calculate the age using Calendar
        val birthCalendar = Calendar.getInstance()
        birthCalendar.time = birthDate

        val currentCalendar = Calendar.getInstance()
        currentCalendar.time = currentDate

        var age = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

        // Check if the birth date hasn't occurred yet this year
        if (currentCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
            (currentCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                    currentCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
            age--
        }

        return age
    }
}