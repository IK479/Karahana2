package com.example.karahana.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karahana.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private TextView textView;
    private FirebaseAuth mAuth;
    private Button buttonSignup;
    private EditText editTextName;
    private EditText editTextEmail;
    private ProgressBar progressBar;
    private EditText editTextPassword;

    private final String EMAIL_MESSAGE = "Email is missing!";
    private final String PASSWORD_MESSAGE = "Password is missing!";
    private final String PHONE_MESSAGE = "Phone number is missing!";
    private final String ACCOUNT_CREATED_MASSAGE = "Account created!";
    private final String CREATED_FAILED_MESSAGE = "Failed to create account!";


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        findViews();
        click();
    }

    private void findViews(){
        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.signup_BTN_forLogin);
        editTextName = findViewById(R.id.signup_LBL_name);
        progressBar = findViewById(R.id.signup_progressbar);
        editTextEmail = findViewById(R.id.signup_LBL_email);
        buttonSignup = findViewById(R.id.signup_BTN_signup);
        editTextPassword = findViewById(R.id.signup_LBL_password);
    }

    private void click() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password, phoneNumber;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                phoneNumber = String.valueOf(editTextName.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUp.this, EMAIL_MESSAGE, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUp.this, PASSWORD_MESSAGE, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(SignUp.this, PHONE_MESSAGE, Toast.LENGTH_SHORT).show();
                    return;
                }
                signUp(email, password);

            }
        });

    }


        public void signUp(String email, String password){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);

                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp.this, ACCOUNT_CREATED_MASSAGE, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(SignUp.this, CREATED_FAILED_MESSAGE, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
}