package com.knoda.exercise.Networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.knoda.exercise.Extras.PredictionBinder;
import com.knoda.exercise.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jeff on 5/23/2014.
 */
public class APIController {

  static final String authToken = "Q4tgFunM22ubWqP4p4zz";
  static final String APIurl = "http://api.knoda.com/api/predictions.json?recent=true&auth_token=" + authToken;
  private RequestQueue queue;

  public APIController(Context c) {
    queue = Volley.newRequestQueue(c);
  }

  public void getPredictions(final ListView listView) {
    final ProgressDialog progressDialog = new ProgressDialog(listView.getContext());
    progressDialog.setMessage("Loading Predictions...");
    progressDialog.show();

    JsonObjectRequest json = new JsonObjectRequest(APIurl, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject jsonObject) {
        progressDialog.hide();
        ArrayList<HashMap<String, String>> predictionsList = new ArrayList<HashMap<String, String>>();
        SimpleAdapter adapter = new SimpleAdapter(listView.getContext(), predictionsList, R.layout.list_item_prediction,
            new String[]{"user_avatar", "username", "verified_account", "body", "expires_at", "created_at", "agreed_count", "disagreed_count",
                "comment_count"},
            new int[]{R.id.userpic, R.id.user, R.id.userverified, R.id.body, R.id.closes_textview, R.id.made_textview, R.id.agrees_textview,
                R.id.agrees_textview,
                R.id.comments_number}
        );
        adapter.setViewBinder(new PredictionBinder());
        listView.setAdapter(adapter);

        try {
          JSONArray predictions = jsonObject.getJSONArray("predictions");
          for (int i = 0; i != predictions.length(); i++) {
            JSONObject j = predictions.getJSONObject(i);
            HashMap<String, String> item = new HashMap<String, String>();
            for (int x = 0; x != j.names().length(); x++) {
              String name = j.names().getString(x);
              item.put(name, j.getString(name));
            }
            predictionsList.add(item);
          }
        } catch (Exception e) {
          Log.e("APIController", e.getMessage());
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError volleyError) {
        progressDialog.hide();
        Toast.makeText(listView.getContext(),"Could not get predictions.",Toast.LENGTH_SHORT).show();
      }
    });
    //10 Second timeout
    json.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    queue.add(json);

  }


}
