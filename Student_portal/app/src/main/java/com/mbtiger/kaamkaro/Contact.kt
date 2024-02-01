package com.mbtiger.kaamkaro

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val firstName: String,
    val lastName: String,
    val rollNo: String,
    val dep :String,
    val section :String,
    val email: String,
    val phoneNumber: String,
    val age: String,
    val city: String,
    val address: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
