package com.ile.seminar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password, repeatPassword;
    Button submit;
    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        repeatPassword = findViewById(R.id.registerRepeatPassword);
        submit = findViewById(R.id.registerSubmit);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidEmail(email.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "ERROR: Invalid email!", Toast.LENGTH_SHORT).show();
                }
                else if(password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "ERROR: Password to short!", Toast.LENGTH_SHORT).show();
                } else if(!password.getText().toString().equals(repeatPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "ERROR: Password and repeat password do not match!", Toast.LENGTH_SHORT).show();
                } else {
                    createAccount(email.getText().toString(), name.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private void createAccount(String email, String name, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        FirebaseUser newUser = auth.getCurrentUser();
                        if(newUser != null) {
                            Map<String, Object> user = new HashMap<>();
                            user.put("email", email);
                            user.put("name", name);
                            user.put("totalScore", 0);
                            db.collection("users").document(newUser.getUid()).set(user)
                                    .addOnSuccessListener(unused -> {
                                        auth.signOut();
                                        finish();
                                    })
                                    .addOnFailureListener(e -> Log.e("FireStore: ", "Error adding document", e));
                        }
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(RegisterActivity.this, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}