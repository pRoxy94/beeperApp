package it.proxy.beeperapp.rest

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonRetrofitInstance {

    companion object {
        private val BASE_URL = "https://2054e4ba767c.ngrok.io"

        fun getPersonRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }

}