package com.swifty.evpntest.repository

import com.swifty.evpntest.Model
import io.reactivex.Observable
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.http.GET
import java.net.HttpURLConnection

/**
 * Created by Swifty Wang on 21/3/2020.
 * This defaultListResponse is only for this test to keep the demo simple, normally should pass a CacheStore
 * into this class and get cacheData from it
 */
class RetrofitApiRepository(private val retrofit: Retrofit, private val defaultListResponse: List<Model>) : ApiRepository {

  override fun getList(): Observable<List<Model>> {
    return retrofit.create(Service::class.java).list()
      .onErrorReturn {
        if ((it as? HttpException)?.code() == HttpURLConnection.HTTP_NOT_FOUND) {
          // it 404 happened just return defaultResponse
          defaultListResponse
        } else {
          throw  it
        }
      }
  }

  interface Service {

    @GET("/list")
    fun list(): Observable<List<Model>>
  }
}