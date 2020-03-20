package com.swifty.evpntest.di

import android.app.Application
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
  fun inject(application: Application)
}