package it.proxy.beeperapp.rest

import com.google.gson.annotations.SerializedName

data class PersonItem(
    @SerializedName("cognome")
    val cognome: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("telefono")
    val telefono: String
)