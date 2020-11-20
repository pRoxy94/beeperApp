package it.proxy.beeperapp.viewmodel

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.proxy.beeperapp.databinding.FragmentSearchBinding
import it.proxy.beeperapp.repository.PersonRepository
import java.lang.IllegalArgumentException

class PersonViewModelFactory(private val repository: PersonRepository,
                             val binding: FragmentSearchBinding,
                             val activity: Activity,
                             private val lifecycleOwner: LifecycleOwner): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            return PersonViewModel(repository, binding, activity, lifecycleOwner) as T
        }
        throw IllegalArgumentException("Unknown View Model class.")
    }
}