package Repository;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import DAO.SuperHeroDao;
import Entity.SuperHeroEntity;
import TrackRoom.TrackRoomDatabase;
import Utils.Constant;
import Utils.VolleySingleton;

public class SuperHeroRepository {
    private Context mContext;
    private String name, realName, team, firstAppearance, createdBy,publisher,bio;
    private String imageUrl;
    private SuperHeroDao superHeroDao;
    private final int MY_SOCKET_TIMEOUT_MS = 30000;
    private RequestQueue mQueue;

    public SuperHeroRepository(Application application) {
        mContext = application.getApplicationContext();
        TrackRoomDatabase db = TrackRoomDatabase.getDatabase(application);
        superHeroDao = db.superHeroDao();
        mQueue = VolleySingleton.getInstance(application).getRequestQueue();
    }
   /* public LiveData<List<SuperHeroEntity>> fetchDataValue() {
        return dao.fetchHeroDetails();
    }*/
    public LiveData<List<SuperHeroEntity>> fetchData() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Constant.ROOT_URL, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("");
                            SuperHeroEntity[] repArray = new SuperHeroEntity[data.length()];
                            for (int i = 0; i < data.length(); i++) {
                                imageUrl = data.getJSONObject(i).getString("imageurl");
                                name = data.getJSONObject(i).getString("name");
                                realName = data.getJSONObject(i).getString("realname");
                                team = data.getJSONObject(i).getString("team");
                                firstAppearance = data.getJSONObject(i).getString("firstappearance");
                                createdBy = data.getJSONObject(i).getString("createdby");
                                publisher = data.getJSONObject(i).getString("publisher");
                                bio = data.getJSONObject(i).getString("bio");
                                repArray[i] = new SuperHeroEntity(name,realName,team,firstAppearance,createdBy,
                                        publisher,imageUrl,bio
                                );
                            }
                            new HeroListTask(superHeroDao).execute(repArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("TIMEZONE", "Asia/Kolkata");
                headers.put("Content-Type", "application/json");
                headers.put("Accept-Encoding", "gzip, deflate, br");
                headers.put("Accept-Language", "en-US,en;q=0.5");
                headers.put("Host", "simplifiedcoding.net");
                headers.put("Authorization", "Bearer ");
                return headers;
            }
        };
        req.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(req);
        return superHeroDao.fetchHeroDetails();
    }

    private static class HeroListTask extends AsyncTask<SuperHeroEntity, Void, Void> {
        private SuperHeroDao mAsyncDao;


        HeroListTask(SuperHeroDao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(SuperHeroEntity... superHeroEntities) {
            mAsyncDao.insertAll(superHeroEntities);
            return null;
        }
    }
}
