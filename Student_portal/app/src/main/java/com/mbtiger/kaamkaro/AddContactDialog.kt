package com.mbtiger.kaamkaro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// Create a Add contact Dialog
@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    //Alert Dialog
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        },

//Add Contact with field set
        title = { Text(text = "Add contact") },
        text = {
            // Data can be show a Column with space arrangement
            Column(
                verticalArrangement = Arrangement.spacedBy(9.dp)
            ) {

                //first name text field ,placeholder and add icon
                TextField(
                    value = state.firstName,
                    onValueChange = {
                        onEvent(ContactEvent.SetFirstName(it))
                    },
                    placeholder = {
                        Text(text = "First Name")

                    },
                    leadingIcon = { Icon(Icons.Default.Person , contentDescription = null  )},
                )

                //end first name textField

                //last name text field ,placeholder and add icon
                TextField(
                    value = state.lastName,
                    onValueChange = {
                        onEvent(ContactEvent.SetLastName(it))
                    },
                    placeholder = {
                        Text(text = "Last Name")
                    },
                    leadingIcon = { Icon(Icons.Default.Person , contentDescription = null  )},
                )
                //end last name textField
                // Roll No text field ,placeholder and add icon
                TextField(
                    value = state.rollNo,
                    onValueChange = {
                        onEvent(ContactEvent.SetRollNo(it))
                    },
                    placeholder = {
                        Text(text = "Roll No")
                    },
                    leadingIcon = { Icon(Icons.Default.Lock , contentDescription = null  )},
                )
                // Dep text field ,placeholder and add icon
                TextField(
                    value = state.dep,
                    onValueChange = {
                        onEvent(ContactEvent.SetDep(it))
                    },
                    placeholder = {
                        Text(text = "Department")
                    },
                    leadingIcon = { Icon(Icons.Default.Home , contentDescription = null  )},
                )
                //end Dep textField

                // Section text field ,placeholder and add icon
                TextField(
                        value = state.section,
                onValueChange = {
                    onEvent(ContactEvent.SetSection(it))
                },
                placeholder = {
                    Text(text = "Section")
                },
                    leadingIcon = { Icon(Icons.Default.Menu , contentDescription = null  )},
                )
                //end Section textField

                // Email text field ,placeholder and add icon
                TextField(
                    value = state.email,
                    onValueChange = {
                        onEvent(ContactEvent.SetEmail(it))
                    },
                    placeholder = {
                        Text(text = "Email")
                    },
                    leadingIcon = { Icon(Icons.Default.Email , contentDescription = null  )},
                )
                //end Email textField

                // Phone Number text field ,placeholder and add icon
                TextField(
                    value = state.phoneNumber,
                    onValueChange = {
                        onEvent(ContactEvent.SetPhoneNumber(it))
                    },
                    placeholder = {
                        Text(text = "Phone Number")
                    },
                    leadingIcon = { Icon(Icons.Default.Phone , contentDescription = null  )},
                )
                //end Phone Number textField
                // Age text field ,placeholder and add icon
                TextField(
                    value = state.age,
                    onValueChange = {
                        onEvent(ContactEvent.SetAge(it))
                    },
                    placeholder = {
                        Text(text = "Age")
                    },
                    leadingIcon = { Icon(Icons.Default.DateRange , contentDescription = null  )},
                )
                //end Age textField

                // City text field ,placeholder and add icon
                TextField(
                    value = state.city,
                    onValueChange = {
                        onEvent(ContactEvent.SetCity(it))
                    },
                    placeholder = {
                        Text(text = "City")
                    },
                    leadingIcon = { Icon(Icons.Default.Place , contentDescription = null  )},

                )
                //end City textField

                // Address text field ,placeholder and add icon

                TextField(
                    value = state.address,
                    onValueChange = {
                        onEvent(ContactEvent.SetAddress(it))
                    },
                    placeholder = {
                        Text(text = "Address")
                    },
                    trailingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                )
                //end Address textField

            }
        },
        // Create a Button All input : Save

        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(ContactEvent.SaveContact)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}
// Data Saving