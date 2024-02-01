package com.mbtiger.kaamkaro

sealed interface ContactEvent {
    object SaveContact: ContactEvent
    data class SetFirstName(val firstName: String): ContactEvent
    data class SetLastName(val lastName: String): ContactEvent
    data class SetRollNo(val rollNo: String): ContactEvent
    data class SetDep(val dep: String): ContactEvent
    data class SetSection(val section: String): ContactEvent
    data class SetEmail(val email: String): ContactEvent
    data class SetPhoneNumber(val phoneNumber: String): ContactEvent
    data class SetAge(val age: String): ContactEvent
    data class SetCity(val city: String): ContactEvent
    data class SetAddress(val address: String): ContactEvent
    object ShowDialog: ContactEvent
    object HideDialog: ContactEvent
    data class SortContacts(val sortType: SortType): ContactEvent
    data class DeleteContact(val contact: Contact): ContactEvent
}