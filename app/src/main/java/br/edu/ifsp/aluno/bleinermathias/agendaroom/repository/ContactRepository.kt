package br.edu.ifsp.aluno.bleinermathias.agendaroom.repository

import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.Contact
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.ContactDAO

class ContactRepository(private val contactDAO: ContactDAO) {

    suspend fun insert(contact: Contact){
        contactDAO.insert(contact)
    }

}