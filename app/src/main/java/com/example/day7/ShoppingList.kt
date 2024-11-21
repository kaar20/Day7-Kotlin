package com.example.day7

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    val id:Int,
    var name:String,
    var quantity:Int,
    var isEditing:Boolean=false

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ShoppingListApp(){
    var sItems by remember{ mutableStateOf(listOf<ShoppingItem>()) }
    var showDiaglog by remember {  mutableStateOf(false)}
    var itemName by remember {
        mutableStateOf("")
    }
    var itemQuantity by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {showDiaglog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems){
                ShoppingListItem(item = it, onEditingClick = { /*TODO*/ }) {
                    
                }

            }
            



        }

    }
   if(showDiaglog)

   {
       AlertDialog(onDismissRequest = { showDiaglog = false },
           confirmButton = {
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(
                                       8.dp
                                   ),
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Button(onClick = { showDiaglog=false }) {
                                   Text(text = "Cancel")
                               }

                               Button(onClick = { var item = ShoppingItem(
                                   id = sItems.size+1,
                                   name= itemName,
                                   quantity = itemQuantity.toInt(),

                                   )
                                   sItems = sItems+item
                                   showDiaglog=false
                                   itemName=""
                                   itemQuantity="" }) {
                                   Text("Add")

                               }


                           }
           },
           title = {Text(text = "Add Shopping Item")},
           text = {
               Column {
                   OutlinedTextField(value = itemName, onValueChange = {
                       itemName = it
                   },

                       singleLine = true,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp)
                       )

                   OutlinedTextField(value = itemQuantity, onValueChange = {
                       itemQuantity = it
                   },

                       singleLine = true,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp)
                   )
               }
           }


//           title = {}
//           Text(text = "Mohamed You Are Doing Great")

       )}
}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditingClick:()->Unit,
    onDeleteClick:()->Unit
){
    Row (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color(0xff018786)),
                shape = RoundedCornerShape(20)
            )
    ){
        Text(text = item.name, modifier = Modifier.padding(8.dp))

    }
}
