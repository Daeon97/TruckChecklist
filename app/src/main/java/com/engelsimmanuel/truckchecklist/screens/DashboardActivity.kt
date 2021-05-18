package com.engelsimmanuel.truckchecklist.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Vehicle Information", fontWeight = FontWeight.Bold)
            OutlinedTextField(value = registrationNumberState, label = {
                Text("Enter truck registration number")
            },
                onValueChange = {
                    registrationNumberState = it
                }
            )
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