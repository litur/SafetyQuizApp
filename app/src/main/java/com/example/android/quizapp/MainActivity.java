package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox myCheckbox1A;
    CheckBox myCheckbox1B;
    CheckBox myCheckbox1C;
    RadioButton myRadioButton2A;
    RadioButton myRadioButton2B;
    RadioButton myRadioButton2C;
    CheckBox myCheckbox3A;
    CheckBox myCheckbox3B;
    CheckBox myCheckbox3C;
    EditText myUsernameInput;
    EditText myCompanynameInput;
    EditText myEditText4a;
    Spinner mySpinner;
    CheckBox myCheckbox6A;
    CheckBox myCheckbox6B;
    CheckBox myCheckbox6C;

    TextView myTextViewQ1;
    TextView myTextViewQ2;
    TextView myTextViewQ3;
    TextView myTextViewQ4;
    TextView myTextViewQ5;
    TextView myTextViewQ6;


    public static int MIN_SCORE = 6;
    public static String EMERGENCY_PHONE_NUMBER = "288";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myUsernameInput = findViewById (R.id.usernameInput);
        myCompanynameInput = findViewById (R.id.companyNameInput);

        myCheckbox1A = findViewById(R.id.checkbox1A);
        myCheckbox1B = findViewById(R.id.checkbox1B);
        myCheckbox1C = findViewById(R.id.checkbox1C);

        myRadioButton2A = findViewById(R.id.radio_button_2A);
        myRadioButton2B = findViewById(R.id.radio_button_2B);
        myRadioButton2C = findViewById(R.id.radio_button_2C);

        myCheckbox3A = findViewById(R.id.checkbox3A);
        myCheckbox3B = findViewById(R.id.checkbox3B);
        myCheckbox3C = findViewById(R.id.checkbox3C);

        myEditText4a = findViewById(R.id.editText4a);

        mySpinner = findViewById(R.id.spinner5);

        myCheckbox6A = findViewById(R.id.checkbox6A);
        myCheckbox6B = findViewById(R.id.checkbox6B);
        myCheckbox6C = findViewById(R.id.checkbox6C);

        myTextViewQ1 = findViewById(R.id.questionNumber1);
        myTextViewQ2 = findViewById(R.id.questionNumber2);
        myTextViewQ3 = findViewById(R.id.questionNumber3);
        myTextViewQ4 = findViewById(R.id.questionNumber4);
        myTextViewQ5 = findViewById(R.id.questionNumber5);
        myTextViewQ6 = findViewById(R.id.questionNumber6);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.question5_values, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mySpinner.setAdapter(adapter);
    }




    /**
     * Calculates the total score for the Quiz
     */
    public void calculateScore(View view) {
        int score;
        String scoreText;

        if (!checkUserData())
                return;

        score = checkAnswer1();
        score += checkAnswer2();
        score += checkAnswer3();
        score += checkAnswer4();
        score += checkAnswer5();
        score += checkAnswer6();

        scoreText = String.valueOf(score);

        showToast(createFeedback(score));


    }

    /**
     * Calculates and returns the score for Question 1 (1 right, 0 wrong)
     * If the answer is wrong, the label of the answer in marked in red to let the user know the wrong answers.
     */
    private int checkAnswer1() {
        int score=0;

        if (myCheckbox1A.isChecked() && myCheckbox1C.isChecked() && !myCheckbox1B.isChecked())
            score = 1;

        switchColor (myTextViewQ1, score);

        return score;

    }

    /**
     * Calculates the score for Question 2
     */
    private int checkAnswer2() {
        int score=0;

        if (myRadioButton2B.isChecked())
            score = 1;

        switchColor (myTextViewQ2, score);

        return score;

    }

    /**
     * Calculates the score for Question 3
     */
    private int checkAnswer3() {
        int score=0;

        if (myCheckbox3A.isChecked() && myCheckbox3B.isChecked() && !myCheckbox3C.isChecked())
            score = 1;

        switchColor (myTextViewQ3, score);

        return score;

    }

    /**
     * Calculates the score for Question 4
     */
    private int checkAnswer4() {
        int score=0;
        String myAnswer = myEditText4a.getText().toString().trim();
        if (myAnswer.equals(EMERGENCY_PHONE_NUMBER))
            score = 1;

        switchColor (myTextViewQ4, score);

        return score;

    }


    /**
     * Calculates the score for Question 5
     */
    private int checkAnswer5() {
        int score = 0;
        int myAnswer =  mySpinner.getSelectedItemPosition();

        if (myAnswer == 1)
            score = 1;

        switchColor (myTextViewQ5, score);

        return score;

    }

    /**
     * Calculates the score for Question 6
     */
    private int checkAnswer6() {
        int score=0;

        if (myCheckbox6A.isChecked() && myCheckbox6B.isChecked() && myCheckbox6C.isChecked())
            score = 1;

        switchColor (myTextViewQ6, score);

        return score;

    }

    /**
     * Creates a feedback message
     * @param score The score calculated for the quiz
     */
    private String createFeedback(int score) {
        String feedback;

        if (score >= MIN_SCORE)
            feedback = "Complimenti " + myUsernameInput.getText().toString() + ". Hai risposto bene a tutte le domande.";
        else
            feedback = myUsernameInput.getText().toString() + ", hai risposto bene a " + score + " domande.";
            feedback = feedback + "Controlla le domande con i numeri segnati in rosso e ripeti il questionario.";

        return feedback;

    }

    /**
     * Checks if the user has inserted data for both username and Companyname
     */
    private Boolean checkUserData() {

        if (myUsernameInput.getText().toString().isEmpty() || myCompanynameInput.getText().toString().isEmpty()) {

            showToast(getString(R.string.user_company_name_missing));

            return false;
        }

        return true;

    }
    /**
     * Displays the given Message in a toast
     * @param toastMessage the message (string) to be displayed by the toast
     */
    public void showToast(String toastMessage) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, toastMessage, duration);
        toast.show();
    }

    /**
     * Changes the color of the label of the question according to the score given to the answer
     * If the answer is wrong, the label of the answer in marked in red to let the user know the wrong answers.
     * @param view it's the Textview to be modified
     * @param score the score given to the answer
     */
    private void switchColor(View view, int score) {

        if ( score == 1)  {
            view.setBackgroundResource(R.drawable.round_text_container);
        }
        else
            view.setBackgroundResource(R.drawable.round_text_container_red);
    }

    /** Called when the user taps the Back Arrow */
    public void openMainMenu(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
