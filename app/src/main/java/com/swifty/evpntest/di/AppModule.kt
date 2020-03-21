package com.swifty.evpntest.di

import android.content.Context
import com.swifty.evpntest.Model
import com.swifty.evpntest.repository.ApiRepository
import com.swifty.evpntest.repository.RetrofitApiRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

  val endPoint = "http://5e7335fbbe8c5400165c35c3.mockapi.io"

  @Provides
  @Singleton
  fun retrofit(): Retrofit {
    val builder = Retrofit.Builder()
    builder
      .baseUrl(endPoint)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    return builder.build()
  }

  @Provides
  @Singleton
  fun apiRepository(retrofit: Retrofit, defaultListResponse: List<Model>): ApiRepository {
    return RetrofitApiRepository(retrofit, defaultListResponse)
  }

  @Provides
  fun defaultResponse(): List<Model> {
    return emptyList()
  }
}