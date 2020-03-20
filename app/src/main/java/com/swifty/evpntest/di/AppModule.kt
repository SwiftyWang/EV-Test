package com.swifty.evpntest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

  @Provides
  @Singleton
  fun retrofit(): Retrofit {
    return Retrofit.Builder().build()
  }
}