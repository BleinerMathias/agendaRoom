package br.edu.ifsp.aluno.bleinermathias.agendaroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var phone:String,
    var email:String?
)
