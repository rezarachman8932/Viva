package id.co.viva.news.app.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import id.co.viva.news.app.R;
import id.co.viva.news.app.adapter.DetailContentAdapterNews;
import id.co.viva.news.app.component.ZoomOutPageTransformer;
import id.co.viva.news.app.model.ChannelNews;

/**
 * Created by reza on 24/10/14.
 */
public class ActDetailContentNews extends FragmentActivity {

    private String id;
    private String url_shared;
    private ViewPager viewPager;
    private DetailContentAdapterNews adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        url_shared = bundle.getString("url_shared");

        setContentView(R.layout.act_detail_content);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.color_news));
        getActionBar().setBackgroundDrawable(colorDrawable);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setIcon(R.drawable.logo_viva_coid_second);

        int position = 0;
        //TODO Handle go back from open url in browser
        for(ChannelNews channelnews : ActDetailChannelNews.channelNewsArrayList) {
            if(channelnews.getId().equals(id)) break;
            position++;
        }

        adapter = new DetailContentAdapterNews(getSupportFragmentManager(), ActDetailChannelNews.channelNewsArrayList);
        viewPager = (ViewPager)findViewById(R.id.vp_detail_content);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setCurrentItem(position);
        adapter.notifyDataSetChanged();
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
        getMenuInflater().inflate(R.menu.menu_detail_headline, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        ShareActionProvider myShareActionProvider = (ShareActionProvider) item.getActionProvider();
        Intent myIntent = new Intent();
        myIntent.setAction(Intent.ACTION_SEND);
        myIntent.putExtra(Intent.EXTRA_TEXT, url_shared);
        myIntent.setType("text/plain");
        myShareActionProvider.setShareIntent(myIntent);
        return true;
    }

}