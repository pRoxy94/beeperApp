package it.proxy.beeperapp.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.proxy.beeperapp.repository.PersonRepository
import java.lang.IllegalArgumentException

class PersonViewModelFactory(private val repository: PersonRepository,
                             private val lifecycleOwner: LifecycleOwner): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            return PersonViewModel(repository, lifecycleOwner) as T
        }
        throw IllegalArgumentException("Unknown View Model class.")
    }
}