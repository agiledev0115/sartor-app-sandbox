package com.sartor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sartor.data.model.Brands;
import com.sartor.data.model.ProductImage;
import com.sartor.data.model.myModels.MyProduct;
import com.sartor.util.constants.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {


    private static final String TAG = TestActivity.class.getName();
    private Button btnRequest;

    TextView textView;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";

    String token = "Klbgh0RgT06NJ2SCyEPeuypkV7MxTJtFS3FAsAWZrwvKUBOKsdLS/BrUMLCqEI1bKXOU7J+B08thv7mQIMUjUA==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        textView = findViewById(R.id.text_view);

//
//        ArrayList<ProductImage> productImages=new ArrayList<>();
//
//        MyProduct product=null;
//
//        ProductImage image=new ProductImage(product.getImg().getImg0());
//
//        productImages.add(image);
//
//

        ArrayList<Brands> list = new ArrayList<>();

        updateFavorite();
    }


    private void sendAndRequestResponse() {


        url = Constant.BASE_URL + "api/brands/products/61b6eeeb3e05a78a00f99e91";


        mRequestQueue = Volley.newRequestQueue(this);

        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                textView.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
                textView.setText(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("Content-Type", "application/json");
                headerMap.put("Authorization", "Bearer " + token);
                return headerMap;
            }
        };

        SearchView searchView;

        mRequestQueue.add(mStringRequest);
    }


    private void updateFavorite() {


        url = Constant.BASE_URL + "api/favorite";


        mRequestQueue = Volley.newRequestQueue(this);

        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                textView.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
                textView.setText(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("Content-Type", "application/json");
                headerMap.put("Authorization", "Bearer " + token);
                return headerMap;
            }


            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("product", "61b6ef5c3e05a78a00f99e95");

                return params;
            }
        };

        mRequestQueue.add(mStringRequest);
    }

}
