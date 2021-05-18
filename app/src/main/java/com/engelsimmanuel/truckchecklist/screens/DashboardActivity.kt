package com.engelsimmanuel.truckchecklist.screens

import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.engelsimmanuel.truckchecklist.R
import com.engelsimmanuel.truckchecklist.ui.theme.TruckChecklistTheme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChecklistScreen()
        }
    }
}

@Composable
fun ChecklistScreen() {
    val scaffoldState = rememberScaffoldState()

    var registrationNumberState by remember {
        mutableStateOf("")
    }
    var selectTruckRegNoExpanded by remember {
        mutableStateOf(false)
    }
    val selectTruckRegNoDropDownIcon = if (selectTruckRegNoExpanded)
        R.drawable.arrowdropup
    else
        R.drawable.arrowdropdown
    val regOne = "#E2975J7"
    val regTwo = "I3357Z4"
    val regThree = "O2J00GK"
    val truckRegNumbers = listOf(regOne, regTwo, regThree)
    var selectedTruckRegNo by remember {
        mutableStateOf("")
    }

    Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.vehicleinformation),
                    contentDescription = "truck reg number icon"
                )
                Spacer(Modifier.padding(start = 8.dp))
                Text(text = "Vehicle Information", fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.padding(top = 8.dp))
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = registrationNumberState,
                onValueChange = {
                    registrationNumberState = it
                },
                label = {
                    Text("Select truck registration number")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.regnumber),
                        contentDescription = "truck reg number icon"
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = selectTruckRegNoDropDownIcon),
                        contentDescription = "Select truck registration number drop down icon",
                        Modifier.clickable { selectTruckRegNoExpanded = !selectTruckRegNoExpanded })
                }
            )
            DropdownMenu(
                expanded = selectTruckRegNoExpanded,
                onDismissRequest = {
                    selectTruckRegNoExpanded = !selectTruckRegNoExpanded
                }) {
                truckRegNumbers.forEach { truckNumber ->
                    DropdownMenuItem(onClick = {
                        selectedTruckRegNo = truckNumber
                        selectTruckRegNoExpanded = !selectTruckRegNoExpanded
                    }) {
                        Text(text = truckNumber)
                    }
                }
                /*Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.regnumber),
                        contentDescription = "truck reg number icon"
                    )
                    Spacer(Modifier.padding(start = 8.dp))

                }*/
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TruckChecklistTheme {
        ChecklistScreen()
    }
}