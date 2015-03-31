package id.co.viva.news.app.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.co.viva.news.app.R;
import id.co.viva.news.app.component.ZoomImageView;

/**
 * Created by reza on 31/12/14.
 */
public class ActDetailPhotoThumb extends ActionBarActivity {

    private ZoomImageView imageView;
    private TextView textView;
    private String photoUrl;
    private String image_caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        getHeaderActionBar();

        Bundle bundle = getIntent().getExtras();
        photoUrl = bundle.getString("photoUrl");
        image_caption = bundle.getString("image_caption");
        setContentView(R.layout.act_detail_photo_thumb);

        imageView = (ZoomImageView)findViewById(R.id.img_thumb_content_dialog);
        imageView.setMaxZoom(4f);
        textView = (TextView)findViewById(R.id.title_thumb_content_dialog);

        if(photoUrl.length() > 0) {
            Picasso.with(this).load(photoUrl).into(imageView);
        }
        if(image_caption.length() > 0) {
            textView.setText(image_caption);
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

    private void getHeaderActionBar() {
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.black));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Detail Foto");
    }

}
