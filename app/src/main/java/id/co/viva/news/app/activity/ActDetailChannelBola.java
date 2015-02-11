package id.co.viva.news.app.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import id.co.viva.news.app.Constant;
import id.co.viva.news.app.Global;
import id.co.viva.news.app.R;
import id.co.viva.news.app.adapter.ChannelBolaAdapter;
import id.co.viva.news.app.component.LoadMoreListView;
import id.co.viva.news.app.component.ProgressWheel;
import id.co.viva.news.app.interfaces.OnLoadMoreListener;
import id.co.viva.news.app.model.ChannelBola;
import id.co.viva.news.app.services.Analytics;

/**
 * Created by reza on 27/10/14.
 */
public class ActDetailChannelBola extends ActBase implements
        OnLoadMoreListener, AdapterView.OnItemClickListener, View.OnClickListener {

    public static ArrayList<ChannelBola> channelBolaArrayList;
    private String id;
    private String channel_title;
    private String timeStamp;
    private int paging = 1;
    private boolean isInternetPresent = false;
    private ProgressWheel progressWheel;
    private TextView tvNoResult;
    private TextView tvChannel;
    private LoadMoreListView listView;
    private String cachedResponse;
    private AnimationAdapter mAnimAdapter;
    private JSONArray jsonArrayResponses, jsonArraySegmentHeadline;
    private Analytics analytics;
    private ChannelBolaAdapter adapter;
    private int dataSize = 0;
    private String data;
    private RippleView rippleView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.item_detail_channel_bola, null, false);
        mDrawerLayout.addView(contentView, 0);

        isInternetPresent = Global.getInstance(this)
                .getConnectionStatus().isConnectingToInternet();

        //Get Intent Data
        getIntentData();

        //Send Analytics
        setAnalytics();

        //Set Theme
        setActionBarTheme();

        //Define All Views
        defineViews();

        if(isInternetPresent) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl(channel_title),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String volleyResponse) {
                            Log.i(Constant.TAG, "CHANNEL BOLA RESPONSE : " + volleyResponse);
                            try {
                                JSONObject jsonObject = new JSONObject(volleyResponse);
                                jsonArrayResponses = jsonObject.getJSONArray(Constant.response);
                                if (jsonArrayResponses != null) {
                                    JSONObject objHeadline = jsonArrayResponses.getJSONObject(0);
                                    if (objHeadline != null) {
                                        jsonArraySegmentHeadline = objHeadline.getJSONArray(Constant.headlines);
                                        for (int i = 0; i < jsonArraySegmentHeadline.length(); i++) {
                                            JSONObject jsonHeadline = jsonArraySegmentHeadline.getJSONObject(i);
                                            String id = jsonHeadline.getString(Constant.id);
                                            String title = jsonHeadline.getString(Constant.title);
                                            String kanal = jsonHeadline.getString(Constant.kanal);
                                            String image_url = jsonHeadline.getString(Constant.image_url);
                                            String date_publish = jsonHeadline.getString(Constant.date_publish);
                                            String url = jsonHeadline.getString(Constant.url);
                                            String timestamp = jsonHeadline.getString(Constant.timestamp);
                                            channelBolaArrayList.add(new ChannelBola(id, title, kanal,
                                                    image_url, date_publish, url, timestamp));
                                            Log.i(Constant.TAG, "CHANNEL BOLA : " + channelBolaArrayList.get(i).getTitle());
                                        }
                                    }
                                }

                                timeStamp = channelBolaArrayList.get(channelBolaArrayList.size()-1).getTimestamp();

                                if (channelBolaArrayList.size() > 0 || !channelBolaArrayList.isEmpty()) {
                                    mAnimAdapter = new ScaleInAnimationAdapter(adapter);
                                    mAnimAdapter.setAbsListView(listView);
                                    listView.setAdapter(mAnimAdapter);
                                    mAnimAdapter.notifyDataSetChanged();
                                    progressWheel.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            volleyError.getMessage();
                            progressWheel.setVisibility(View.GONE);
                            rippleView.setVisibility(View.VISIBLE);
                        }
                    });
            stringRequest.setShouldCache(true);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    Constant.TIME_OUT,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Global.getInstance(this).getRequestQueue().getCache().invalidate(getUrl(channel_title), true);
            Global.getInstance(this).getRequestQueue().getCache().get(getUrl(channel_title));
            Global.getInstance(this).addToRequestQueue(stringRequest, Constant.JSON_REQUEST);
        } else {
            Toast.makeText(this, R.string.title_no_connection, Toast.LENGTH_SHORT).show();
            if(Global.getInstance(this).getRequestQueue().getCache().get(getUrl(channel_title)) != null) {
                cachedResponse = new String(Global.getInstance(this).
                        getRequestQueue().getCache().get(getUrl(channel_title)).data);
                Log.i(Constant.TAG, "CHANNEL BOLA CACHED RESPONSE : " + cachedResponse);
                try{
                    JSONObject jsonObject = new JSONObject(cachedResponse);
                    jsonArrayResponses = jsonObject.getJSONArray(Constant.response);
                    if(jsonArrayResponses != null) {
                        JSONObject objHeadline = jsonArrayResponses.getJSONObject(0);
                        if (objHeadline != null) {
                            jsonArraySegmentHeadline = objHeadline.getJSONArray(Constant.headlines);
                            for(int i=0; i<jsonArraySegmentHeadline.length(); i++) {
                                JSONObject jsonHeadline = jsonArraySegmentHeadline.getJSONObject(i);
                                String id = jsonHeadline.getString(Constant.id);
                                String title = jsonHeadline.getString(Constant.title);
                                String kanal = jsonHeadline.getString(Constant.kanal);
                                String image_url = jsonHeadline.getString(Constant.image_url);
                                String date_publish = jsonHeadline.getString(Constant.date_publish);
                                String url = jsonHeadline.getString(Constant.url);
                                String timestamp = jsonHeadline.getString(Constant.timestamp);
                                channelBolaArrayList.add(new ChannelBola(id, title, kanal,
                                        image_url, date_publish, url, timestamp));
                                Log.i(Constant.TAG, "CHANNEL BOLA CACHED : " + channelBolaArrayList.get(i).getTitle());
                            }
                        }
                    }

                    timeStamp = channelBolaArrayList.get(channelBolaArrayList.size()-1).getTimestamp();

                    if(channelBolaArrayList.size() > 0 || !channelBolaArrayList.isEmpty()) {
                        mAnimAdapter = new ScaleInAnimationAdapter(adapter);
                        mAnimAdapter.setAbsListView(listView);
                        listView.setAdapter(mAnimAdapter);
                        mAnimAdapter.notifyDataSetChanged();
                        progressWheel.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                progressWheel.setVisibility(View.GONE);
                tvNoResult.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onLoadMore() {
        data = String.valueOf(dataSize += 10);
        paging += 1;
        if(isInternetPresent) {
            setAnalytics(String.valueOf(paging));
            StringRequest stringRequest = new StringRequest(Request.Method.GET, getPagingUrl(channel_title, data, timeStamp),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String volleyResponse) {
                            Log.i(Constant.TAG, "CHANNEL BOLA RESPONSE : " + volleyResponse);
                            try {
                                JSONObject jsonObject = new JSONObject(volleyResponse);
                                jsonArrayResponses = jsonObject.getJSONArray(Constant.response);
                                if (jsonArrayResponses != null) {
                                    JSONObject objHeadline = jsonArrayResponses.getJSONObject(0);
                                    if (objHeadline != null) {
                                        jsonArraySegmentHeadline = objHeadline.getJSONArray(Constant.headlines);
                                        for (int i = 0; i < jsonArraySegmentHeadline.length(); i++) {
                                            JSONObject jsonHeadline = jsonArraySegmentHeadline.getJSONObject(i);
                                            String id = jsonHeadline.getString(Constant.id);
                                            String title = jsonHeadline.getString(Constant.title);
                                            String kanal = jsonHeadline.getString(Constant.kanal);
                                            String image_url = jsonHeadline.getString(Constant.image_url);
                                            String date_publish = jsonHeadline.getString(Constant.date_publish);
                                            String url = jsonHeadline.getString(Constant.url);
                                            String timestamp = jsonHeadline.getString(Constant.timestamp);
                                            channelBolaArrayList.add(new ChannelBola(id, title, kanal,
                                                    image_url, date_publish, url, timestamp));
                                        }
                                    }
                                }

                                timeStamp = channelBolaArrayList.get(channelBolaArrayList.size()-1).getTimestamp();

                                if (channelBolaArrayList.size() > 0 || !channelBolaArrayList.isEmpty()) {
                                    mAnimAdapter = new ScaleInAnimationAdapter(adapter);
                                    mAnimAdapter.setAbsListView(listView);
                                    mAnimAdapter.notifyDataSetChanged();
                                    listView.onLoadMoreComplete();
                                }
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            volleyError.getMessage();
                        }
                    });
            stringRequest.setShouldCache(true);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    Constant.TIME_OUT,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Global.getInstance(this).getRequestQueue().getCache().invalidate(getPagingUrl(channel_title, data, timeStamp), true);
            Global.getInstance(this).getRequestQueue().getCache().get(getPagingUrl(channel_title, data, timeStamp));
            Global.getInstance(this).addToRequestQueue(stringRequest, Constant.JSON_REQUEST);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if(channelBolaArrayList.size() > 0) {
            ChannelBola channelBola = channelBolaArrayList.get(position);
            Log.i(Constant.TAG, "ID : " + channelBola.getId());
            Bundle bundle = new Bundle();
            bundle.putString("id", channelBola.getId());
            Intent intent = new Intent(this, ActDetailContentBola.class);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.layout_ripple_view) {
            if(isInternetPresent) {
                rippleView.setVisibility(View.GONE);
                progressWheel.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl(channel_title),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String volleyResponse) {
                                Log.i(Constant.TAG, "CHANNEL BOLA RESPONSE : " + volleyResponse);
                                try {
                                    JSONObject jsonObject = new JSONObject(volleyResponse);
                                    jsonArrayResponses = jsonObject.getJSONArray(Constant.response);
                                    if (jsonArrayResponses != null) {
                                        JSONObject objHeadline = jsonArrayResponses.getJSONObject(0);
                                        if (objHeadline != null) {
                                            jsonArraySegmentHeadline = objHeadline.getJSONArray(Constant.headlines);
                                            for (int i = 0; i < jsonArraySegmentHeadline.length(); i++) {
                                                JSONObject jsonHeadline = jsonArraySegmentHeadline.getJSONObject(i);
                                                String id = jsonHeadline.getString(Constant.id);
                                                String title = jsonHeadline.getString(Constant.title);
                                                String kanal = jsonHeadline.getString(Constant.kanal);
                                                String image_url = jsonHeadline.getString(Constant.image_url);
                                                String date_publish = jsonHeadline.getString(Constant.date_publish);
                                                String url = jsonHeadline.getString(Constant.url);
                                                String timestamp = jsonHeadline.getString(Constant.timestamp);
                                                channelBolaArrayList.add(new ChannelBola(id, title, kanal,
                                                        image_url, date_publish, url, timestamp));
                                                Log.i(Constant.TAG, "CHANNEL BOLA : " + channelBolaArrayList.get(i).getTitle());
                                            }
                                        }
                                    }

                                    timeStamp = channelBolaArrayList.get(channelBolaArrayList.size()-1).getTimestamp();

                                    if (channelBolaArrayList.size() > 0 || !channelBolaArrayList.isEmpty()) {
                                        mAnimAdapter = new ScaleInAnimationAdapter(adapter);
                                        mAnimAdapter.setAbsListView(listView);
                                        listView.setAdapter(mAnimAdapter);
                                        mAnimAdapter.notifyDataSetChanged();
                                        progressWheel.setVisibility(View.GONE);
                                        if(rippleView.getVisibility() == View.VISIBLE) {
                                            rippleView.setVisibility(View.GONE);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                volleyError.getMessage();
                                progressWheel.setVisibility(View.GONE);
                                rippleView.setVisibility(View.VISIBLE);
                            }
                        });
                stringRequest.setShouldCache(true);
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        Constant.TIME_OUT,
                        0,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Global.getInstance(this).getRequestQueue().getCache().invalidate(getUrl(channel_title), true);
                Global.getInstance(this).getRequestQueue().getCache().get(getUrl(channel_title));
                Global.getInstance(this).addToRequestQueue(stringRequest, Constant.JSON_REQUEST);
            }
        } else if(view.getId() == R.id.fab) {
            listView.setSelection(0);
        }
    }

    private String getUrl(String channelTitle) {
        String url_channel;
        if(channelTitle.equalsIgnoreCase(Constant.AllNews)) {
            url_channel = Constant.NEW_KANAL + "ch/" + id + Constant.ALL_NEWS_URL;
        } else {
            url_channel = Constant.NEW_KANAL + "ch/" + id + Constant.SUB_CHANNEL_LV_2_URL;
        }
        return url_channel;
    }

    private String getPagingUrl(String channelTitle, String page, String timeStamp) {
        String url_channel_paging;
        if(channelTitle.equalsIgnoreCase(Constant.AllNews)) {
            url_channel_paging = Constant.NEW_KANAL + "ch/" + id + Constant.ALL_NEWS_URL_PAGING + timeStamp + "/type/terbaru";
        } else {
            url_channel_paging = Constant.NEW_KANAL + "ch/" + id + Constant.SUB_CHANNEL_LV_2_URL_PAGING + page;
        }
        return url_channel_paging;
    }

    private void setAnalytics() {
        analytics = new Analytics(this);
        analytics.getAnalyticByATInternet(Constant.SUBKANAL_BOLA_PAGE
                + channel_title.toUpperCase()
                + "_"
                + "HAL_"
                + paging);
        analytics.getAnalyticByGoogleAnalytic(Constant.SUBKANAL_BOLA_PAGE
                + channel_title.toUpperCase()
                + "_"
                + "HAL_"
                + paging);
    }

    private void setAnalytics(String page) {
        if(analytics == null) {
            analytics = new Analytics(this);
        }
        analytics.getAnalyticByATInternet(Constant.SUBKANAL_BOLA_PAGE
                + channel_title.toUpperCase()
                + "_"
                + "HAL_"
                + page);
        analytics.getAnalyticByGoogleAnalytic(Constant.SUBKANAL_BOLA_PAGE
                + channel_title.toUpperCase()
                + "_"
                + "HAL_"
                + page);
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        channel_title = bundle.getString("channel_title");
    }

    private void setActionBarTheme() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.color_bola));
        getActionBar().setBackgroundDrawable(colorDrawable);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setIcon(R.drawable.logo_viva_coid_second);
    }

    private void defineViews() {
        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);

        rippleView = (RippleView) findViewById(R.id.layout_ripple_view);
        rippleView.setVisibility(View.GONE);
        rippleView.setOnClickListener(this);

        tvChannel = (TextView) findViewById(R.id.text_channel);
        tvChannel.setText(channel_title.toUpperCase());

        tvNoResult = (TextView) findViewById(R.id.text_no_result_detail_channel_bola);
        tvNoResult.setVisibility(View.GONE);

        channelBolaArrayList = new ArrayList<ChannelBola>();
        adapter = new ChannelBolaAdapter(this, channelBolaArrayList);

        listView = (LoadMoreListView) findViewById(R.id.list_channel_bola);
        listView.setOnItemClickListener(this);
        listView.setOnLoadMoreListener(this);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.GONE);
        floatingActionButton.attachToListView(listView, new FloatingActionButton.FabOnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                int firstIndex = listView.getFirstVisiblePosition();
                if(firstIndex > Constant.NUMBER_OF_TOP_LIST_ITEMS) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                } else {
                    floatingActionButton.setVisibility(View.GONE);
                }
            }
        });
        floatingActionButton.setOnClickListener(this);
    }

}
