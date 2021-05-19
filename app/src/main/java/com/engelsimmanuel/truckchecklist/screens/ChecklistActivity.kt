package com.engelsimmanuel.truckchecklist.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider
import com.engelsimmanuel.truckchecklist.R
import com.engelsimmanuel.truckchecklist.database.utils.Info
import com.engelsimmanuel.truckchecklist.mvvm.arch.InfoViewModel
import com.engelsimmanuel.truckchecklist.ui.theme.TruckChecklistTheme
import kotlinx.coroutines.launch
import java.util.Date
import java.sql.Timestamp

class ChecklistActivity : ComponentActivity() {
    private lateinit var infoViewModel: InfoViewModel

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infoViewModel = InfoViewModel(application)
        infoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        ).get(InfoViewModel::class.java)

        setContent {
            ChecklistScreen(infoViewModel = infoViewModel, activity = this)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}


@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun ChecklistScreenPreview() {
    TruckChecklistTheme {
        Text("Hello world. My name is Engels")
    }
}

@ExperimentalAnimationApi
@Composable
fun ChecklistScreen(infoViewModel: InfoViewModel, activity: Activity) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    var registrationNumber by remember {
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
    val regTwo = "#S3357Z4"
    val regThree = "#O2J00GK"
    val truckRegNumbers = listOf(regOne, regTwo, regThree)
    val vehicleIdentificationNumber = when (registrationNumber) {
        regOne -> "ID-2975J7"
        regTwo -> "ID-3357Z4"
        regThree -> "ID-2J00GK"
        else -> ""
    }
    val model = when (registrationNumber) {
        regOne -> "M-2j7"
        regTwo -> "M-3Z4"
        regThree -> "M-2GK"
        else -> ""
    }
    val year = when (registrationNumber) {
        regOne -> "2017"
        regTwo -> "2021"
        regThree -> "2009"
        else -> ""
    }
    var truckBoy by remember {
        mutableStateOf("")
    }
    var selectTruckBoyExpanded by remember {
        mutableStateOf(false)
    }
    val selectTruckBoyDropDownIcon = if (selectTruckBoyExpanded)
        R.drawable.arrowdropup
    else
        R.drawable.arrowdropdown
    val truckBoyOne = "Engels"
    val truckBoyTwo = "Immanuel"
    val truckBoyThree = "Opeyemi"
    val truckBoyFour = "Julius"
    val truckBoys = listOf(truckBoyOne, truckBoyTwo, truckBoyThree, truckBoyFour)

    var seatAndSeatBeltsChecked by remember {
        mutableStateOf(false)
    }
    var seatAndSeatBeltsComment by remember {
        mutableStateOf("")
    }

    var warningLampsControlChecked by remember {
        mutableStateOf(false)
    }
    var warningLampsControlComment by remember {
        mutableStateOf("")
    }

    var wiperViewToFrontChecked by remember {
        mutableStateOf(false)
    }
    var wiperViewToFrontComment by remember {
        mutableStateOf("")
    }

    var brakeControlsChecked by remember {
        mutableStateOf(false)
    }
    var brakeControlsComment by remember {
        mutableStateOf("")
    }

    var doorsMirrorsHornChecked by remember {
        mutableStateOf(false)
    }
    var doorsMirrorsHornComment by remember {
        mutableStateOf("")
    }

    var handBrakerLevelChecked by remember {
        mutableStateOf(false)
    }
    var handBrakerLevelComment by remember {
        mutableStateOf("")
    }

    var speedometerChecked by remember {
        mutableStateOf(false)
    }
    var speedometerComment by remember {
        mutableStateOf("")
    }

    var driverLicenseChecked by remember {
        mutableStateOf(false)
    }
    var driverLicenseComment by remember {
        mutableStateOf("")
    }


    var basicToolSetChecked by remember {
        mutableStateOf(false)
    }
    var basicToolSetComment by remember {
        mutableStateOf("")
    }

    var reflectorTriangleChecked by remember {
        mutableStateOf(false)
    }
    var reflectorTriangleComment by remember {
        mutableStateOf("")
    }

    var fireExtinguishersChecked by remember {
        mutableStateOf(false)
    }
    var fireExtinguishersComment by remember {
        mutableStateOf("")
    }

    var hazardWarningLightChecked by remember {
        mutableStateOf(false)
    }
    var hazardWarningLightComment by remember {
        mutableStateOf("")
    }

    var headLampChecked by remember {
        mutableStateOf(false)
    }
    var headLampComment by remember {
        mutableStateOf("")
    }

    var brakeLightChecked by remember {
        mutableStateOf(false)
    }
    var brakeLightComment by remember {
        mutableStateOf("")
    }

    var indicatorChecked by remember {
        mutableStateOf(false)
    }
    var indicatorComment by remember {
        mutableStateOf("")
    }

    var tyreConditionChecked by remember {
        mutableStateOf(false)
    }
    var tyreConditionComment by remember {
        mutableStateOf("")
    }


    var registrationPlatesChecked by remember {
        mutableStateOf(false)
    }
    var registrationPlatesComment by remember {
        mutableStateOf("")
    }

    var turnTableHeightChecked by remember {
        mutableStateOf(false)
    }
    var turnTableHeightComment by remember {
        mutableStateOf("")
    }

    var turnTableHookChecked by remember {
        mutableStateOf(false)
    }
    var turnTableHookComment by remember {
        mutableStateOf("")
    }

    var airSpringChecked by remember {
        mutableStateOf(false)
    }
    var airSpringComment by remember {
        mutableStateOf("")
    }

    var brakeSystemChecked by remember {
        mutableStateOf(false)
    }
    var brakeSystemComment by remember {
        mutableStateOf("")
    }

    var fuelTankCoverChecked by remember {
        mutableStateOf(false)
    }
    var fuelTankCoverComment by remember {
        mutableStateOf("")
    }

    var sparedTyreChecked by remember {
        mutableStateOf(false)
    }
    var sparedTyreComment by remember {
        mutableStateOf("")
    }


    var jackAndHandleChecked by remember {
        mutableStateOf(false)
    }
    var jackAndHandleComment by remember {
        mutableStateOf("")
    }

    var wheelSpannerChecked by remember {
        mutableStateOf(false)
    }
    var wheelSpannerComment by remember {
        mutableStateOf("")
    }

    var wedgeChecked by remember {
        mutableStateOf(false)
    }
    var wedgeComment by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(android.R.color.white)
                    ),
                    onClick = {
                        activity.startActivity(Intent(activity, DashboardActivity::class.java))
                        activity.finish()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "back button"
                    )
                }
                Text(
                    text = "New vehicle check",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .verticalScroll(state = scrollState)
            ) {
                Image(
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    painter = painterResource(
                        id = R.drawable.truckillustration
                    ),
                    contentDescription = "truck illustration"
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vehicleinformation),
                        contentDescription = "vehicle information icon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Vehicle Information",
                        fontSize = 32.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.regnumber),
                        contentDescription = "truck registration number icon"
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        value = registrationNumber,
                        enabled = false,
                        label = {
                            Text("Select truck registration number")
                        },
                        onValueChange = {
                            registrationNumber = it
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = selectTruckRegNoDropDownIcon),
                                contentDescription = "Select truck registration number drop down icon",
                                modifier = Modifier
                                    .clickable {
                                        selectTruckRegNoExpanded = !selectTruckRegNoExpanded
                                    }
                            )
                        }
                    )
                    DropdownMenu(
                        modifier = Modifier
                            .fillMaxWidth(),
                        expanded = selectTruckRegNoExpanded,
                        onDismissRequest = {
                            selectTruckRegNoExpanded = !selectTruckRegNoExpanded
                        }
                    ) {
                        truckRegNumbers.forEach { truckNumber ->
                            DropdownMenuItem(onClick = {
                                registrationNumber = truckNumber
                                selectTruckRegNoExpanded = !selectTruckRegNoExpanded
                            }) {
                                Text(text = truckNumber)
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.identificationnumber),
                        contentDescription = "vehicle identification number icon"
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        value = vehicleIdentificationNumber,
                        enabled = false,
                        label = {
                            Text("Vehicle identification number")
                        },
                        onValueChange = {},
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.modelnumber),
                        contentDescription = "model number icon"
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        value = model,
                        enabled = false,
                        label = {
                            Text("Model number")
                        },
                        onValueChange = {},
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.year),
                        contentDescription = "year icon"
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        value = year,
                        enabled = false,
                        label = {
                            Text("Year")
                        },
                        onValueChange = {},
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.truckboy),
                        contentDescription = "select truck boy icon"
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        value = truckBoy,
                        enabled = false,
                        label = {
                            Text("Select truck boy")
                        },
                        onValueChange = {
                            truckBoy = it
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = selectTruckBoyDropDownIcon),
                                contentDescription = "Select truck boy drop down icon",
                                modifier = Modifier
                                    .clickable {
                                        selectTruckBoyExpanded = !selectTruckBoyExpanded
                                    }
                            )
                        }
                    )
                    DropdownMenu(
                        modifier = Modifier
                            .fillMaxWidth(),
                        expanded = selectTruckBoyExpanded,
                        onDismissRequest = {
                            selectTruckBoyExpanded = !selectTruckBoyExpanded
                        }
                    ) {
                        truckBoys.forEach { truckBoyName ->
                            DropdownMenuItem(onClick = {
                                truckBoy = truckBoyName
                                selectTruckBoyExpanded = !selectTruckBoyExpanded
                            }) {
                                Text(text = truckBoyName)
                            }
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.interiorcheck),
                        contentDescription = "interior check icon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Interior Check",
                        fontSize = 32.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.seatandseatbelts),
                        contentDescription = "seat and seat belts icon"
                    )
                    Text(
                        "Seat and seat belts", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = seatAndSeatBeltsChecked,
                        onCheckedChange = {
                            seatAndSeatBeltsChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = seatAndSeatBeltsChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = seatAndSeatBeltsComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            seatAndSeatBeltsComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.warninglampscontrol),
                        contentDescription = "warning lamps control icon"
                    )
                    Text(
                        "Warning lamps control", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = warningLampsControlChecked,
                        onCheckedChange = {
                            warningLampsControlChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = warningLampsControlChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = warningLampsControlComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            warningLampsControlComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wiperviewtofront),
                        contentDescription = "wiper view to front icon"
                    )
                    Text(
                        "Wiper view to front", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = wiperViewToFrontChecked,
                        onCheckedChange = {
                            wiperViewToFrontChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = wiperViewToFrontChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = wiperViewToFrontComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            wiperViewToFrontComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.brakecontrols),
                        contentDescription = "brake controls icon"
                    )
                    Text(
                        "Brake controls", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = brakeControlsChecked,
                        onCheckedChange = {
                            brakeControlsChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = brakeControlsChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = brakeControlsComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            brakeControlsComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.doorsmirrorshorn),
                        contentDescription = "doors mirrors horn icon"
                    )
                    Text(
                        "Doors, mirrors, horn", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = doorsMirrorsHornChecked,
                        onCheckedChange = {
                            doorsMirrorsHornChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = doorsMirrorsHornChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = doorsMirrorsHornComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            doorsMirrorsHornComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.handbrakerlevel),
                        contentDescription = "hand braker level icon"
                    )
                    Text(
                        "Hand braker level", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = handBrakerLevelChecked,
                        onCheckedChange = {
                            handBrakerLevelChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = handBrakerLevelChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = handBrakerLevelComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            handBrakerLevelComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.speedometer),
                        contentDescription = "speedometer icon"
                    )
                    Text(
                        "Speedometer", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = speedometerChecked,
                        onCheckedChange = {
                            speedometerChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = speedometerChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = speedometerComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            speedometerComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.driverlicense),
                        contentDescription = "driver license icon"
                    )
                    Text(
                        "Driver license", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = driverLicenseChecked,
                        onCheckedChange = {
                            driverLicenseChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = driverLicenseChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = driverLicenseComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            driverLicenseComment = it
                        },
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.safetycheck),
                        contentDescription = "safety check icon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Safety Check",
                        fontSize = 32.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.basictoolset),
                        contentDescription = "basic tool set icon"
                    )
                    Text(
                        "Basic tool set", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = basicToolSetChecked,
                        onCheckedChange = {
                            basicToolSetChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = basicToolSetChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = basicToolSetComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            basicToolSetComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.reflectortriangle),
                        contentDescription = "reflector triangle icon"
                    )
                    Text(
                        "Reflector triangle", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = reflectorTriangleChecked,
                        onCheckedChange = {
                            reflectorTriangleChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = reflectorTriangleChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = reflectorTriangleComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            reflectorTriangleComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fireextinguishers),
                        contentDescription = "fire extinguishers icon"
                    )
                    Text(
                        "Fire extinguishers", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = fireExtinguishersChecked,
                        onCheckedChange = {
                            fireExtinguishersChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = fireExtinguishersChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = fireExtinguishersComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            fireExtinguishersComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hazardwarninglight),
                        contentDescription = "hazard warning light icon"
                    )
                    Text(
                        "Hazard warning light", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = hazardWarningLightChecked,
                        onCheckedChange = {
                            hazardWarningLightChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = hazardWarningLightChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = hazardWarningLightComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            hazardWarningLightComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.headlamp),
                        contentDescription = "head lamp icon"
                    )
                    Text(
                        "Head lamp", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = headLampChecked,
                        onCheckedChange = {
                            headLampChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = headLampChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = headLampComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            headLampComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.brakelight),
                        contentDescription = "brake light icon"
                    )
                    Text(
                        "Brake light", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = brakeLightChecked,
                        onCheckedChange = {
                            brakeLightChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = brakeLightChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = brakeLightComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            brakeLightComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.indicator),
                        contentDescription = "indicator icon"
                    )
                    Text(
                        "Indicator", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = indicatorChecked,
                        onCheckedChange = {
                            indicatorChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = indicatorChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = indicatorComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            indicatorComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tyrecondition),
                        contentDescription = "tyre condition icon"
                    )
                    Text(
                        "Tyre condition", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = tyreConditionChecked,
                        onCheckedChange = {
                            tyreConditionChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = tyreConditionChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = tyreConditionComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            tyreConditionComment = it
                        },
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.exteriorcheck),
                        contentDescription = "exterior check icon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Exterior Check",
                        fontSize = 32.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.registrationplates),
                        contentDescription = "registration plates icon"
                    )
                    Text(
                        "Registration plates", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = registrationPlatesChecked,
                        onCheckedChange = {
                            registrationPlatesChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = registrationPlatesChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = registrationPlatesComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            registrationPlatesComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.turntableheight),
                        contentDescription = "turn table height icon"
                    )
                    Text(
                        "Turn table height", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = turnTableHeightChecked,
                        onCheckedChange = {
                            turnTableHeightChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = turnTableHeightChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = turnTableHeightComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            turnTableHeightComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.turntablehook),
                        contentDescription = "turn table hook icon"
                    )
                    Text(
                        "Turn table hook", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = turnTableHookChecked,
                        onCheckedChange = {
                            turnTableHookChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = turnTableHookChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = turnTableHookComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            turnTableHookComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.airspring),
                        contentDescription = "air spring icon"
                    )
                    Text(
                        "Air spring", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = airSpringChecked,
                        onCheckedChange = {
                            airSpringChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = airSpringChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = airSpringComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            airSpringComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.brakesystem),
                        contentDescription = "brake system icon"
                    )
                    Text(
                        "Brake system", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = brakeSystemChecked,
                        onCheckedChange = {
                            brakeSystemChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = brakeSystemChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = brakeSystemComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            brakeSystemComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fueltankcover),
                        contentDescription = "fuel tank cover icon"
                    )
                    Text(
                        "Fuel tank cover", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = fuelTankCoverChecked,
                        onCheckedChange = {
                            fuelTankCoverChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = fuelTankCoverChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = fuelTankCoverComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            fuelTankCoverComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sparedtyre),
                        contentDescription = "spared tyre icon"
                    )
                    Text(
                        "Spared tyre", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = sparedTyreChecked,
                        onCheckedChange = {
                            sparedTyreChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = sparedTyreChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = sparedTyreComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            sparedTyreComment = it
                        },
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.generalneeds),
                        contentDescription = "general needs icon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "General Needs",
                        fontSize = 32.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.jackandhandle),
                        contentDescription = "jack and handle icon"
                    )
                    Text(
                        "Jack and handle", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = jackAndHandleChecked,
                        onCheckedChange = {
                            jackAndHandleChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = jackAndHandleChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = jackAndHandleComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            jackAndHandleComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wheelspanner),
                        contentDescription = "wheel spanner icon"
                    )
                    Text(
                        "Wheel spanner", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = wheelSpannerChecked,
                        onCheckedChange = {
                            wheelSpannerChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = wheelSpannerChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = wheelSpannerComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            wheelSpannerComment = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wedge),
                        contentDescription = "wedge icon"
                    )
                    Text(
                        "Wedge", modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = wedgeChecked,
                        onCheckedChange = {
                            wedgeChecked = it
                        }
                    )
                }
                AnimatedVisibility(visible = wedgeChecked) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        value = wedgeComment,
                        label = {
                            Text("Defects/Comments")
                        },
                        onValueChange = {
                            wedgeComment = it
                        },
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(R.color.purple_500)),
                    onClick = {
                        when {
                            registrationNumber != "" && vehicleIdentificationNumber != "" &&
                                    model != "" && year != "" && truckBoy != "" &&

                                    (!seatAndSeatBeltsChecked || (seatAndSeatBeltsChecked && seatAndSeatBeltsComment != "")) &&
                                    (!warningLampsControlChecked || (warningLampsControlChecked && warningLampsControlComment != "")) &&
                                    (!wiperViewToFrontChecked || (wiperViewToFrontChecked && wiperViewToFrontComment != "")) &&
                                    (!brakeControlsChecked || (brakeControlsChecked && brakeControlsComment != "")) &&
                                    (!doorsMirrorsHornChecked || (doorsMirrorsHornChecked && doorsMirrorsHornComment != "")) &&
                                    (!handBrakerLevelChecked || (handBrakerLevelChecked && handBrakerLevelComment != "")) &&
                                    (!speedometerChecked || (speedometerChecked && speedometerComment != "")) &&
                                    (!driverLicenseChecked || (driverLicenseChecked && driverLicenseComment != "")) &&

                                    (!basicToolSetChecked || (basicToolSetChecked && basicToolSetComment != "")) &&
                                    (!reflectorTriangleChecked || (reflectorTriangleChecked && reflectorTriangleComment != "")) &&
                                    (!fireExtinguishersChecked || (fireExtinguishersChecked && fireExtinguishersComment != "")) &&
                                    (!hazardWarningLightChecked || (hazardWarningLightChecked && hazardWarningLightComment != "")) &&
                                    (!headLampChecked || (headLampChecked && headLampComment != "")) &&
                                    (!brakeLightChecked || (brakeLightChecked && brakeLightComment != "")) &&
                                    (!indicatorChecked || (indicatorChecked && indicatorComment != "")) &&
                                    (!tyreConditionChecked || (tyreConditionChecked && tyreConditionComment != "")) &&

                                    (!registrationPlatesChecked || (registrationPlatesChecked && registrationPlatesComment != "")) &&
                                    (!turnTableHeightChecked || (turnTableHeightChecked && turnTableHeightComment != "")) &&
                                    (!turnTableHookChecked || (turnTableHookChecked && turnTableHookComment != "")) &&
                                    (!airSpringChecked || (airSpringChecked && airSpringComment != "")) &&
                                    (!brakeSystemChecked || (brakeSystemChecked && brakeSystemComment != "")) &&
                                    (!fuelTankCoverChecked || (fuelTankCoverChecked && fuelTankCoverComment != "")) &&
                                    (!sparedTyreChecked || (sparedTyreChecked && sparedTyreComment != "")) &&

                                    (!jackAndHandleChecked || (jackAndHandleChecked && jackAndHandleComment != "")) &&
                                    (!wheelSpannerChecked || (wheelSpannerChecked && wheelSpannerComment != "")) &&
                                    (!wedgeChecked || (wedgeChecked && wedgeComment != ""))

                            -> addChecklistToDatabase(
                                activity = activity,
                                infoViewModel = infoViewModel,

                                registrationNumber = registrationNumber,
                                vehicleIdentificationNumber = vehicleIdentificationNumber,
                                model = model,
                                year = year.toInt(),
                                truckBoy = truckBoy,

                                seatAndSeatBeltsChecked = seatAndSeatBeltsChecked,
                                seatAndSeatBeltsComment = seatAndSeatBeltsComment,
                                warningLampsControlChecked = warningLampsControlChecked,
                                warningLampsControlComment = warningLampsControlComment,
                                wiperViewToFrontChecked = wiperViewToFrontChecked,
                                wiperViewToFrontComment = wiperViewToFrontComment,
                                brakeControlsChecked = brakeControlsChecked,
                                brakeControlsComment = brakeControlsComment,
                                doorsMirrorsHornChecked = doorsMirrorsHornChecked,
                                doorsMirrorsHornComment = doorsMirrorsHornComment,
                                handBrakerLevelChecked = handBrakerLevelChecked,
                                handBrakerLevelComment = handBrakerLevelComment,
                                speedometerChecked = speedometerChecked,
                                speedometerComment = speedometerComment,
                                driverLicenseChecked = driverLicenseChecked,
                                driverLicenseComment = driverLicenseComment,

                                basicToolSetChecked = basicToolSetChecked,
                                basicToolSetComment = basicToolSetComment,
                                reflectorTriangleChecked = reflectorTriangleChecked,
                                reflectorTriangleComment = reflectorTriangleComment,
                                fireExtinguishersChecked = fireExtinguishersChecked,
                                fireExtinguishersComment = fireExtinguishersComment,
                                hazardWarningLightChecked = hazardWarningLightChecked,
                                hazardWarningLightComment = hazardWarningLightComment,
                                headLampChecked = headLampChecked,
                                headLampComment = headLampComment,
                                brakeLightChecked = brakeLightChecked,
                                brakeLightComment = brakeLightComment,
                                indicatorChecked = indicatorChecked,
                                indicatorComment = indicatorComment,
                                tyreConditionChecked = tyreConditionChecked,
                                tyreConditionComment = tyreConditionComment,

                                registrationPlatesChecked = registrationPlatesChecked,
                                registrationPlatesComment = registrationPlatesComment,
                                turnTableHeightChecked = turnTableHeightChecked,
                                turnTableHeightComment = turnTableHeightComment,
                                turnTableHookChecked = turnTableHookChecked,
                                turnTableHookComment = turnTableHookComment,
                                airSpringChecked = airSpringChecked,
                                airSpringComment = airSpringComment,
                                brakeSystemChecked = brakeSystemChecked,
                                brakeSystemComment = brakeSystemComment,
                                fuelTankCoverChecked = fuelTankCoverChecked,
                                fuelTankCoverComment = fuelTankCoverComment,
                                sparedTyreChecked = sparedTyreChecked,
                                sparedTyreComment = sparedTyreComment,

                                jackAndHandleChecked = jackAndHandleChecked,
                                jackAndHandleComment = jackAndHandleComment,
                                wheelSpannerChecked = wheelSpannerChecked,
                                wheelSpannerComment = wheelSpannerComment,
                                wedgeChecked = wedgeChecked,
                                wedgeComment = wedgeComment
                            )
                            else -> scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Please check that checked item(s) have their defects/comments field(s) filled")
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Submit",
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    )
                }
            }
        }
    }
}

