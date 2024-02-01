package com.mbtiger.kaamkaro

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable

fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    LocalContext.current
//    var context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ContactEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact"
                )
            }
        },
    ) {  _ ->
        if(state.isAddingContact) {
            AddContactDialog(state = state, onEvent = onEvent)
        }
//padding value
        LazyColumn(
            contentPadding = PaddingValues(20.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                //Horizontal verticalAlignment
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //Sort with Enum class
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(ContactEvent.SortContacts(sortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {

                            //RadioButton
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(ContactEvent.SortContacts(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.contacts) { contact ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${contact.firstName} ${contact.lastName} ${contact.rollNo}",
                            fontSize = 20.sp
                        )
//FontSize
                        Text(text = contact.dep, fontSize = 12.sp)
                        Text(text = contact.section, fontSize = 12.sp)
                        Text(text = contact.email, fontSize = 12.sp)
                        Text(text = contact.phoneNumber, fontSize = 12.sp)
                        Text(text = contact.age, fontSize = 12.sp)
                        Text(text = contact.city, fontSize = 12.sp)
                        Text(text = contact.address, fontSize = 12.sp)

                    }
                    IconButton(onClick = {
                        onEvent(ContactEvent.DeleteContact(contact))
                    }) {

                        //Default icon
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete contact"

                        )

                    }
                }
            }
        }
    }
}