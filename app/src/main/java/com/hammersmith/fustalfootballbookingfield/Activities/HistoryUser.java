package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.login.widget.ProfilePictureView;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.AdapterHistory;
import com.hammersmith.fustalfootballbookingfield.adapter.AdapterLeague;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.History;
import com.hammersmith.fustalfootballbookingfield.model.League;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thuon on 2/26/2016.
 */
public class HistoryUser extends AppCompatActivity implements AdapterHistory.ClickListener {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    AdapterHistory mAdapter;
    List<History> histories = new ArrayList<>();
    History history;
    ImageView proGoogle;
    ProfilePictureView profilePictureView;
    User user;
    Context context;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_user);

        user = PrefUtils.getCurrentUser(HistoryUser.this);
        proGoogle = (ImageView) findViewById(R.id.proGoogle);
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePic);
        mRecyclerView = (RecyclerView) findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new AdapterHistory(this, histories);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mAdapter.setClickListener(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        if (user.getImageProfile() != null) {
            profilePictureView.setVisibility(View.GONE);
            Picasso.with(context).load(user.getImageProfile()).into(proGoogle);
            if (proGoogle.getDrawable() == null) {
                profilePictureView.setVisibility(View.GONE);
                proGoogle.setImageResource(R.drawable.default_male_avatar);
            }
        } else {
            proGoogle.setVisibility(View.GONE);
            profilePictureView.setProfileId(user.getFacebookID());
        }

        if (histories.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest leagueReq = new JsonArrayRequest(Constant.URL_HISTORY + user.getFacebookID(), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        int n = i + 1;
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            history = new History();
                            history.setNo(("" + n));
                            history.setUsername(obj.getString("username"));
                            history.setLocation(obj.getString("location"));
                            history.setType(obj.getString("type"));
                            history.setField(obj.getString("field"));
                            history.setDate(obj.getString("date"));
                            history.setTime(obj.getString("time"));
                            histories.add(history);
                            hidePDialog();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getApplicationContext(), volleyError + "", Toast.LENGTH_SHORT).show();
                    hidePDialog();
                }
            });
            AppController.getInstance().addToRequestQueue(leagueReq);

        }
    }

    @Override
    public void itemClicked(View view, int position) {

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


