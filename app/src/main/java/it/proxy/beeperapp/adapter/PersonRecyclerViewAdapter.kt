package it.proxy.beeperapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.proxy.beeperapp.databinding.ListItemBinding
import it.proxy.beeperapp.rest.PersonItem

class PersonRecyclerViewAdapter(private val listPerson: List<PersonItem>): RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder>() {
    private lateinit var binding: ListItemBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ListItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonRecyclerViewAdapter.ViewHolder, position: Int) {
        val personItem: PersonItem = listPerson.get(position)
        holder.nameTextView.text = personItem.nome
        holder.surnameTextView.text = personItem.cognome
        holder.phoneNumberTextView.text = personItem.telefono
    }

    override fun getItemCount(): Int = listPerson.size


    inner class ViewHolder(binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.txtName
        val surnameTextView = binding.txtSurname
        val phoneNumberTextView = binding.txtPhoneNumber
    }
}