package com.swifty.evpntest.repository

import com.swifty.evpntest.Model
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

/**
 * Created by Swifty Wang on 21/3/2020.
 */
internal class RetrofitApiRepositoryTest {

  private var mockWebServer = MockWebServer()
  private lateinit var mockRetrofit: Retrofit
  @Before
  fun setUp() {
    mockWebServer.start()
    mockRetrofit = Retrofit.Builder()
      .baseUrl(mockWebServer.url("/"))
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
  }


  @After
  fun teardown() {
    mockWebServer.shutdown()
  }

  @Test
  fun getListNotFound_case1() {
    // given
    val response = MockResponse()
      .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
    mockWebServer.enqueue(response)

    val apiRepository = RetrofitApiRepository(mockRetrofit, emptyList())

    // when
    val model = apiRepository.getList().blockingFirst()

    // then
    assertEquals(emptyList<Model>(), model)
  }

  @Test
  fun getListNotFound_case2() {
    // given
    val response = MockResponse()
      .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
    mockWebServer.enqueue(response)
    val dummyList = listOf(Model("0", "Swifty"), Model("1", "Tony"))

    val apiRepository = RetrofitApiRepository(mockRetrofit, dummyList)

    // when
    val model = apiRepository.getList().blockingFirst()

    // then
    assertEquals(dummyList, model)
  }
}