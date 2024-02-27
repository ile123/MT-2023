package com.ile.vjezba4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button submit;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        submit = findViewById(R.id.registerSubmit);
        auth = FirebaseAuth.getInstance();

        submit.setOnClickListener(v -> {
            if(!isValidEmail(email.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "ERROR: Invalid email!", Toast.LENGTH_SHORT).show();
            }
            else if(password.length() < 6) {
                Toast.makeText(RegisterActivity.this, "ERROR: Password to short!", Toast.LENGTH_SHORT).show();
            } else {
                createAccount(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void createAccount(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> finish());
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}