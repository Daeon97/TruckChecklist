package com.engelsimmanuel.truckchecklist.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.engelsimmanuel.truckchecklist.R
import com.engelsimmanuel.truckchecklist.database.utils.Info
import com.engelsimmanuel.truckchecklist.mvvm.arch.InfoViewModel
import com.engelsimmanuel.truckchecklist.sharedprefs.SharedPrefsManager
import com.engelsimmanuel.truckchecklist.ui.theme.TruckChecklistTheme
import kotlinx.coroutines.launch

class DashboardActivity : ComponentActivity() {
    private lateinit var infoViewModel: InfoViewModel
    private lateinit var infoList: ArrayList<Info>

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        infoViewModel = InfoViewModel(application)
        infoList = arrayListOf()

        infoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        ).get(InfoViewModel::class.java)
        infoViewModel.allInfo.observe(
            this, {
                infoList.addAll(it)
                Log.wtf("info list", "info list is ${infoList.size}")
            }
        )
        setContent {
            DashboardScreen(infoList = infoList, activity = this)
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

@ExperimentalMaterialApi
@Composable
fun DashboardScreen(infoList: ArrayList<Info>, activity: Activity) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var dropdownShown by remember {
        mutableStateOf(false)
    }
    val dropdownMenus = listOf("About", "Sign out")
    val dropdownIconIds = listOf(R.drawable.about, R.drawable.logout)

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            activity.startActivity(Intent(activity, ChecklistActivity::class.java))
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
                            }
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
                                        if(dropdownIndex == 0){
                                            //.
                                        } else {
                                            SharedPrefsManager.getInstance(activity).isLoggedIn = false
                                            activity.startActivity(Intent(activity, AuthActivity::class.java))
                                            activity.finish()
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
                    Text("There are ${infoList.size} items here")
                }
            }
        }
    }
}