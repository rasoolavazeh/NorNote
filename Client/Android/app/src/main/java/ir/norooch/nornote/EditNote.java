package ir.norooch.nornote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNote extends AppCompatActivity implements View.OnClickListener {
    private EditText title, content;
    private Button cancel, save;
    Note note;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        init();
        setValues();

        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    private void init() {
        title = findViewById(R.id.title_edit_note);
        content = findViewById(R.id.content_edit_note);
        cancel = findViewById(R.id.cancel_edit_note);
        save = findViewById(R.id.save_edit_note);
        handler = new Handler();

        Intent intent = getIntent();
        note = new Note();
        note.setId(intent.getIntExtra("id", 0));
        note.setTitle(intent.getStringExtra("title"));
        note.setContent(intent.getStringExtra("content"));
        note.setUser(intent.getStringExtra("username"));
    }

    public void setValues() {
        title.setText(note.getTitle());
        content.setText(note.getContent());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == save.getId()) {
            note.setTitle(title.getText().toString().trim());
            note.setContent(content.getText().toString().trim());
            handler.editNote(this, note);
        } else if (id == cancel.getId()) {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                handler.deleteNote(EditNote.this, note);
                return false;
            }
        }).setShowAsAction(1);
        return super.onCreateOptionsMenu(menu);
    }
}
