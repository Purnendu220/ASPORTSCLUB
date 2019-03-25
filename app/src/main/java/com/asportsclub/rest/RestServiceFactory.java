package com.asportsclub.rest;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.asportsclub.R;
import com.asportsclub.rest.services.ApiService;
import com.asportsclub.rest.services.ServiceConstants;
import com.asportsclub.utils.AppConstants;
import com.asportsclub.utils.AppContext;
import com.asportsclub.utils.AppSharedPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class RestServiceFactory {

    public static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit retrofit;

    public static ApiService apiService;
    private static X509TrustManager x509TrustManager;

    private static <S> S createService(Class<S> serviceClass) {
        if (apiService == null) {

            // if (MyuApplication.RETROFIT_SHOW_LOG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
            // }

            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    boolean value = true;
                    return value;
                }
            });

            httpClient.readTimeout(2, TimeUnit.MINUTES);
            httpClient.connectTimeout(2, TimeUnit.MINUTES);
            httpClient.writeTimeout(2, TimeUnit.MINUTES);


            String baseUrl = "";


            baseUrl = AppSharedPreferences.getInstance(AppContext.getInstance().getContext()).getURL();
            //baseUrl = ServiceConstants.BASE_SERVICE_URL;


            Retrofit.Builder builder =
                    new Retrofit.Builder()

                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.client(httpClient.build()).build();
            apiService = (ApiService) retrofit.create(serviceClass);

        }
        return (S) apiService;
    }

    public static Retrofit retrofit() {
        return retrofit;
    }

    public static ApiService createService() {
        return createService(ApiService.class);
    }


}
