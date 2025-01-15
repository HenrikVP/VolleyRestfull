package dk.tec.volleyrestfull;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ApiCalls {
    RequestQueue requestQueue;

    public ApiCalls(Context ctx){
        requestQueue = Volley.newRequestQueue(ctx);
    }
    public <T> void getAll(String uri, Type type, ApiCallBack<List<T>> callback) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://192.168.0.246:8585/" + uri,
                response -> {
                    callback.onSucces(new Gson().fromJson(response, type));
                },
                error -> Log.e("******** Volley ********", "Error :" + error)
        );
        requestQueue.add(request);
    }
}
