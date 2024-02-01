package com.mbtiger.kaamkaro

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
//first name
    @Query("SELECT * FROM contact ORDER BY firstName ASC")
    fun getContactsOrderedByFirstName(): Flow<List<Contact>>
//last name
    @Query("SELECT * FROM contact ORDER BY lastName ASC")
    fun getContactsOrderedByLastName(): Flow<List<Contact>>
    //roll no
    @Query("SELECT * FROM contact ORDER BY rollNo ASC")
    fun getContactsOrderedByRollNo(): Flow<List<Contact>>
    //Dep
    @Query("SELECT * FROM contact ORDER BY dep ASC")
    fun getContactsOrderedByDep(): Flow<List<Contact>>
    //section
    @Query("SELECT * FROM contact ORDER BY section ASC")
    fun getContactsOrderedBySection(): Flow<List<Contact>>
    //email
    @Query("SELECT * FROM contact ORDER BY email ASC")
    fun getContactsOrderedByEmail(): Flow<List<Contact>>
//phone Number
    @Query("SELECT * FROM contact ORDER BY phoneNumber ASC")
    fun getContactsOrderedByPhoneNumber(): Flow<List<Contact>>
    //Age
    @Query("SELECT * FROM contact ORDER BY age ASC")
    fun getContactsOrderedByAge(): Flow<List<Contact>>
    //City
    @Query("SELECT * FROM contact ORDER BY city ASC")
    fun getContactsOrderedByCity(): Flow<List<Contact>>
    //Address
    @Query("SELECT * FROM contact ORDER BY address ASC")
    fun getContactsOrderedByAddress(): Flow<List<Contact>>
}