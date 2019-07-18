package ir.norooch.nornote;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Handler {

    public void login(final Activity context, EditText username, EditText password) {
        final String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (user.equals("")) {
            username.requestFocus();
        } else if (pass.equals("")) {
            password.requestFocus();
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = "http://1.1.1.1/NorNote/login.php";
            JSONObject loginObject = new JSONObject();
            try {
                loginObject.put("username", user);
                loginObject.put("password", pass);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, loginObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("isOk")) {
                            Intent intent = new Intent(context, Dashboard.class);
                            intent.putExtra("username", user);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, "Check username and password", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQueue.add(request);
        }
    }


    public void signUp(final Activity context, EditText username, EditText password, EditText confirmPass) {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String confirm = confirmPass.getText().toString().trim();

        if (user.equals("")) {
            username.requestFocus();
        } else if (pass.equals("")) {
            password.requestFocus();
        } else if (!pass.equals(confirm)) {
            confirmPass.requestFocus();
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = "http://1.1.1.1/NorNote/signUp.php";
            JSONObject signUpObject = new JSONObject();
            try {
                signUpObject.put("username", user);
                signUpObject.put("password", pass);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, signUpObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("isExist")) {
                            Toast.makeText(context, "Username already exist", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "You were added", Toast.LENGTH_SHORT).show();
                            context.finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQueue.add(request);
        }
    }

    public ArrayList<Note> getNotes(final Activity context, String username) {
        final ArrayList<Note> notes = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://1.1.1.1/NorNote/getNotes.php";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() != 0) {
                    JSONObject object;
                    Note note;
                    for (int i = 0;i < response.length();i++) {
                        try {
                            object = response.getJSONObject(i);
                            note = new Note();
                            note.setId(object.getInt("id"));
                            note.setTitle(object.getString("title"));
                            note.setContent(object.getString("content"));
                            note.setUser(object.getString("username"));
                            notes.add(note);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

        return notes;
    }

    public void addNote(final Activity context, final Note note) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://1.1.1.1/NorNote/addNote.php";
        JSONObject noteObject = new JSONObject();
        try {
            noteObject.put("title", note.getTitle());
            noteObject.put("content", note.getContent());
            noteObject.put("username", note.getUser());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, noteObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("added")) {
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show();
                        context.finish();
                    } else {
                        Toast.makeText(context, "Wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    public void editNote(final Activity context, final Note note) {
        Toast.makeText(context, "Note Edited", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://1.1.1.1/NorNote/editNote.php";
        JSONObject editObject = new JSONObject();
        try {
            editObject.put("id", note.getId());
            editObject.put("title", note.getTitle());
            editObject.put("content", note.getContent());
            editObject.put("username", note.getUser());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, editObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("edited")) {
                        Toast.makeText(context, "Note Edited", Toast.LENGTH_SHORT).show();
                        context.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    public void deleteNote(final Activity context, final Note note) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://1.1.1.1/NorNote/deleteNote.php";
        JSONObject deleteObject = new JSONObject();
        try {
            deleteObject.put("id", note.getId());
            deleteObject.put("title", note.getTitle());
            deleteObject.put("content", note.getContent());
            deleteObject.put("username", note.getUser());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, deleteObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("deleted")) {
                        Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();
                        context.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
