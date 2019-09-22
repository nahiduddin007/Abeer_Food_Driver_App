package com.nahiduddin.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nahiduddin.myapplication.utils.utils;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private Button signInButton;
    private TextView forgotPassTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initialization();
    }

    private void initialization() {
        emailET = findViewById(R.id.signInEmailET);
        passwordET = findViewById(R.id.signInPasswordET);
        signInButton = findViewById(R.id.signInSignInButton);
        forgotPassTV = findViewById(R.id.signInForgotPasswordTV);

        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginIntoAccouont();
            }
        });
    }

    private void loginIntoAccouont() {
        ProgressDialog dialog = new ProgressDialog(getApplicationContext());
        dialog.setTitle("Logging..");
        dialog.setMessage("Please wait a while...");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                utils.SINGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", emailET.getText().toString());
                params.put("password", passwordET.getText().toString());
                return super.getParams();
            }
        };
    }

    private void checkInputs(){

        if (!TextUtils.isEmpty(emailET.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(emailET.getText().toString()).matches()){
            if (!TextUtils.isEmpty(passwordET.getText().toString()) && passwordET.getText().toString().length()>6){
                signInButton.setEnabled(true);
            } else {
                signInButton.setEnabled(false);
                passwordET.setError("Enter password more than 6 letters");
            }
        } else {
            signInButton.setEnabled(false);
            emailET.setError("Enter a valid email");
        }
    }
}
