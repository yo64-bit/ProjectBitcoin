package com.example.android.projectbitcoin;

import org.json.JSONException;
import org.json.JSONObject;

public class BitcoinModel  {

String rate;
    public static BitcoinModel fromJson(JSONObject jsonObject){
BitcoinModel model = new BitcoinModel();


        try {
           model.rate =jsonObject.getJSONObject("bpi").getJSONObject("INR").getString("rate");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return model;
    }

    public String getRate() {
        return rate;
    }
}


