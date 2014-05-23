package com.knoda.exercise.Networking;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

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

  public void getPredictions(final ProgressDialog progressDialog) {
    progressDialog.setMessage("Loading Predictions...");
    progressDialog.show();
    JsonObjectRequest json = new JsonObjectRequest(APIurl, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject jsonObject) {
        progressDialog.hide();
        System.out.println(jsonObject);
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError volleyError) {
        progressDialog.hide();
      }
    });
    //5 Second timeout
    json.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    queue.add(json);

  }


}
