package br.edu.ifsp.aluno.bleinermathias.agendaroom.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ContactDAO {
    @Insert
    suspend fun insert(contact: Contact)
}