package com.csc.cscany.modules;

import androidx.lifecycle.ViewModelProvider;

import com.csc.cscany.classes.AppDatabase;
import com.csc.cscany.classes.Repository;
import com.csc.cscany.network.ApiCallInterface;
import com.csc.cscany.network.interceptor.NormalApiInterceptor;
import com.csc.cscany.view_models.ViewModelFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class UtilsModules {

    @Singleton
    @Provides
    Gson provideGSON() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Singleton
    @Provides
    OkHttpClient provideRequestHeader(NormalApiInterceptor normalApiInterceptor) {
        SSLContext sslContext = null;
        TrustManager[] trustAllCerts = null;
        try {
            // Create a trust manager that does not validate certificate chains
            trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (Exception e) {

        }
        // Create an ssl socket factory with our all-trusting manager
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(normalApiInterceptor)
                .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return builder.build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl("https://www.fildum.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiCallInterface provideApiCallInterface(Retrofit retrofit) {
        return retrofit.create(ApiCallInterface.class);
    }


    @Provides
    @Singleton
    Repository getRepository(ApiCallInterface apiCallInterface) {
        return new Repository(apiCallInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(Repository myRepository, AppDatabase appDatabase) {
        return new ViewModelFactory(myRepository,appDatabase);
    }
}
