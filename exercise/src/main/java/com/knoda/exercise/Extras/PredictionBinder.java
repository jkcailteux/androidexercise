package com.knoda.exercise.Extras;

import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.knoda.exercise.R;

import java.util.HashMap;

/**
 * Created by Jeff on 5/23/2014.
 */
public class PredictionBinder implements SimpleAdapter.ViewBinder {
  @Override
  public boolean setViewValue(View view, Object data, String textRepresentation) {
    HashMap<String,String> map = (HashMap)data;
    if(view.getId()==R.id.user)
      ((TextView) view.findViewById(R.id.user)).setText(map.get("username"));

    //((TextView) view.findViewById(R.id.body)).setText(map.get("body"));
    return true;
  }
}
