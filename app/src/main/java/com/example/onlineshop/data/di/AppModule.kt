package com.example.onlineshop.data.di

import com.example.onlineshop.data.api.MockyApi
import com.example.onlineshop.data.repository.ProductRepositoryImpl
import com.example.onlineshop.domain.repository.ProductRepository
import com.example.onlineshop.other.Constant.BASE_URL
import com.example.onlineshop.other.Constant.DEFAULT_WEB_CLIENT_ID
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): MockyApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(MockyApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: MockyApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }
}