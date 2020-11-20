package it.proxy.beeperapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.button.MaterialButtonToggleGroup
import it.proxy.beeperapp.adapter.PersonRecyclerViewAdapter
import it.proxy.beeperapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            recyclerPerson.adapter = personViewModel.recyclerViewAdapter.value

            toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
                when {
                    btnName.id == checkedId -> personViewModel.inputType.value = btnName.text.toString()
                    btnSurname.id == checkedId -> personViewModel.inputType.value = btnSurname.text.toString()
                    btnPhoneNumber.id == checkedId -> personViewModel.inputType.value = btnPhoneNumber.text.toString()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {}
    }
}