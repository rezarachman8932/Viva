package id.co.viva.news.app;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by rezarachman on 02/10/14.
 */
public class Constant {

    public static final int TIME_OUT = 3000;
    public static final int TIME_OUT_LONG = 6000;
    public static final int TIME_OUT_REGISTRATION = 15000;
    public static final int PROFILE_PIC_SIZE = 400;
    public final static int NUMBER_OF_TOP_LIST_ITEMS = 4;
    public final static String URL_FACEBOOK_PHOTO = "http://graph.facebook.com/";

    public static final String CODE_VIVA = "10";
    public static final String CODE_FACEBOOK = "11";
    public static final String CODE_PATH = "12";
    public static final String CODE_GPLUS = "13";

    public static final int MIN_TIME_BW_UPDATES = 20000;

    public static final String TAG_LOCATION_NAME = "nama";
    public static final String TAG_LOCATION_PROVINCE_ID = "id_propinsi";
    public static final String TAG_LOCATION_KABUPATEN_ID = "id_kabupaten";

    public static final String ADAPTER_PROVINCE = "adapter_province";
    public static final String ADAPTER_CITY = "adapter_city";
    public static final String ADAPTER_CHANNEL_BOLA = "adapter_channel_bola";
    public static final String ADAPTER_CHANNEL_LIFE = "adapter_channel_life";
    public static final String ADAPTER_CHANNEL_NEWS = "adapter_channel_news";

    public static final String fragment_terbaru = "class id.co.viva.news.app.fragment.TerbaruFragment";
    public static final String fragment_bola = "class id.co.viva.news.app.fragment.BolaFragment";
    public static final String fragment_life = "class id.co.viva.news.app.fragment.LifeFragment";
    public static final String fragment_news = "class id.co.viva.news.app.fragment.NewsFragment";

    public static final String FIRST_INSTALL_PROFILE = "first_install_profile";
    public static final String FIRST_INSTALL_TUTORIAL = "first_install_tutorial";

    public static final String MOVE_TUTORIAL = "move_tutorial";
    public static final String MOVE_APPLICATION = "move_application";

    public static final String FAVORITES_LIST = "favorites_list";
    public static final String FAVORITES_LIST_SIZE = "favorites_list_size";
    public static final String LOGIN_STATES_EMAIL = "login_states_email";
    public static final String LOGIN_STATES_FULLNAME = "login_states_fullname";
    public static final String LOGIN_STATES_URL_PHOTO = "login_states_url_photo";
    public static final String LOGIN_STATES_USER_SOCIAL_ID = "login_states_user_social_id";
    public static final String LOGIN_STATES_APP_ID = "login_states_app_id";
    public static final String LOGIN_STATES_ISLOGIN = "isLogin";
    public static final String LOGIN_STATES_GENDER = "login_states_gender";
    public static final String LOGIN_STATES_CITY = "login_states_city";
    public static final String LOGIN_STATES_BIRTHDATE = "login_states_birthdate";
    public static final String LOGIN_STATES_PROVINCE = "login_states_province";
    public static final String LOGIN_STATES_COUNTRY = "login_states_country";

    public static final String LINK_YOUTUBE = "www.youtube.com";
    public static final String LINK_VIDEO_VIVA = "video.viva.co.id";
    public static final String LINK_ARTICLE_VIVA = "viva.co.id/news/read";

    public static final String GCM_SENDER_ID = "702339857576";
    public static final String GCM_URL_BACKEND_SERVER = "http://api.vivall.tv/rest/gcmreg";

    public static final String TAG = VivaApp.class.getSimpleName();
    public static final String TAG_GCM = "VIVA-GCM";

    private static String BASE_URL_NEW = "http://api.viva.co.id/v/208/";
    public static String TUTORIAL_IMAGES_URL = BASE_URL_NEW + "coach";
    public static String NEW_HEADLINE = BASE_URL_NEW + "headlinelist/";
    public static String NEW_TERBARU = BASE_URL_NEW + "terbarulist/";
    public static String NEW_NEWS = BASE_URL_NEW + "knews";
    public static String NEW_LIFE = BASE_URL_NEW + "klife";
    public static String NEW_BOLA = BASE_URL_NEW + "kbola";
    public static String NEW_SEARCH = BASE_URL_NEW + "search/";
    public static String NEW_KANAL = BASE_URL_NEW + "kanal/";
    public static String NEW_DETAIL = BASE_URL_NEW + "detail/";
    public static String NEW_LOGIN = BASE_URL_NEW + "login";
    public static String NEW_REGISTER = BASE_URL_NEW + "register2";
    public static String NEW_COMMENTS = BASE_URL_NEW + "sendcomment/";
    public static String NEW_RATES = BASE_URL_NEW + "sendrate/";
    public static String NEW_LIST_COMMENT = BASE_URL_NEW + "commentlist";
    public static String NEW_FORGOT_PASSWORD = BASE_URL_NEW + "forgotpass";
    public static final String NEW_GET_PROVINCE = BASE_URL_NEW + "location/";
    public static final String NEW_UPDATE_PROFILE = BASE_URL_NEW + "updateprofile";
    public static final String ALL_NEWS_URL = "/lv/1/s/0/type/terbaru";
    public static final String ALL_NEWS_URL_PAGING = "/lv/1/published/";
    public static final String SUB_CHANNEL_LV_2_URL = "/lv/2/s/0";
    public static final String SUB_CHANNEL_LV_1_URL = "/lv/1/s/0";
    public static final String SUB_CHANNEL_LV_2_URL_PAGING = "/lv/2/s/";
    public static final String SUB_CHANNEL_LV_1_URL_PAGING = "/lv/1/s/";

    public static final String JSON_REQUEST = "json_obj_req";

