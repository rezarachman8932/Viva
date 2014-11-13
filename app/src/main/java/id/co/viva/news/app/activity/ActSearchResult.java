package id.co.viva.news.app.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import id.co.viva.news.app.services.Analytics;
import id.co.viva.news.app.Constant;
import id.co.viva.news.app.R;
import id.co.viva.news.app.VivaApp;
import id.co.viva.news.app.adapter.SearchResultAdapter;
import id.co.viva.news.app.model.SearchResult;

/**
 * Created by reza on 13/10/14.
 */
public class ActSearchResult extends ActBase implements AdapterView.OnItemClickListener {

    private TextView tvSearchResult;
    private ListView listSearchResult;
    private String query;
    private boolean isInternetPresent = false;
    private JSONArray jsonArrayResponses;
    private ArrayList<SearchResult> resultArrayList;
    private RelativeLayout loading_layout;
    private TextView tvNoResult;
    private AnimationAdapter mAnimAdapter;
    private Analytics analytics;

    private String id ;
    private String kanal ;
    private String image_url ;
    private String title ;
    private String slug ;
    private String date_publish ;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.act_search_result, null, false);
        mDrawerLayout.addView(contentView, 0);

        getActionBar().setTitle("Pencarian");

        isInternetPresent = VivaApp.getInstance().getConnectionStatus().isConnectingToInternet();

        tvSearchResult = (TextView)findViewById(R.id.text_search_result);
        listSearchResult = (ListView)findViewById(R.id.list_search_result);
        listSearchResult.setOnItemClickListener(this);
        tvNoResult = (TextView)findViewById(R.id.text_no_result);
        loading_layout = (RelativeLayout)findViewById(R.id.loading_progress_layout);

        if(isInternetPresent) {
            tvNoResult.setVisibility(View.GONE);
            loading_layout.setVisibility(View.VISIBLE);
            resultArrayList = new ArrayList<SearchResult>();
            handleIntent(getIntent());
        } else {
            Toast.makeText(VivaApp.getInstance(), R.string.title_no_connection, Toast.LENGTH_SHORT).show();
            loading_layout.setVisibility(View.GONE);
            tvNoResult.setVisibility(View.VISIBLE);
            tvSearchResult.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            tvSearchResult.setText("Hasil Pencarian : " + query);

            analytics = new Analytics();
            analytics.getAnalyticByATInternet(Constant.SEARCH_RESULT_PAGE + query.toUpperCase());
            analytics.getAnalyticByGoogleAnalytic(Constant.SEARCH_RESULT_PAGE + query.toUpperCase());

            StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.URL_SEARCH + query,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String volleyResponse) {
                            Log.i(Constant.TAG, "SEARCH RESPONSES : " + volleyResponse);
                            try {
                                JSONObject jsonObject = new JSONObject(volleyResponse);
                                JSONObject response = jsonObject.getJSONObject(Constant.response);
                                jsonArrayResponses = response.getJSONArray(Constant.search);
                                if(jsonArrayResponses != null) {
                                    for(int i=0; i<jsonArrayResponses.length(); i++) {
                                        JSONObject jsonHeadline = jsonArrayResponses.getJSONObject(i);
                                        id = jsonHeadline.getString(Constant.id);
                                        kanal = jsonHeadline.getString(Constant.kanal);
                                        image_url = jsonHeadline.getString(Constant.image_url);
                                        title = jsonHeadline.getString(Constant.title);
                                        slug = jsonHeadline.getString(Constant.slug);
                                        date_publish = jsonHeadline.getString(Constant.date_publish);
                                        url = jsonHeadline.getString(Constant.url);
                                        resultArrayList.add(new SearchResult(id, kanal, image_url,
                                                title, slug, date_publish, url));
                                        Log.i(Constant.TAG, "SEARCH RESULTS : " + resultArrayList.get(i).getTitle());
                                    }
                                }
                                if (resultArrayList.size() > 0 || !resultArrayList.isEmpty()) {
                                    mAnimAdapter = new ScaleInAnimationAdapter(new SearchResultAdapter(VivaApp.getInstance(), resultArrayList));
                                    mAnimAdapter.setAbsListView(listSearchResult);
                                    listSearchResult.setAdapter(mAnimAdapter);
                                    mAnimAdapter.notifyDataSetChanged();
                                    loading_layout.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyError.getMessage();
                }
            });

            stringRequest.setShouldCache(true);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    Constant.TIME_OUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VivaApp.getInstance().getRequestQueue().getCache().invalidate(Constant.URL_SEARCH + query, true);
            VivaApp.getInstance().getRequestQueue().getCache().get(Constant.URL_SEARCH + query);
            VivaApp.getInstance().addToRequestQueue(stringRequest, Constant.JSON_REQUEST);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if(resultArrayList.size() > 0) {
            SearchResult searchResult = resultArrayList.get(position);
            Log.i(Constant.TAG, "ID : " + searchResult.getId());
            Bundle bundle = new Bundle();
            bundle.putString("id", searchResult.getId());
            bundle.putString("type", "search");
            bundle.putString("kanal", searchResult.getKanal());
            bundle.putString("shared_url", searchResult.getUrl());
            Intent intent = new Intent(VivaApp.getInstance(), ActDetailContentDefault.class);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_frag_default, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

}
