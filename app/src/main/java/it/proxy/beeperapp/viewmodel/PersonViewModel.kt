package it.proxy.beeperapp.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.*
import it.proxy.beeperapp.adapter.PersonRecyclerViewAdapter
import it.proxy.beeperapp.repository.PersonRepository
import it.proxy.beeperapp.rest.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class PersonViewModel(private val repository: PersonRepository, private val lifecycleOwner: LifecycleOwner): ViewModel() {

    @Bindable
    var inputType = MutableLiveData<String>()

    @Bindable
    var input = MutableLiveData<String>()

    @Bindable
    var recyclerViewAdapter = MutableLiveData<PersonRecyclerViewAdapter>()

    suspend fun search() {
        val list = viewModelScope.async(Dispatchers.IO) {
            when(inputType.value) {
                "Nome" -> repository.getPersonByName(input.value!!, lifecycleOwner)
                "Cognome" -> repository.getPersonBySurname(input.value!!, lifecycleOwner)
                "Telefono" -> repository.getPersonByPhoneNumber(input.value!!, lifecycleOwner)
                else -> repository.getPersonByName(input.value!!, lifecycleOwner)
            }
        }
        recyclerViewAdapter.value = PersonRecyclerViewAdapter(list.await())
    }
}