package android.rezkyauliapratama.com.qlue_project.di.application

import android.rezkyauliapratama.com.qlue_project.BuildConfig
import android.rezkyauliapratama.com.qlue_project.data.network.NetworkApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by Rezky Aulia Pratama on 11/9/18.
 */
@Module
class NetworkModule{

    @Provides
    fun providesOkHttpClient() : OkHttpClient{
        val httpClient : OkHttpClient.Builder = OkHttpClient.Builder();
        httpClient.addInterceptor {
            chain ->

            val original : Request = chain.request();

            val request = original.newBuilder()
                    .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoicWx1ZWluIiwiaWF0IjoxNDk0Mzk5Njg1fQ.mG5wmoCwmchufTPloxI7AjZaeM_bwcpCEJpyUnCDrmk")
                    .method(original.method(), original.body())
                    .build();

            chain.proceed(request);
        }
     return httpClient.build()
    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build()

    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    // This function need Retrofit object which we are passing in argument.
    // We will not create Retrofit object in this function.
    // Instead it will be injected/provided by Dagger2.
    // Dagger2 will get Retrofit object from provideRetrofit function declared above.

    @Provides
    fun provideNetworkApi(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }
}