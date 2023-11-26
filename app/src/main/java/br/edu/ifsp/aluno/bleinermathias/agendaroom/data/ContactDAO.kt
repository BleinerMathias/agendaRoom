package br.edu.ifsp.aluno.bleinermathias.agendaroom.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDAO {
    @Insert
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY name")
    fun getAllContacts():LiveData<List<Contact>>

    @Query("SELECT * FROM contact WHERE id = :id")
    fun getContactById(id:Int):LiveData<Contact>
}