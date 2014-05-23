package com.knoda.exercise.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.knoda.exercise.Networking.APIController;
import com.knoda.exercise.R;


public class MainActivity extends Activity {

  APIController apiController;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction()
          .add(R.id.container, new PlaceholderFragment())
          .commit();
    }
    apiController= new APIController(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {
    ListView listView;
    MainActivity mainActivity;
    public PlaceholderFragment() {
    }
    private void loadPage(){
      mainActivity.apiController.getPredictions(listView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
      super.onActivityCreated(savedInstanceState);
      mainActivity=(MainActivity)getActivity();
      listView = (ListView) mainActivity.findViewById(R.id.mainListview);
      loadPage();
    }
  }
}
