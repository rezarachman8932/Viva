package id.co.viva.news.app.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.Toast;

import id.co.viva.news.app.R;
import id.co.viva.news.app.adapter.DetailContentAdapterLife;
import id.co.viva.news.app.component.ZoomOutPageTransformer;
import id.co.viva.news.app.model.ChannelLife;

/**
 * Created by reza on 24/10/14.
 */
public class ActDetailContentLife extends ActionBarActivity {

    private String id;
    private String channel_title;
    private ViewPager viewPager;
    private DetailContentAdapterLife adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        channel_title = bundle.getString("channel_title");

        setContentView(R.layout.act_detail_content);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.color_life));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        if (channel_title != null) {
            getSupportActionBar().setTitle(channel_title);
        }

        int position = 0;
        if(ActDetailChannelLife.channelLifeArrayList != null) {
            if(ActDetailChannelLife.channelLifeArrayList.size() > 0) {
                for(ChannelLife channelLife : ActDetailChannelLife.channelLifeArrayList) {
                    if(channelLife.getId().equals(id)) break;
                    position++;
                }
            }
            adapter = new DetailContentAdapterLife(getSupportFragmentManager(), ActDetailChannelLife.channelLifeArrayList);
            viewPager = (ViewPager)findViewById(R.id.vp_detail_content);
            viewPager.setAdapter(adapter);
            viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            viewPager.setCurrentItem(position);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, R.string.label_error, Toast.LENGTH_SHORT).show();
            onBackPressed();
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

}
