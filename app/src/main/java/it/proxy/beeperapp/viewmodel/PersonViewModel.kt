package it.proxy.beeperapp.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.*
import it.proxy.beeperapp.repository.PersonRepository
import it.proxy.beeperapp.rest.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class PersonViewModel(private val repository: PersonRepository, private val lifecycleOwner: LifecycleOwner): ViewModel() {

    @Bindable
    val inputType = MutableLiveData<String>()

    @Bindable
    val input = MutableLiveData<String>()

    suspend fun search(): Person {
        val list = viewModelScope.async(Dispatchers.IO) {
            when(inputType.value) {
                "Nome" -> repository.getPersonByName(input.value!!, lifecycleOwner)
                "Cognome" -> repository.getPersonBySurname(input.value!!, lifecycleOwner)
                "Telefono" -> repository.getPersonByPhoneNumber(input.value!!, lifecycleOwner)
                else -> repository.getPersonByName(input.value!!, lifecycleOwner)
            }
        }
        return list.await()
    }
}