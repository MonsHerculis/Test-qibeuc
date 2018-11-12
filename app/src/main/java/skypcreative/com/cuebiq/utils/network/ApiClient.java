package skypcreative.com.cuebiq.utils.network;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import skypcreative.com.cuebiq.utils.Constants;


public class ApiClient {


    public static OkHttpClient okHttpClient = null;
    private static Retrofit requestsRetrofit = null;



    public static Retrofit getRequestsRetrofit() {


        if (requestsRetrofit == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            requestsRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return requestsRetrofit;

    }

}
