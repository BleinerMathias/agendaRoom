package br.edu.ifsp.aluno.bleinermathias.agendaroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.Contact
import br.edu.ifsp.aluno.bleinermathias.agendaroom.databinding.TileContactBinding

class ContactAdapter():RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private lateinit var tileContactBinding: TileContactBinding
    var contactList = ArrayList<Contact>()

    fun updatelist(newList: ArrayList<Contact>){
        contactList = newList
    }

    fun updateList(newList: ArrayList<Contact> ){
        contactList = newList
        var contatosListaFilterable = contactList
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
        holder.nameViewHolder.text = contactList[position].name
        holder.phoneViewHolder.text = contactList[position].phone
    }
    override fun getItemCount(): Int {
        return contactList.size
    }
    inner class ContactViewHolder(view:TileContactBinding): RecyclerView.ViewHolder(view.root)
    {
        val nameViewHolder = view.nome
        val phoneViewHolder = view.fone
    }


}