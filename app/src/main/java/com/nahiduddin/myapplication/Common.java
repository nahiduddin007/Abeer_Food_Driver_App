package com.nahiduddin.myapplication;

import com.nahiduddin.myapplication.Remote.IGoogleApi;
import com.nahiduddin.myapplication.Remote.RetrofitClient;

public class Common {


    public static final String baseURL = "https://googleapis.com";
    public  static IGoogleApi getGoogleApi()
    {
         return RetrofitClient.getClient(baseURL).create(IGoogleApi.class);

    }
}
