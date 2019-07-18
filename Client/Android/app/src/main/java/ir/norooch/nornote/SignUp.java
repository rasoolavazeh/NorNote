package ir.norooch.nornote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText username, password, confirmPass;
    Button cancel, signUpBtn;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

        cancel.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
    }

    private void init() {
        username = findViewById(R.id.sign_up_username);
        password = findViewById(R.id.sign_up_password);
        confirmPass = findViewById(R.id.sign_up_confirm_password);
        cancel = findViewById(R.id.sign_up_cancel);
        signUpBtn = findViewById(R.id.sign_up_btn);
        handler = new Handler();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == signUpBtn.getId()) {
            handler.signUp(this, username, password, confirmPass);
        } else if (id == cancel.getId()) {
            finish();
        }
    }
}
