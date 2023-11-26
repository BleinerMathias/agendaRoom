package br.edu.ifsp.aluno.bleinermathias.agendaroom.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.Contact
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.ContactDAO
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.ContactDatabase
import br.edu.ifsp.aluno.bleinermathias.agendaroom.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application):AndroidViewModel(application) {
    private val repository:ContactRepository
    var allContacts: LiveData<List<Contact>>
    lateinit var contact:LiveData<Contact>

    init{
        val dao = ContactDatabase.getDatabase(application).contatoDAO()
        repository = ContactRepository(dao)
        allContacts = repository.getAllContact()
    }

    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(contact)
    }

}