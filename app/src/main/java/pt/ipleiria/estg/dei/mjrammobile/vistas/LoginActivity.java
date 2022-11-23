package pt.ipleiria.estg.dei.mjrammobile.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import pt.ipleiria.estg.dei.mjrammobile.MainActivity;
import pt.ipleiria.estg.dei.mjrammobile.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private final int MIN_PASS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void onClickLogin(View view) {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(!isEmailValid(email))
        {
            etEmail.setText(R.string.txt_Email_invalido);
            return;
        }
        if (!isPasswordValid(password))
        {
            etPassword.setError(getString(R.string.txt_pass_invalida));
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("EMAIL", email);
        startActivity(intent);
        finish();
    }

    private boolean isEmailValid(String email)
    {
        if(email == null)
            return false;
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String pass)
    {
        if(pass==null)
            return false;
        return pass.length()>=MIN_PASS;
    }

}