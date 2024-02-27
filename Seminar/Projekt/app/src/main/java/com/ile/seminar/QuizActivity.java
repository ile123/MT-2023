package com.ile.seminar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.ile.seminar.adapters.PlayerAdapter;
import com.ile.seminar.models.BestPlayer;
import com.ile.seminar.models.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    List<Question> questionList = new ArrayList<>();

    TextView questionNumber, questionText;
    Button firstAnswer, secondAnswer, thirdAnswer, fourthAnswer;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collection = db.collection("questions");
    int currentScore = 0, questionIndex = 0, amountOfCorrectAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionNumber = findViewById(R.id.questionNumber);
        questionText = findViewById(R.id.questionText);
        firstAnswer = findViewById(R.id.firstAnswerButton);
        secondAnswer = findViewById(R.id.secondAnswerButton);
        thirdAnswer = findViewById(R.id.thirdAnswerButton);
        fourthAnswer = findViewById(R.id.fourthAnswerButton);

        collection.addSnapshotListener((value, error) -> collection
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        for(DocumentSnapshot documentSnapshot : task.getResult()) {
                            Question question = new Question(
                                    documentSnapshot.getString("question"),
                                    documentSnapshot.getString("firstAnswer"),
                                    documentSnapshot.getString("secondAnswer"),
                                    documentSnapshot.getString("thirdAnswer"),
                                    documentSnapshot.getString("fourthAnswer"),
                                    documentSnapshot.getString("correctAnswer"));
                            questionList.add(question);
                        }
                        Collections.shuffle(questionList);
                        setUpQuestion();
                    } else {
                        Toast.makeText(QuizActivity.this, "ERROR: FireStore related problem!", Toast.LENGTH_SHORT).show();
                    }
                }));

        firstAnswer.setOnClickListener(v -> {
            if(Objects.equals(questionList.get(questionIndex).getFirstAnswer(), questionList.get(questionIndex).getCorrectAnswer())) {
                currentScore += 100;
                amountOfCorrectAnswers += 1;
            }
            new Handler().postDelayed(() -> {
                isQuizDone(questionIndex, questionList.size(), currentScore, amountOfCorrectAnswers);
                questionIndex += 1;
                setUpQuestion();
            }, 500);
        });

        secondAnswer.setOnClickListener(v -> {
            if(Objects.equals(questionList.get(questionIndex).getSecondAnswer(), questionList.get(questionIndex).getCorrectAnswer())) {
                currentScore += 100;
                amountOfCorrectAnswers += 1;
            }
            new Handler().postDelayed(() -> {
                isQuizDone(questionIndex, questionList.size(), currentScore, amountOfCorrectAnswers);
                questionIndex += 1;
                setUpQuestion();
            }, 500);
        });

        thirdAnswer.setOnClickListener(v -> {
            if(Objects.equals(questionList.get(questionIndex).getThirdAnswer(), questionList.get(questionIndex).getCorrectAnswer())) {
                currentScore += 100;
                amountOfCorrectAnswers += 1;
            }
            new Handler().postDelayed(() -> {
                isQuizDone(questionIndex, questionList.size(), currentScore, amountOfCorrectAnswers);
                questionIndex += 1;
                setUpQuestion();
            }, 500);
        });

        fourthAnswer.setOnClickListener(v -> {
            if(Objects.equals(questionList.get(questionIndex).getFourthAnswer(), questionList.get(questionIndex).getCorrectAnswer())) {
                currentScore += 100;
                amountOfCorrectAnswers += 1;
            }
            new Handler().postDelayed(() -> {
                isQuizDone(questionIndex, questionList.size(), currentScore, amountOfCorrectAnswers);
                questionIndex += 1;
                setUpQuestion();
            }, 500);
        });
    }

    private void setUpQuestion() {
        questionNumber.setText((questionIndex + 1) + "/" + questionList.size());
        questionText.setText(questionList.get(questionIndex).getQuestion());
        firstAnswer.setText(questionList.get(questionIndex).getFirstAnswer());
        secondAnswer.setText(questionList.get(questionIndex).getSecondAnswer());
        thirdAnswer.setText(questionList.get(questionIndex).getThirdAnswer());
        fourthAnswer.setText(questionList.get(questionIndex).getFourthAnswer());
    }

    private void isQuizDone(int index, int questionListSize, int score, int amountOfCorrectAnswers) {
        if(index + 1 == questionListSize) {
            Intent intent = new Intent(this, QuizResultActivity.class);
            intent.putExtra("score", String.valueOf(score));
            intent.putExtra("sizeOfQuiz", String.valueOf(questionListSize));
            intent.putExtra("amountOfCorrectAnswers", String.valueOf(amountOfCorrectAnswers));
            startActivity(intent);
            finish();
        }
    }
}