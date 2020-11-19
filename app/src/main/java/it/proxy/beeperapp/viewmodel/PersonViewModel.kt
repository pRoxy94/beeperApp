package it.proxy.beeperapp.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.*
import it.proxy.beeperapp.repository.PersonRepository
import it.proxy.beeperapp.rest.Person
import it.proxy.beeperapp.rest.PersonItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PersonViewModel(private val repository: PersonRepository, private val lifecycleOwner: LifecycleOwner): ViewModel() {

    @Bindable
    val inputType = MutableLiveData<String>()

    @Bindable
    val input = MutableLiveData<String>()

    suspend fun search() {
        var aList = viewModelScope.async(Dispatchers.IO) {
            var list = mutableListOf<PersonItem>()
            when(inputType.value) {
                "Nome" -> list = repository.getPersonByName(input.value!!, lifecycleOwner)
                "Cognome" -> list = repository.getPersonBySurname(input.value!!, lifecycleOwner)
                "Telefono" -> list = repository.getPersonByPhoneNumber(input.value!!, lifecycleOwner)
            }
            return@async list
        }
//        return aList
    }
}