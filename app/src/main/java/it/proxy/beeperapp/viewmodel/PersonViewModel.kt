package it.proxy.beeperapp.viewmodel


import android.view.View
import androidx.lifecycle.*
import it.proxy.beeperapp.adapter.PersonRecyclerViewAdapter
import it.proxy.beeperapp.databinding.FragmentSearchBinding
import it.proxy.beeperapp.repository.PersonRepository
import it.proxy.beeperapp.rest.PersonItem
import kotlinx.coroutines.*

class PersonViewModel(
        private val repository: PersonRepository,
        val binding: FragmentSearchBinding,
        private val lifecycleOwner: LifecycleOwner): ViewModel() {

    var inputType = MutableLiveData<String>()
    var input = MutableLiveData<String>()
    var list = mutableListOf<PersonItem>()

    fun search() {
        when(inputType.value) {
            "Nome" -> {
                input.observe(lifecycleOwner, Observer {
                    viewModelScope.launch {
                        list.clear()
                        repository.getPersonByName(it, lifecycleOwner)
                        if (repository.haveResult) list = repository.resultList
                    }
                    binding.apply {
                        if (!repository.haveResult) {
                            txtNoResults.visibility = View.VISIBLE
                        } else {
                            txtNoResults.visibility = View.GONE
                            recyclerPerson.adapter = PersonRecyclerViewAdapter(list)
                            recyclerPerson.adapter!!.notifyDataSetChanged()
                        }
                    }
                })
            }
            "Cognome" -> {
                input.observe(lifecycleOwner, Observer {
                    viewModelScope.launch {
                        list.clear()
                        repository.getPersonBySurname(it, lifecycleOwner)
                        if (repository.haveResult) list = repository.resultList
                    }
                    binding.apply {
                        if (!repository.haveResult) {
                            txtNoResults.visibility = View.VISIBLE
                        } else {
                            txtNoResults.visibility = View.GONE
                            recyclerPerson.adapter = PersonRecyclerViewAdapter(list)
                            recyclerPerson.adapter!!.notifyDataSetChanged()
                        }
                    }
                })
            }
            "Telefono" -> {
                input.observe(lifecycleOwner, Observer {
                    viewModelScope.launch {
                        list.clear()
                        repository.getPersonByPhoneNumber(it, lifecycleOwner)
                        if (repository.haveResult) list = repository.resultList
                    }
                    binding.apply {
                        if (!repository.haveResult) {
                            txtNoResults.visibility = View.VISIBLE
                        } else {
                            txtNoResults.visibility = View.GONE
                            recyclerPerson.adapter = PersonRecyclerViewAdapter(list)
                            recyclerPerson.adapter!!.notifyDataSetChanged()
                        }
                    }
                })
            }
            else ->  {
                input.observe(lifecycleOwner, Observer {
                    viewModelScope.launch {
                        list.clear()
                        repository.getPersonByName(it, lifecycleOwner)
                        if (repository.haveResult) list = repository.resultList
                    }
                    binding.apply {
                        if (!repository.haveResult) {
                            txtNoResults.visibility = View.VISIBLE
                        } else {
                            txtNoResults.visibility = View.GONE
                            recyclerPerson.adapter = PersonRecyclerViewAdapter(list)
                            recyclerPerson.adapter!!.notifyDataSetChanged()
                        }
                    }
                })
            }
        }
    }
}