package com.example.ekatechhp.pkmapplication;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextConfirmPassword;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputLayout textInputLayoutConfirmPassword;
    Button buttonReset;
    SqliteHelper sqliteHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();
                    sqliteHelper.updatePassword(Email, Password);
                    Snackbar.make(buttonReset, "Password updated successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        buttonReset = (Button) findViewById(R.id.buttonReset);
    }
    private boolean validate() {
        boolean valid = false;
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();
        String ConfirmPassword = editTextConfirmPassword.getText().toString();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter password!");
        }
        else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is too short!");
            }
        }
        if (!Password.contentEquals(ConfirmPassword)) {
            valid = false;
            textInputLayoutConfirmPassword.setError("Please enter correct password!");
        }
        else {
            valid = true;
            textInputLayoutConfirmPassword.setError(null);
        }
        return valid;
    }
}
