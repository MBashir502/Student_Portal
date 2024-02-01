package com.mbtiger.kaamkaro

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val rollNo: String = "",
    val dep: String = "",
    val section: String = "",
    val age: String = "",
    val email: String = "",
    val city: String = "",
    val address: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)
