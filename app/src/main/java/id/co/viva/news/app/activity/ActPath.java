package id.co.viva.news.app.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import id.co.viva.news.app.Constant;
import id.co.viva.news.app.R;
import id.co.viva.news.app.model.Path;

/**
 * Created by reza on 11/12/14.
 */
public class ActPath extends FragmentActivity {

    private WebView mWebiew;
    private ProgressBar mProgressBar;
    public static Path socmedPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_path);
        getHeaderActionBar();
        defineView();
        try {
            mWebiew.loadUrl(Constant.PATH_AUTHENTICATE_URL + "?response_type=code&client_id=" +
                    Constant.PATH_CLIENT_ID);
            Log.i(Constant.TAG, "URL FIRST : " + Constant.PATH_AUTHENTICATE_URL + "?response_type=code&client_id=" +
                    Constant.PATH_CLIENT_ID);
            mWebiew.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    mProgressBar.setVisibility(View.VISIBLE);
                    if(url != null) {
                        Log.i(Constant.TAG, "URL : " + url);
                        String redirect_url = Constant.CONFIG_PATH_REDIRECT_URL;
                        String response_type = Constant.CONFIG_PATH_RESPONSE_TYPE;
                        if(url.startsWith(redirect_url)) {
                            socmedPath.setPath_code(url.replace(redirect_url + "?" + response_type + "=", ""));
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    mProgressBar.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void defineView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mWebiew = (WebView) findViewById(R.id.web_path);
        socmedPath = new Path();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getHeaderActionBar() {
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(R.color.header_headline_terbaru_new)));
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setIcon(R.drawable.logo_viva_coid_second);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

    }

}
