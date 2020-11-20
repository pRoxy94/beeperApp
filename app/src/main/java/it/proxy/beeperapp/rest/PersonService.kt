package it.proxy.beeperapp.rest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonService {

    @GET(value = "id={id}")
    suspend fun getPersonById(@Query(value = "id") id : String) : Response<Person>

    @GET(value = "nome={nome}")
    suspend fun getPersonByName(@Query(value = "nome") nome : String) : Response<Person>

    @GET(value = "cognome={cognome}")
    suspend fun getPersonBySurname(@Query(value = "cognome") cognome : String) : Response<Person>

    @GET(value = "telefono={telefono}")
    suspend fun getPersonByPhoneNumber(@Query(value = "telefono") telefono : String) : Response<Person>

}