private fun addChecklistToDatabase(
    activity: Activity,
    infoViewModel: InfoViewModel,

    registrationNumber: String,
    vehicleIdentificationNumber: String,
    model: String,
    year: Int,
    truckBoy: String,

    seatAndSeatBeltsChecked: Boolean,
    seatAndSeatBeltsComment: String,
    warningLampsControlChecked: Boolean,
    warningLampsControlComment: String,
    wiperViewToFrontChecked: Boolean,
    wiperViewToFrontComment: String,
    brakeControlsChecked: Boolean,
    brakeControlsComment: String,
    doorsMirrorsHornChecked: Boolean,
    doorsMirrorsHornComment: String,
    handBrakerLevelChecked: Boolean,
    handBrakerLevelComment: String,
    speedometerChecked: Boolean,
    speedometerComment: String,
    driverLicenseChecked: Boolean,
    driverLicenseComment: String,

    basicToolSetChecked: Boolean,
    basicToolSetComment: String,
    reflectorTriangleChecked: Boolean,
    reflectorTriangleComment: String,
    fireExtinguishersChecked: Boolean,
    fireExtinguishersComment: String,
    hazardWarningLightChecked: Boolean,
    hazardWarningLightComment: String,
    headLampChecked: Boolean,
    headLampComment: String,
    brakeLightChecked: Boolean,
    brakeLightComment: String,
    indicatorChecked: Boolean,
    indicatorComment: String,
    tyreConditionChecked: Boolean,
    tyreConditionComment: String,

    registrationPlatesChecked: Boolean,
    registrationPlatesComment: String,
    turnTableHeightChecked: Boolean,
    turnTableHeightComment: String,
    turnTableHookChecked: Boolean,
    turnTableHookComment: String,
    airSpringChecked: Boolean,
    airSpringComment: String,
    brakeSystemChecked: Boolean,
    brakeSystemComment: String,
    fuelTankCoverChecked: Boolean,
    fuelTankCoverComment: String,
    sparedTyreChecked: Boolean,
    sparedTyreComment: String,

    jackAndHandleChecked: Boolean,
    jackAndHandleComment: String,
    wheelSpannerChecked: Boolean,
    wheelSpannerComment: String,
    wedgeChecked: Boolean,
    wedgeComment: String
) {
    val date = Date()
    infoViewModel.insert(
        Info(
            uid = Timestamp(date.time).toString(),
            timestamp = Timestamp(date.time).toString(),
            registrationNumber = registrationNumber,
            identificationNumber = vehicleIdentificationNumber,
            model = model,
            year = year,
            driverName = "Name",
            truckBoy = truckBoy,
            seatAndSeatBelts = seatAndSeatBeltsChecked,
            seatAndSeatBeltsComment = seatAndSeatBeltsComment,
            warningLampsControl = warningLampsControlChecked,
            warningLampsControlComment = warningLampsControlComment,
            wiperViewToFront = wiperViewToFrontChecked,
            wiperViewToFrontComment = wiperViewToFrontComment,
            brakeControls = brakeControlsChecked,
            brakeControlsComment = brakeControlsComment,
            doorsMirrorHorn = doorsMirrorsHornChecked,
            doorsMirrorHornComment = doorsMirrorsHornComment,
            handBrakerLevel = handBrakerLevelChecked,
            handBrakerLevelComment = handBrakerLevelComment,
            speedometer = speedometerChecked,
            speedometerComment = speedometerComment,
            driverLicense = driverLicenseChecked,
            driverLicenseComment = driverLicenseComment,
            basicToolSet = basicToolSetChecked,
            basicToolSetComment = basicToolSetComment,
            reflectorTriangle = reflectorTriangleChecked,
            reflectorTriangleComment = reflectorTriangleComment,
            fireExtinguishers = fireExtinguishersChecked,
            fireExtinguishersComment = fireExtinguishersComment,
            hazardWarningLight = hazardWarningLightChecked,
            hazardWarningLightComment = hazardWarningLightComment,
            headLamp = headLampChecked,
            headLampComment = headLampComment,
            brakeLight = brakeLightChecked,
            brakeLightComment = brakeLightComment,
            indicator = indicatorChecked,
            indicatorComment = indicatorComment,
            tyreCondition = tyreConditionChecked,
            tyreConditionComment = tyreConditionComment,
            registrationPlates = registrationPlatesChecked,
            registrationPlatesComment = registrationPlatesComment,
            turnTableHeight = turnTableHeightChecked,
            turnTableHeightComment = turnTableHeightComment,
            turnTableHook = turnTableHookChecked,
            turnTableHookComment = turnTableHookComment,
            airSpring = airSpringChecked,
            airSpringComment = airSpringComment,
            brakeSystem = brakeSystemChecked,
            brakeSystemComment = brakeSystemComment,
            fuelTankCover = fuelTankCoverChecked,
            fuelTankCoverComment = fuelTankCoverComment,
            sparedTyre = sparedTyreChecked,
            sparedTyreComment = sparedTyreComment,
            jackAndHandle = jackAndHandleChecked,
            jackAndHandleComment = jackAndHandleComment,
            wheelSpanner = wheelSpannerChecked,
            wheelSpannerComment = wheelSpannerComment,
            wedge = wedgeChecked,
            wedgeComment = wedgeComment,
        )
    )
    Toast.makeText(activity, "Your report has been recorded", Toast.LENGTH_LONG).show()
    activity.startActivity(Intent(activity, DashboardActivity::class.java))
    activity.finish()
}