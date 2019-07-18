package ir.norooch.nornote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateNote extends AppCompatActivity implements View.OnClickListener {
    private EditText title, content;
    private Button cancel, save;
    private String username;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        init();

        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    private void init() {
        title = findViewById(R.id.title_new_note);
        content = findViewById(R.id.content_new_note);
        cancel = findViewById(R.id.cancel_new_note);
        save = findViewById(R.id.save_new_note);
        handler = new Handler();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == save.getId()) {
            Note note = new Note(title.getText().toString().trim(), content.getText().toString().trim(), username);
            handler.addNote(this, note);
        } else if (id == cancel.getId()) {
            finish();
        }
    }
}
