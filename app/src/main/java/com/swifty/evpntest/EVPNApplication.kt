package com.swifty.evpntest

import android.app.Application
import com.swifty.evpntest.di.AppComponent
import com.swifty.evpntest.di.AppModule
import com.swifty.evpntest.di.DaggerAppComponent

class EVPNApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}