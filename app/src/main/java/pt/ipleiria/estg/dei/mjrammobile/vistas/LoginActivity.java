package pt.ipleiria.estg.dei.mjrammobile.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipleiria.estg.dei.mjrammobile.MainActivity;
import pt.ipleiria.estg.dei.mjrammobile.R;
import pt.ipleiria.estg.dei.mjrammobile.listeners.LoginListener;
import pt.ipleiria.estg.dei.mjrammobile.modelo.Singleton;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    private EditText etEmail, etPassword;
    private String username;
    private final int MIN_PASS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Singleton.getInstance(getApplicationContext()).setLoginListener(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        SharedPreferences sharedPreferences =getSharedPreferences(MainActivity.SHARED_USER, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(MainActivity.USERNAME, null);
        if (username!=null){
            etEmail.setText(username);
        }

    }

    public void onClickLogin(View view) {

        String username = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        /*if(!isEmailValid(username))
        {
            etEmail.setText(R.string.txt_Email_invalido);
            return;
        }*/
        /*if (!isPasswordValid(password))
        {
            etPassword.setError(getString(R.string.txt_pass_invalida));
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("EMAIL", email);
        startActivity(intent);
        finish();*/
        Singleton.getInstance(getApplicationContext()).loginAPI(username,password,getApplicationContext());


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

    @Override
    public void onValidateLogin(String token, String username, Context context) {
        if (token!=null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.USERNAME, username);
            intent.putExtra(MainActivity.TOKEN, token);
            startActivity(intent);
            finish();
        }else
        {
            Toast.makeText(getApplicationContext(),"Erro login",Toast.LENGTH_LONG).show();
        }
    }
}