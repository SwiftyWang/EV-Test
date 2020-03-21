package com.swifty.evpntest.repository

import com.swifty.evpntest.Model
import io.reactivex.Observable

/**
 * Created by Swifty Wang on 21/3/2020.
 */
interface ApiRepository {
  fun getList(): Observable<List<Model>>
}