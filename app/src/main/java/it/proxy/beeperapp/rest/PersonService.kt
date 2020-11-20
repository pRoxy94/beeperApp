package it.proxy.beeperapp.rest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonService {

    @GET(value = "/id={id}")
    suspend fun getPersonById(@Path(value = "id") id : String) : Response<Person>

    @GET(value = "/nome={nome}")
    suspend fun getPersonByName(@Path(value = "nome") nome : String) : Response<Person>

    @GET(value = "/cognome={cognome}")
    suspend fun getPersonBySurname(@Path(value = "cognome") cognome : String) : Response<Person>

    @GET(value = "/telefono={telefono}")
    suspend fun getPersonByPhoneNumber(@Path(value = "telefono") telefono : String) : Response<Person>

}