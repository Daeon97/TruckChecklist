package com.engelsimmanuel.truckchecklist.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.engelsimmanuel.truckchecklist.R
import com.engelsimmanuel.truckchecklist.sharedprefs.SharedPrefsManager
import com.engelsimmanuel.truckchecklist.ui.theme.TruckChecklistTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthScreen(activity = this)
        }
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrefsManager.getInstance(this).isLoggedIn) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    TruckChecklistTheme {
        Text("Hello world. My name is Engels")
    }
}

@Composable
fun AuthScreen(activity: Activity) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    var driverId by remember {
        mutableStateOf("")
    }
    var driverPassword by remember {
        mutableStateOf("")
    }

    var setShowDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                    top = 16.dp
                )
                .verticalScroll(state = scrollState)
        ) {
            Image(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                painter = painterResource(
                    id = R.drawable.signinillustration
                ),
                contentDescription = "sign in illustration"
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp)
                    .fillMaxWidth(),
                value = driverId,
                label = {
                    Text(text = "Driver ID")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.driverid),
                        contentDescription = "driver id text field leading icon"
                    )
                },
                onValueChange = {
                    driverId = it
                },
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp)
                    .fillMaxWidth(),
                value = driverPassword,
                label = {
                    Text(text = "Driver password")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.driverpassword),
                        contentDescription = "driver password text field leading icon"
                    )
                },
                onValueChange = {
                    driverPassword = it
                },
            )
            Button(
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(R.color.purple_500)),
                onClick = {
                    if (driverId.trim().equals("") || driverPassword.trim().equals("")) {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Please fill all fields")
                        }
                    } else {
                        coroutineScope.launch {
                            setShowDialog = true
                            delay(3000)
                            setShowDialog = false
                            SharedPrefsManager.getInstance(activity).isLoggedIn = true
                            activity.startActivity(Intent(activity, DashboardActivity::class.java))
                            activity.finish()
                        }
                    }
                }
            ) {
                Text(
                    text = "Sign in",
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                )
            }
            if (setShowDialog) {
                Dialog(
                    properties = DialogProperties(
                        dismissOnBackPress = false,
                        dismissOnClickOutside = false
                    ),
                    onDismissRequest = {
                        setShowDialog = false
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CircularProgressIndicator(color = Color(R.color.purple_500))
                        Text(
                            text = "Signing in...",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}