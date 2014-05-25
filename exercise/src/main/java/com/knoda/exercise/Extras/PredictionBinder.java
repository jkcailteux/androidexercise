package com.knoda.exercise.Extras;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.knoda.exercise.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Jeff on 5/23/2014.
 */
public class PredictionBinder implements SimpleAdapter.ViewBinder {
  @Override
  public boolean setViewValue(View view, Object data, String textRepresentation) {
    switch (view.getId()) {
      case R.id.userpic:
        try {
          JSONObject json = new JSONObject(textRepresentation);
          UrlImageViewHelper.setUrlDrawable(((ImageView) view.findViewById(R.id.userpic)), json.getString("small"));
        } catch (Exception e) {
          Log.e("PredictionBinder", e.getMessage());
        }
        return true;
      case R.id.userverified:
        if (textRepresentation.equals("true"))
          ((ImageView) view).setImageResource(R.drawable.verified_badge);
        return true;
      case R.id.closes_textview:
        String closes = "";
        Date d = formatDate(textRepresentation);
        Long time = (d.getTime() - new Date().getTime()) / 1000;
        if ((time / (30 * 24 * 60 * 60)) >= 1) {
          int x = (int) (time / (30 * 24 * 60 * 60));
          closes = "closes " + x + "mo | ";
        } else if ((time / (24 * 60 * 60)) >= 1) {
          int x = (int) (time / (24 * 60 * 60));
          closes = "closes " + x + "d | ";
        } else if ((time / (60 * 60)) >= 1) {
          int x = (int) (time / (60 * 60));
          closes = "closes " + x + "h | ";
        } else if ((time / (60)) >= 1) {
          int x = (int) (time / 60);
          closes = "closes " + x + "m | ";
        }
        ((TextView) view).setText(closes);
        return true;
      case R.id.made_textview:
        String made = "";
        Date d1 = formatDate(textRepresentation);

        //No idea of timezone so assume Central
        Long time1 = ((new Date().getTime() - d1.getTime()) / 1000);
        if ((time1 / (30 * 24 * 60 * 60)) >= 1) {
          int x = (int) (time1 / (30 * 24 * 60 * 60));
          made = "made " + x + "mo ago | ";
        } else if ((time1 / (24 * 60 * 60)) >= 1) {
          int x = (int) (time1 / (24 * 60 * 60));
          made = "made " + x + "d ago | ";
        } else if ((time1 / (60 * 60)) >= 1) {
          int x = (int) (time1 / (60 * 60));
          made = "made " + x + "h ago | ";
        } else if ((time1 / (60)) >= 1) {
          int x = (int) (time1 / 60);
          made = "made " + x + "m ago | ";
        } else if ((time1) >= 1) {
          long x = (time1);
          made = "made " + x + "s ago | ";
        }
        ((TextView) view).setText(made);
        return true;
      case R.id.agrees_textview:

        String s = ((TextView) view).getText().toString();
        ((TextView) view).setText(textRepresentation);
        if (s.length() == 0) {
          ((TextView) view).setText(textRepresentation);
        } else {
          try {
            int agreed = Integer.parseInt(s);
            int disagreed = Integer.parseInt(textRepresentation);
            int percent = ((100 * agreed / (agreed + disagreed)));
            ((TextView) view).setText(percent + "% agree");
          } catch (NumberFormatException e) {
            Log.e("PredictionBinder", e.getMessage());
          }
        }
        return true;
    }
    return false;
  }

  private Date formatDate(String text) {
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
      return formatter.parse(text);
    } catch (Exception e) {
      Log.e("PredictionBinder", e.getMessage());
    }
    return new Date();
  }
}
