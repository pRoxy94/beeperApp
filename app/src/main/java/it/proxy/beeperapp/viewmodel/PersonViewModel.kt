package it.proxy.beeperapp.viewmodel


import androidx.lifecycle.*
import it.proxy.beeperapp.repository.PersonRepository
import it.proxy.beeperapp.rest.PersonItem
import kotlinx.coroutines.*

class PersonViewModel(
        private val repository: PersonRepository,
        private val lifecycleOwner: LifecycleOwner): ViewModel() {

    var inputType = MutableLiveData<String>()
    var input = MutableLiveData<String>()
    var list = listOf(
            PersonItem("Laricchia", "1", "Rossana", "0804559184"),
            PersonItem("Laricchia", "2", "Rossana", "0804559184")
    )

    fun search() {
        when(inputType.value) {
            "Nome" -> {
                input.observe(lifecycleOwner, Observer {
                    viewModelScope.launch {
                        list = repository.getPersonByName(it, lifecycleOwner)
                    }
                })
            }
            "Cognome" -> {
                input.observe(lifecycleOwner, Observer {
                    viewModelScope.launch {
                        list = repository.getPersonBySurname(it, lifecycleOwner)
                    }
                })
            }
            "Telefono" -> {
                input.observe(lifecycleOwner, Observer {
                    viewModelScope.launch {
                        list = repository.getPersonByPhoneNumber(it, lifecycleOwner)
                    }
                })
            }
            else ->  {
                input.observe(lifecycleOwner, Observer {
                    viewModelScope.launch {
                        list = repository.getPersonByName(it, lifecycleOwner)
                    }
                })
            }
        }
    }
}