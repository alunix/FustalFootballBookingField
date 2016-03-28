package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerHomeAdapter;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Field;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements RecyclerHomeAdapter.ClickListener {
    Toolbar toolbar;
    EditText editText;
    String str_search;
    ListView listView;
    RecyclerView recyclerView;
    public static RecyclerHomeAdapter adapter;
    private ProgressDialog pDialog;
    int[] id;
    String[] title;
    List<Field> fields = new ArrayList<>();
    Field field;
    String image = "";
    ImageView imgClearText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_search);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.list_item);
        imgClearText = (ImageView) findViewById(R.id.search_clear);
        Bundle bundle = getIntent().getExtras();
        str_search = bundle.getString("key");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        editText.setText(str_search);
        recyclerView = (RecyclerView) findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerHomeAdapter(this, fields);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imgClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Intent intent = new Intent(getIntent());
                    Bundle bundle = new Bundle();
                    bundle.putString("key", editText.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        str_search = str_search.replaceAll(" ","_").toLowerCase();
        if (fields.size() <= 0) {
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_SEARCH + str_search , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    hidePDialog();
                    id = new int[jsonArray.length()];
                    title = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            field = new Field();
                            field.setImage(Constant.URL_HOME + obj.getString("image_path"));
                            field.setName(obj.getString("name"));
                            field.setLocation(obj.getString("address"));
                            id[i] = obj.getInt("id");
                            title[i] = obj.getString("name");
                            fields.add(field);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getApplicationContext(), "Date not found!", Toast.LENGTH_SHORT).show();
                    hidePDialog();
                }
            });
            int socketTimeout = 60000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            fieldReq.setRetryPolicy(policy);
            AppController.getInstance().addToRequestQueue(fieldReq);
        }
    }

    @Override
    public void itemClicked(View view, int position) {
        image = ((Field)fields.get(position)).getImage();
        Intent intent = new Intent(SearchActivity.this, ActivityBooking.class);
        intent.putExtra("location", title[position]);
        intent.putExtra("field", image);
        intent.putExtra("ID", id[position]);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
