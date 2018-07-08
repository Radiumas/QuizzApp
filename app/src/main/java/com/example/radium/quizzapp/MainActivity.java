package com.example.radium.quizzapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variable for the result showing correct answers
    int score = 0;
    // Variable for a toast message
    String message;
    // Variable for username input
    EditText nameInput;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt("SCORE", score);
        outState.putString("MESSAGE", message);
    }

    /**
     * Saves values if screen rotated
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt("SCORE");
        message = savedInstanceState.getString("MESSAGE");
    }

    // Adds points for questions 1-8
    private void addForRadio() {
        // ---Variables for correct answer buttons
        RadioButton buttonQ1 = findViewById(R.id.correct_Q1_button);
        RadioButton buttonQ2 = findViewById(R.id.correct_Q2_button);
        RadioButton buttonQ3 = findViewById(R.id.correct_Q3_button);
        RadioButton buttonQ4 = findViewById(R.id.correct_Q4_button);
        RadioButton buttonQ5 = findViewById(R.id.correct_Q5_button);
        RadioButton buttonQ6 = findViewById(R.id.correct_Q6_button);
        RadioButton buttonQ7 = findViewById(R.id.correct_Q7_button);
        RadioButton buttonQ8 = findViewById(R.id.correct_Q8_button);

        boolean correctQ1 = buttonQ1.isChecked();
        if (correctQ1) {
            score++;
        }

        boolean correctQ2 = buttonQ2.isChecked();
        if (correctQ2) {
            score++;
        }

        boolean correctQ3 = buttonQ3.isChecked();
        if (correctQ3) {
            score++;
        }

        boolean correctQ4 = buttonQ4.isChecked();
        if (correctQ4) {
            score++;
        }

        boolean correctQ5 = buttonQ5.isChecked();
        if (correctQ5) {
            score++;
        }

        boolean correctQ6 = buttonQ6.isChecked();
        if (correctQ6) {
            score++;
        }

        boolean correctQ7 = buttonQ7.isChecked();
        if (correctQ7) {
            score++;
        }

        boolean correctQ8 = buttonQ8.isChecked();
        if (correctQ8) {
            score++;
        }
    }

    // add points for type in question

    private void addForTypeIn() {
        EditText typeIn = findViewById(R.id.type_in_question);
        String answer = typeIn.getText().toString();
        if (answer.equals("Java") || answer.equals("java")) {
            score++;
        }
    }

    // Evaluates whether the extra question was answered correctly
    private String checkForExtra() {
        String extraMessage;
        CheckBox checkBoxYellow = findViewById(R.id.yellow_checkbox);
        CheckBox checkBoxGreen = findViewById(R.id.green_checkbox);
        CheckBox checkBoxRed = findViewById(R.id.red_checkbox);
        CheckBox checkBoxBlue = findViewById(R.id.blue_checkbox);
        CheckBox checkBoxBlack = findViewById(R.id.black_checkbox);
        CheckBox checkBoxWhite = findViewById(R.id.white_checkbox);
        boolean extra = false;
        if ((!checkBoxBlack.isChecked()) && (!checkBoxBlue.isChecked()) && (!checkBoxWhite.isChecked()) &&
                (checkBoxRed.isChecked()) && (checkBoxGreen.isChecked()) && (checkBoxYellow.isChecked())) {
            extra = true;
            score++;
        }
        extraMessage = chooseExtra(extra);
        return extraMessage;
    }

    private String chooseExtra(boolean extra) {
        String extraMessage;
        if (extra) {
            extraMessage = getResources().getString(R.string.right_extra);
        } else {
            extraMessage = getResources().getString(R.string.wrong_extra);
        }
        return extraMessage;
    }
    // toast messages for different points


    public void toastOutput() {

        nameInput = findViewById(R.id.name_input);
        name = nameInput.getText().toString();

        if (score == 9) {
            message = name + ", " + getResources().getString(R.string.right_all)
                  + " "  + checkForExtra();
        } else {
            message = name + ", " + score + " " + getResources().getString(R.string.right_not_all)
                  + " "  + checkForExtra();
        }
    }

    // Calculates the score when button DONE is clicked
    public void calculateScore(View view) {
        addForRadio();
        addForTypeIn();
        checkForExtra();
        toastOutput();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
        score = 0;
    }
}
