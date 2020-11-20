package it.proxy.beeperapp.rest

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonRetrofitInstance {

    companion object {
        private val BASE_URL = "http://718c9f33c19d.ngrok.io"

        fun getPersonRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }

}