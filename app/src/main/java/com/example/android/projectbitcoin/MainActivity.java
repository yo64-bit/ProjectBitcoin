package com.example.android.projectbitcoin;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    ImageView im;
    ImageButton btn;
    String WEB_URL = "https://api.coindesk.com/v1/bpi/currentprice/INR.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
im = findViewById(R.id.imv);
btn = findViewById(R.id.refresh);
//im.setScaleType(ImageView.ScaleType.CENTER);


        text1 = findViewById(R.id.tv);

btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this,"Refreshed",Toast.LENGTH_SHORT).show();
        RotateAnimation rotate = new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatCount(Animation.ABSOLUTE);
        rotate.setRepeatMode(Animation.RELATIVE_TO_SELF);
        rotate.setInterpolator(new LinearInterpolator());
        btn.startAnimation(rotate);
        DoNetworking();
    }
});

    }

    @Override
    protected void onResume() {
        super.onResume();

        DoNetworking();
    }

    private void DoNetworking() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEB_URL,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("MY",  response.toString());
                BitcoinModel data = BitcoinModel.fromJson(response);


                update(data);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void update(BitcoinModel model) {
        text1.setText(new StringBuilder().append("1 Bitcoin equals:₹").append(model.getRate()).toString());

    }

}
