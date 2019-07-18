package ir.norooch.nornote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<Note> notes;
    private Context context;

    public MyAdapter(Context context, ArrayList notes) {
        this.context = context;
        this.notes = notes;

    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        }

        Note note = (Note) getItem(i);
        TextView title = view.findViewById(R.id.title_item);
        TextView content = view.findViewById(R.id.content_item);
        title.setText(note.getTitle());
        content.setText(note.getContent());

        return view;
    }
}
