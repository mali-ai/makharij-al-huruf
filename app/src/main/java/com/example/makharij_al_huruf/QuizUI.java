package com.example.makharij_al_huruf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class QuizUI extends AppCompatActivity implements View.OnClickListener{
    Map<String, String> table = new HashMap<String, String>(){{ put("أ", "Sound produced from the End of Throat");
        put("ب", "Sound produced from Inner part of the both lips touch each other");
        put("ت", "Sound produced from Tip of the tongue touching the base of the front 2 teeth");
        put("ث", "Sound produced from Tip of the tongue touching the tip of the frontal 2 teeth");
        put("ج", "Sound produced from Tongue touching the center of the mouth roof");
        put("ح", "Sound produced from the Middle of Throat");
        put("خ", "Sound produced from the Start of Throat");
        put("د", "Sound produced from Tip of the tongue touching the base of the front 2 teeth");
        put("ذ", "Sound produced from Tip of the tongue touching the tip of the frontal 2 teeth");
        put("ر", "Sound produced from Rounded tip of the tongue and some portion near it touching the base of the frontal 4 teeth");
        put("ز", "Sound produced from Tip of the tongue comes between the front top and bottom teeth");
        put("س", "Sound produced from Tip of the tongue comes between the front top and bottom teeth");
        put("ش", "Sound produced from Tongue touching the center of the mouth roof");
        put("ص", "Sound produced from Tip of the tongue comes between the front top and bottom teeth");
        put("ض", "Sound produced from One side of the tongue touching the molar teeth");
        put("ط", "Sound produced from Tip of the tongue touching the base of the front 2 teeth");
        put("ظ", "Sound produced from Tip of the tongue touching the tip of the frontal 2 teeth");
        put("ع", "Sound produced from the Middle of Throat");
        put("غ", "Sound produced from the Start of Throat");
        put("ف", "Sound produced from Tip of the two upper jaw teeth touches the inner part of the lower lip");
        put("ق", "Sound produced from Base of Tongue which is near Uvula touching the mouth roof");
        put("ك", "Sound produced from Portion of Tongue near its base touching the roof of mouth");
        put("ل", "Sound produced from Rounded tip of the tongue touching the base of the frontal 8 teeth");
        put("م", "Sound produced from Outer part of both lips touch each other");
        put("ن", "Sound produced from Rounded tip of the tongue touching the base of the frontal 6 teeth");
        put("و", "Sound produced from Rounding both lips and not closing the mouth");
        put("ه", "Sound produced from the End of Throat");
        put("ي", "Sound produced from Tongue touching the center of the mouth roof");}};
    List<String> questions = new ArrayList<String>();
    List<String> correctAnswers = new ArrayList<String>();
    List<String> userAnswers = new ArrayList<String>();
    TextView textView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ImageView tick;
    ImageView cross;
    TextView correct;
    TextView wrong;
    int correctCounter = 0;
    int wrongCounter = 0;
    int counter = 1;
    List<String> valueList = new ArrayList<String>(table.values());
    List<String> keyList = new ArrayList<String>(table.keySet());
    List<String> optionList = new ArrayList<String>();
    Random generator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_ui);
        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.optionA);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.optionB);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.optionC);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.optionD);
        button4.setOnClickListener(this);
        tick = findViewById(R.id.correctCounter);
        cross = findViewById(R.id.wrongCounter);
        correct = findViewById(R.id.tickCounter);
        wrong = findViewById(R.id.crossCounter);
        generateQuestion();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.optionA:
            case R.id.optionB:
            case R.id.optionC:
            case R.id.optionD:
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
                button4.setClickable(false);
                verifyAnswer(v);
                if(counter < 10) {
                    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                    service.schedule(this::generateQuestion, 2, TimeUnit.SECONDS);
                    counter++;
                }else{
                    Log.i("Enter", "New Intent");
                    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                    service.schedule(this::moveToResult, 2, TimeUnit.SECONDS);
                    Intent intent = new Intent(QuizUI.this, Result.class);
                    Bundle args = new Bundle();
                    args.putSerializable("questions",(Serializable)questions);
                    args.putSerializable("correctAnswers",(Serializable)correctAnswers);
                    args.putSerializable("userAnswers",(Serializable)userAnswers);
                    args.putSerializable("correct", (Serializable)correctCounter);
                    intent.putExtra("BUNDLE",args);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    public void moveToResult(){
        Intent intent = new Intent(QuizUI.this, Result.class);
        intent.putExtra("questions", (Parcelable) questions);
        intent.putExtra("correctAnswers", (Parcelable) correctAnswers);
        intent.putExtra("userAnswers", (Parcelable) userAnswers);
        startActivity(intent);
    }


    public void verifyAnswer(View v){
        if(table.get(textView.getText().toString()) == ((Button)v).getText()){
            //v.setBackgroundColor(getResources().getColor(R.color.correct));
            v.setBackgroundTintList(this.getResources().getColorStateList(R.color.green));
            correctCounter++;
            correct.setText(String.valueOf(correctCounter));
        }
        else{
            wrongCounter = wrongCounter + 1;
            wrong.setText(String.valueOf(wrongCounter));
            //v.setBackgroundColor(getResources().getColor(R.color.wrong));
            v.setBackgroundTintList(this.getResources().getColorStateList(R.color.red));
            if(table.get(textView.getText().toString()) == (button1.getText())){
                //button1.setBackgroundColor(getResources().getColor(R.color.correct));
                button1.setBackgroundTintList(this.getResources().getColorStateList(R.color.green));
            }else if(table.get(textView.getText().toString()) == (button2.getText())){
                //button2.setBackgroundColor(getResources().getColor(R.color.correct));
                button2.setBackgroundTintList(this.getResources().getColorStateList(R.color.green));
            }else if(table.get(textView.getText().toString()) == (button3.getText())){
                //button3.setBackgroundColor(getResources().getColor(R.color.correct));
                button3.setBackgroundTintList(this.getResources().getColorStateList(R.color.green));
            }else{
                //button4.setBackgroundColor(getResources().getColor(R.color.correct));
                button4.setBackgroundTintList(this.getResources().getColorStateList(R.color.green));
            }
        }
        userAnswers.add(((Button)v).getText().toString());
    }


    public void generateQuestion(){
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        button4.setClickable(true);
        button1.setBackgroundTintList(this.getResources().getColorStateList(R.color.defaulf_color));
        button2.setBackgroundTintList(this.getResources().getColorStateList(R.color.defaulf_color));
        button3.setBackgroundTintList(this.getResources().getColorStateList(R.color.defaulf_color));
        button4.setBackgroundTintList(this.getResources().getColorStateList(R.color.defaulf_color));
        int randomIndex = generator.nextInt(keyList.size());
        String randomQuestion = keyList.get(randomIndex);
        textView.setText(randomQuestion);
        questions.add(randomQuestion);
        correctAnswers.add(valueList.get(randomIndex));
        List<String> optionList = new ArrayList<String>();
        optionList.add(valueList.get(randomIndex));
        optionList.add(valueList.get(generator.nextInt(valueList.size())));
        optionList.add(valueList.get(generator.nextInt(valueList.size())));
        optionList.add(valueList.get(generator.nextInt(valueList.size())));
        Collections.shuffle(optionList);
        button1.setText(optionList.get(0));
        button2.setText(optionList.get(1));
        button3.setText(optionList.get(2));
        button4.setText(optionList.get(3));

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("counter", (Serializable)counter);
        savedInstanceState.putSerializable("optionA", (Serializable)button1.getText());
        savedInstanceState.putSerializable("optionB", (Serializable)button2.getText());
        savedInstanceState.putSerializable("optionC", (Serializable)button3.getText());
        savedInstanceState.putSerializable("optionD", (Serializable)button4.getText());
        savedInstanceState.putSerializable("letter", (Serializable)textView.getText());
        savedInstanceState.putSerializable("correct", (Serializable)correctCounter);
        savedInstanceState.putSerializable("wrong", (Serializable)wrongCounter);
        savedInstanceState.putSerializable("questions",(Serializable)questions);
        savedInstanceState.putSerializable("correctAnswers",(Serializable)correctAnswers);
        savedInstanceState.putSerializable("userAnswers",(Serializable)userAnswers);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = (Integer)savedInstanceState.getSerializable("counter");
        button1.setText((String)savedInstanceState.getSerializable("optionA"));
        button2.setText((String)savedInstanceState.getSerializable("optionB"));
        button3.setText((String)savedInstanceState.getSerializable("optionC"));
        button4.setText((String)savedInstanceState.getSerializable("optionD"));
        textView.setText((String)savedInstanceState.getSerializable("letter"));
        correctCounter = (Integer)savedInstanceState.getSerializable("correct");
        correct.setText(String.valueOf(correctCounter));
        wrongCounter = (Integer)savedInstanceState.getSerializable("wrong");
        wrong.setText(String.valueOf(wrongCounter));
        questions = (List<String>)savedInstanceState.getSerializable("questions");
        correctAnswers = (List<String>)savedInstanceState.getSerializable("correctAnswers");
        userAnswers = (List<String>)savedInstanceState.getSerializable("userAnswers");
    }
}