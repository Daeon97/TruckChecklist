package com.engelsimmanuel.truckchecklist.screens

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.engelsimmanuel.truckchecklist.R
import com.engelsimmanuel.truckchecklist.mvvm.arch.InfoViewModel
import com.engelsimmanuel.truckchecklist.mvvm.arch.InfoViewModelFactory
import com.engelsimmanuel.truckchecklist.sharedprefs.SharedPrefsManager
import com.engelsimmanuel.truckchecklist.ui.theme.TruckChecklistTheme
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.util.*

class DashboardActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DashboardScreen(activity = this)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    TruckChecklistTheme {
        Text("Hello world. My name is Engels")
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun DashboardScreen(
    activity: Activity
) {
    val context = LocalContext.current
    val infoViewModel: InfoViewModel =
        viewModel(factory = InfoViewModelFactory(context.applicationContext as Application))
    val infoList = infoViewModel.allInfo.observeAsState(listOf()).value

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var dropdownShown by remember {
        mutableStateOf(false)
    }
    val dropdownMenus = listOf("About", "Clear database", "Sign out")
    val dropdownIconIds = listOf(R.drawable.about, R.drawable.database, R.drawable.logout)

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            activity.startActivity(
                                Intent(
                                    activity,
                                    ChecklistActivity::class.java
                                )
                            )
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                            activity.finish()
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                        ),
                        painter = painterResource(id = R.drawable.newvehiclecheck),
                        contentDescription = "new vehicle check icon"
                    )
                    Text(
                        text = "New vehicle check",
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                            end = 16.dp
                        )
                    )
                }
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    thickness = 1.dp,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                                scaffoldState.snackbarHostState.showSnackbar(message = "This feature is unavailable at this time")
                            }
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                        ),
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "settings icon"
                    )
                    Text(
                        text = "Settings",
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                            end = 16.dp
                        )
                    )
                }
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    thickness = 1.dp,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                                scaffoldState.snackbarHostState.showSnackbar(message = "This feature is unavailable at this time")
                            }
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                        ),
                        painter = painterResource(id = R.drawable.newbugreport),
                        contentDescription = "bug report icon"
                    )
                    Text(
                        text = "Bug report",
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                            end = 16.dp
                        )
                    )
                }
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = Color(R.color.purple_500),
                    onClick = {
                        coroutineScope.launch {
                            modalBottomSheetState.show()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "add icon for floating action button"
                    )
                }
            },
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.maleillustration),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        "Welcome",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(android.R.color.white)
                            ),
                            onClick = {
                                dropdownShown = !dropdownShown
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.menu),
                                contentDescription = "menu button"
                            )
                            DropdownMenu(
                                expanded = dropdownShown,
                                onDismissRequest = {
                                    dropdownShown = !dropdownShown
                                }
                            ) {
                                dropdownMenus.forEachIndexed { dropdownIndex, dropdownMenu ->
                                    DropdownMenuItem(onClick = {
                                        dropdownShown = !dropdownShown
                                        when (dropdownIndex) {
                                            0 -> {
                                                coroutineScope.launch {
                                                    scaffoldState.snackbarHostState.showSnackbar(
                                                        message = "Truck Checklist v1.0",
                                                        actionLabel = "Dismiss",
                                                        duration = SnackbarDuration.Indefinite
                                                    )
                                                }
                                            }
                                            1 -> {
                                                infoViewModel.deleteAllInfo()
                                            }
                                            else -> {
                                                SharedPrefsManager.getInstance(activity).isLoggedIn =
                                                    false
                                                activity.startActivity(
                                                    Intent(
                                                        activity,
                                                        AuthActivity::class.java
                                                    )
                                                )
                                                activity.finish()
                                            }
                                        }
                                    }) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(id = dropdownIconIds[dropdownIndex]),
                                                contentDescription = "menu icons"
                                            )
                                            Text(
                                                modifier = Modifier.padding(start = 8.dp),
                                                text = dropdownMenu
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (infoList.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .width(300.dp)
                                .height(300.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            painter = painterResource(
                                id = R.drawable.checklistillustration
                            ),
                            contentDescription = "check list illustration"
                        )
                        Text(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally),
                            text = "You have not made any checks yet",
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2)
                    ) {
                        items(infoList) { info ->
                            Card(
                                modifier = Modifier
                                    .padding(16.dp),
                                shape = RoundedCornerShape(16),
                                elevation = 16.dp
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.time),
                                            contentDescription = "image of a clock"
                                        )
                                        Text(
                                            text = showDateAndTime(info.timestamp),
                                            modifier = Modifier
                                                .padding(start = 8.dp),
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(top = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .width(100.dp)
                                                .height(100.dp),
                                            painter = painterResource(id = R.drawable.vehiclesatfactoryillustration),
                                            contentDescription = "vehicles at factory illustration",
                                        )
                                        Column(
                                            modifier = Modifier.padding(start = 16.dp),
                                            verticalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            Text(
                                                "Reg no. ${info.registrationNumber}"
                                            )
                                            Text(
                                                info.identificationNumber
                                            )
                                            Text(
                                                "Model ${info.model}, ${info.year}"
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.truckboy),
                                            contentDescription = "truck boy icon"
                                        )
                                        Text(
                                            info.truckBoy,
                                            modifier = Modifier
                                                .padding(start = 8.dp),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun showDateAndTime(timestamp: String): String {
    val actualTimestamp = Timestamp.valueOf(timestamp)
    return "On ${
        DateFormat.format(
            "E dd MMMM yyyy",
            actualTimestamp.time
        )
    } at ${DateFormat.format("hh:mm:ss", actualTimestamp.time)}"
}