package br.edu.ifsp.aluno.bleinermathias.agendaroom.adapter

import android.location.GnssAntennaInfo.Listener
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.Contact
import br.edu.ifsp.aluno.bleinermathias.agendaroom.databinding.TileContactBinding

class ContactAdapter():RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(), Filterable {

    private lateinit var tileContactBinding: TileContactBinding
    var contactList = ArrayList<Contact>()
    var contactListFilterable = ArrayList<Contact>()
    var listener:ContactListener?=null



    interface ContactListener
    {
        fun onItemClick(pos: Int)
    }

    fun setClickListener(listener: ContactListener) {
        this.listener = listener
    }

    fun updateList(newList: ArrayList<Contact> ){
        contactList = newList
        contactListFilterable = contactList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        tileContactBinding = TileContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(tileContactBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.apply {
            nameViewHolder.text = contactListFilterable[position].name
            phoneViewHolder.text = contactListFilterable[position].phone
        }
    }

    override fun getItemCount(): Int {
        return contactListFilterable.size
    }

    inner class ContactViewHolder(view:TileContactBinding): RecyclerView.ViewHolder(view.root)
    {
        val nameViewHolder = view.nome
        val phoneViewHolder = view.fone
        init {
            view.root.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }

    }

    override fun getFilter(): Filter {
        return object : Filter(){

            override fun performFiltering(searchText: CharSequence?): FilterResults {

                if (searchText.toString().isEmpty()) {
                    contactListFilterable = contactList
                } else{
                    val resultList = ArrayList<Contact>()
                    for (row in contactList) {
                        if (row.name.lowercase().contains(searchText.toString().lowercase())) {
                            resultList.add(row)
                        }
                    }
                    contactListFilterable = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = contactListFilterable
                return filterResults

            }

            override fun publishResults(searchText: CharSequence?, filteredResult: FilterResults?) {
                contactListFilterable = filteredResult?.values as ArrayList<Contact>
                notifyDataSetChanged()
            }

        }
    }

}