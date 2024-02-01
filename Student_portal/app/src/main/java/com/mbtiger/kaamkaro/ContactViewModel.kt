package com.mbtiger.kaamkaro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModel(
    private val dao: ContactDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.FIRST_NAME -> dao.getContactsOrderedByFirstName()
                SortType.LAST_NAME -> dao.getContactsOrderedByLastName()
                SortType.ROLL_NO -> dao.getContactsOrderedByLastName()
                SortType.DEP -> dao.getContactsOrderedByDep()
                SortType.SECTION -> dao.getContactsOrderedBySection()
                SortType.EMAIL -> dao.getContactsOrderedByEmail()
                SortType.PHONE_NUMBER -> dao.getContactsOrderedByPhoneNumber()
                SortType.AGE -> dao.getContactsOrderedByAge()
                SortType.CITY -> dao.getContactsOrderedByCity()
                SortType.ADDRESS -> dao.getContactsOrderedByAddress()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ContactState())
    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent(event: ContactEvent) {
        when(event) {
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }
            ContactEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingContact = false
                ) }
            }
            ContactEvent.SaveContact -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val rollNo = state.value.rollNo
                val dep = state.value.dep
                val section = state.value.section
                val email = state.value.email
                val phoneNumber = state.value.phoneNumber
                val age = state.value.age
                val city = state.value.city
                val address = state.value.address

                if(firstName.isBlank() ||
                    lastName.isBlank() ||
                    rollNo.isBlank() ||
                    dep.isBlank() ||
                    section.isBlank() ||
                    email.isBlank() ||
                    phoneNumber.isBlank() ||
                    age.isBlank() ||
                    city.isBlank() ||
                    address.isBlank()) {
                    return
                }

                val contact = Contact(
                    firstName = firstName,
                    lastName = lastName,
                    rollNo = rollNo,
                    dep = dep,
                    section= section,
                    email= email,
                    phoneNumber = phoneNumber,
                    age = age,
                    city = city,
                    address = address
                )
                viewModelScope.launch {
                    dao.upsertContact(contact)
                }
                _state.update { it.copy(
                    isAddingContact = false,
                    firstName = "",
                    lastName = "",
                    rollNo = "",
                    dep = "",
                    section = "",
                    email = "",
                    phoneNumber = "",
                    age = "",
                    city = "",
                    address  = ""
                ) }
            }
            is ContactEvent.SetFirstName -> {
                _state.update { it.copy(
                    firstName = event.firstName
                ) }
            }
            is ContactEvent.SetLastName -> {
                _state.update { it.copy(
                    lastName = event.lastName
                ) }
            }
            is ContactEvent.SetRollNo -> {
                _state.update { it.copy(
                    rollNo = event.rollNo
                ) }
            }
            is ContactEvent.SetDep -> {
                _state.update { it.copy(
                    dep = event.dep
                ) }
            }
            is ContactEvent.SetSection -> {
                _state.update { it.copy(
                    section = event.section
                ) }
            }
            is ContactEvent.SetEmail -> {
                _state.update { it.copy(
                    email = event.email
                ) }
            }
            is ContactEvent.SetPhoneNumber -> {
                _state.update { it.copy(
                    phoneNumber = event.phoneNumber
                ) }
            }
            is ContactEvent.SetAge -> {
                _state.update { it.copy(
                    age = event.age
                ) }
            }
            is ContactEvent.SetCity -> {
                _state.update { it.copy(
                    city = event.city
                ) }
            }
            is ContactEvent.SetAddress -> {
                _state.update { it.copy(
                    address = event.address
                ) }
            }
            ContactEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingContact = true
                ) }
            }
            is ContactEvent.SortContacts -> {
                _sortType.value = event.sortType
            }

        }
    }
}