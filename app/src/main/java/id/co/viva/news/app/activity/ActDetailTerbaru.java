package id.co.viva.news.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.Toast;

import id.co.viva.news.app.R;
import id.co.viva.news.app.adapter.DetailTerbaruAdapter;
import id.co.viva.news.app.component.ZoomOutPageTransformer;
import id.co.viva.news.app.fragment.TerbaruFragment;
import id.co.viva.news.app.model.News;

/**
 * Created by reza on 14/10/14.
 */
public class ActDetailTerbaru extends FragmentActivity {

    private String id;
    private ViewPager viewPager;
    private DetailTerbaruAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        setContentView(R.layout.frag_detail_terbaru);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Terbaru");

        int position = 0;
        if(TerbaruFragment.newsArrayList != null) {
            if(TerbaruFragment.newsArrayList.size() > 0) {
                for(News news : TerbaruFragment.newsArrayList) {
                    if(news.getId().equals(id)) break;
                    position++;
                }
            }
        } else {
            Toast.makeText(this, R.string.label_error, Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

        adapter = new DetailTerbaruAdapter(getSupportFragmentManager(), TerbaruFragment.newsArrayList);
        viewPager = (ViewPager)findViewById(R.id.vp_news_detail);
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

}
