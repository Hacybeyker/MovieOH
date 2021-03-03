package com.hacybeyker.repository.network.di;

import android.content.Context
import com.hacybeyker.repository.network.services.MovieServices
import com.hacybeyker.repository.network.utils.API_KEY_QUERY
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "BASE_URL"
const val API_KEY = "API_KEY"

val retrofitModule = module {
    single { providerHttpLoggingInterceptor() }
    single { providerGsonConverterFactory() }
    single { ApiInterceptor(getProperty(API_KEY)) }
    single { providerOkHttpClient(get(), get(), get()) }
    single { providerRetrofit(getProperty(BASE_URL), get(), get()) }
    single { providerMovieServices(get()) }
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
}

fun providerGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

fun providerOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiInterceptor: ApiInterceptor,
    context: Context
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(ChuckInterceptor(context))
        .addInterceptor(apiInterceptor)
        .build()
}

fun providerRetrofit(
    baseUrl: String,
    client: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun providerMovieServices(retrofit: Retrofit): MovieServices {
    return retrofit.create(MovieServices::class.java)
}

class ApiInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(API_KEY_QUERY, apiKey).build()
        request = Request.Builder().url(url).build()
        return chain.proceed(request)
    }
}