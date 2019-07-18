package ir.norooch.nornote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username, password;
    Button loginBtn, signUpBtn;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        loginBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
    }

    private void init() {
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        signUpBtn = findViewById(R.id.sign_up);
        handler = new Handler();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == loginBtn.getId()) {
            handler.login(this, username, password);
        } else if (id == signUpBtn.getId()) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        }
    }
}
