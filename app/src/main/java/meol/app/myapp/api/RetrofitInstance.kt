package meol.app.myapp.api

import meol.app.myapp.APP_ID
import meol.app.myapp.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val addQuery = original.url().newBuilder().addQueryParameter("appid", APP_ID).build()
        val requestBuilder: Request.Builder = original.newBuilder().url(addQuery)
        return chain.proceed(requestBuilder.build())
    }
}

class RetrofitInstance {
    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(HeaderInterceptor()).addInterceptor(logging)
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
    }

    val api:ApiInterface by lazy{
        retrofit.create(ApiInterface::class.java)
    }
}