    public static final String AllNews = "Semua Berita";
    public static final String response = "response";
    public static final String timestamp = "timestamp";
    public static final String headlines = "headlines";
    public static final String comment_list = "comment_list";
    public static final String related_article = "related_article";
    public static final String detail = "detail";
    public static final String url = "url";
    public static final String search = "search";
    public static final String id = "id";
    public static final String title = "title";
    public static final String slug = "slug";
    public static final String kanal = "kanal";
    public static final String image_url = "image_url";
    public static final String date_publish = "date_publish";
    public static final String source = "source";
    public static final String reporter_name = "reporter_name";
    public static final String content = "content";
    public static final String data = "data";

    public static final String name = "name";
    public static final String parent_id = "parent_id";
    public static final String comment_text = "comment_text";
    public static final String app_id = "app_id";
    public static final String submitted_date = "submitted_date";
    public static final String status = "status";
    public static final String image_caption = "image_caption";
    public static final String content_video = "content_video";
    public static final String text = "text";

    public static final String article_id = "article_id";
    public static final String related_article_id = "related_article_id";
    public static final String related_title = "related_title";
    public static final String related_channel_level_1_id = "related_channel_level_1_id";
    public static final String channel_id = "channel_id";
    public static final String related_date_publish = "related_date_publish";
    public static final String image = "image";
    public static final String content_images = "content_images";

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;

    public static final String AT_LOG_DOMAIN = ".ati-host.net";
    public static final String AT_SUB_DOMAIN = "logw351";
    public static final String AT_SITE_ID = "551156";
    public static final String AT_SUB_SITE = "1";

    public static final String PATH_REDIRECT = "http://viva.co.id";
    public static final String PATH_CLIENT_ID = "29eb3edd59992f2418946e028774d91004bd05a9";
    public static final String PATH_SECRET_ID = "e8b08b8d51b679a3a33de1089ec373d6f5a38419";
    public static final String PATH_AUTHENTICATE_URL = "https://partner.path.com/oauth2/authenticate";
    public static final String PATH_ACCESS_TOKEN_URL = "https://partner.path.com/oauth2/access_token";
    public static final String PATH_USER_INFO_URL = "https://partner.path.com/1/user/self";
    public static final String ATTRIBUTE_PATH_ACCESS_TOKEN = "access_token";
    public static final String ATTRIBUTE_PATH_USER_ID = "user_id";

    public static final String BERITA_SEKITAR_PAGE = "BERITA_SEKITAR_HAL_";
    public static final String BERITA_SEKITAR_DETAIL_PAGE = "BERITA_SEKITAR_DETAIL_";
    public static final String HEADLINE_PAGE = "HEADLINE_HAL_";
    public static final String HEADLINE_DETAIL_PAGE = "HEADLINE_DETAIL_";
    public static final String TERBARU_PAGE = "TERBARU_HAL_";
    public static final String TERBARU_DETAIL_PAGE = "TERBARU_DETAIL_";

    public static final String KANAL_NEWS_PAGE = "KANAL_NEWS_PAGE";
    public static final String SUBKANAL_NEWS_PAGE = "SUBKANAL_NEWS_";
    public static final String DETAIL_CONTENT_NEWS_PAGE = "DetailArticle_NEWS_";

    public static final String KANAL_BOLA_PAGE = "KANAL_BOLA_PAGE";
    public static final String SUBKANAL_BOLA_PAGE = "SUBKANAL_BOLA_";
    public static final String DETAIL_CONTENT_BOLA_PAGE = "DetailArticle_BOLA_";

    public static final String KANAL_LIFE_PAGE = "KANAL_LIFE_PAGE";
    public static final String SUBKANAL_LIFE_PAGE = "SUBKANAL_LIFE_";
    public static final String DETAIL_CONTENT_LIFE_PAGE = "DetailArticle_LIFE_";

    public static final String SEARCH_RESULT_PAGE = "SEARCH_RESULT_PAGE_";
    public static final String FROM_SEARCH_RESULT_DETAIL_CONTENT = "FROM_SEARCH_RESULT_DETAIL_CONTENT_";
    public static final String FROM_RELATED_ARTICLE_DETAIL_CONTENT = "FROM_RELATED_ARTICLE_DETAIL_CONTENT_";
    public static final String FROM_EDITOR_CHOICE = "FROM_EDITOR_CHOICE_";
    public static final String FAVORITES_PAGE = "FAVORITES_PAGE";
    public static final String FAVORITES_PAGE_DETAIL = "FAVORITES_PAGE_DETAIL_";
    public static final String COMMENTED_ARTICLE = "COMMENTED_ARTICLE";
    public static final String RATING_ARTICLE = "RATING_ARTICLE";
    public static final String ARTICLE_FROM_NOTIFICATION = "ARTICLE_FROM_NOTIFICATION";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blur(Context ctx, Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
        RenderScript rs = RenderScript.create(ctx);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ActionBar.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            time *= 1000;
        }
        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "baru saja";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "satu menit yang lalu";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " menit yang lalu";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "satu jam yang lalu";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " jam yang lalu";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "kemarin";
        } else {
            return diff / DAY_MILLIS + " hari yang lalu";
        }
    }

    public static int getDynamicImageSize(Context mCtx) {
        WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String getArticleViva(String url) {
        Log.i(Constant.TAG, "URL : " + url);
        String[] separated = url.split("/");
        String urlSplit;
        if (separated.length < 5) {
            urlSplit = separated[3];
        } else {
            urlSplit = separated[5];
        }
        Log.i(Constant.TAG, "URL Split : " + urlSplit);
        String[] splitter = urlSplit.split("-");
        String article_id = splitter[0];
        Log.i(Constant.TAG, "Article Id : " + article_id);
        return  article_id;
    }

}
