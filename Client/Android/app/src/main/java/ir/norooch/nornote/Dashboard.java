package ir.norooch.nornote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    ListView listNotes;
    Handler handler;
    String username;
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        notes = handler.getNotes(Dashboard.this, username);
        MyAdapter adapter = new MyAdapter(Dashboard.this, notes);
        listNotes.setAdapter(adapter);
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = (Note) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(Dashboard.this, EditNote.class);
                intent.putExtra("id", note.getId());
                intent.putExtra("title", note.getTitle());
                intent.putExtra("content", note.getContent());
                intent.putExtra("username", note.getUser());
                startActivity(intent);
            }
        });
    }

    private void init() {
        listNotes = findViewById(R.id.list_notes);
        handler = new Handler();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        notes = handler.getNotes(this, username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Sync").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                listNotes.setAdapter(new MyAdapter(Dashboard.this, notes));
                return false;
            }
        }).setShowAsAction(1);
        menu.add("New").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(Dashboard.this, CreateNote.class);
                intent.putExtra("username", username);
                startActivity(intent);
                return false;
            }
        }).setShowAsAction(1);

        menu.add("Logout").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                finish();
                return false;
            }
        }).setShowAsAction(1);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        notes = handler.getNotes(this, username);
    }

}
