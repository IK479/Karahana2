package com.example.karahana.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.karahana.BuildConfig;
import com.example.karahana.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextView title;
    private TextView textView;
    private FirebaseAuth mAuth;
    private AppCompatButton buttonLogin;
    private ProgressBar progressBar;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private LinearLayout backgroundImg;

    private final String EMAIL_MESSAGE = "Email is missing!";
    private final String PASSWORD_MESSAGE = "Password is missing!";
    private final String LOGIN_FAILED_MESSAGE = "failed to login";
    private final String LOGIN_SUCCESSFUL_MESSAGE = "Login Successful";



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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        findViews();
        click();
    }


    private void findViews() {
        editTextEmail = findViewById(R.id.login_LBL_email);
        editTextPassword = findViewById(R.id.login_LBL_password);
        buttonLogin = findViewById(R.id.login_BTN_login);
        progressBar = findViewById(R.id.signup_progressbar);
        textView = findViewById(R.id.login_BTN_forSignup);
    }


    private void click() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, EMAIL_MESSAGE, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, PASSWORD_MESSAGE, Toast.LENGTH_SHORT).show();
                    return;
                }
                login(email, password);
            }
        });
    }

        public void login(String email, String password){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful() || BuildConfig.DEBUG) {
                        Toast.makeText(Login.this, LOGIN_SUCCESSFUL_MESSAGE, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(Login.this, LOGIN_FAILED_MESSAGE, Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

}