package it.proxy.beeperapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.proxy.beeperapp.adapter.PersonRecyclerViewAdapter
import it.proxy.beeperapp.databinding.FragmentSearchBinding
import it.proxy.beeperapp.repository.PersonRepository
import it.proxy.beeperapp.viewmodel.PersonViewModel
import it.proxy.beeperapp.viewmodel.PersonViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: PersonViewModel
    private lateinit var personViewModelFactory: PersonViewModelFactory
    private lateinit var personRepository: PersonRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        personRepository = PersonRepository()
        personViewModelFactory = PersonViewModelFactory(personRepository, binding, requireActivity(), this)
        viewModel = ViewModelProvider(requireActivity(), personViewModelFactory).get(PersonViewModel::class.java)
        binding.personViewModel = viewModel
        binding.recyclerPerson.layoutManager = LinearLayoutManager(activity)
        binding.recyclerPerson.adapter = PersonRecyclerViewAdapter(viewModel.list)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
                when {
                    btnName.id == checkedId -> {
                        personViewModel!!.inputType.value = btnName.text.toString()
                    }
                    btnSurname.id == checkedId -> {
                        personViewModel!!.inputType.value = btnSurname.text.toString()
                    }
                    btnPhoneNumber.id == checkedId -> {
                        personViewModel!!.inputType.value = btnPhoneNumber.text.toString()
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment().apply {}
    }
}