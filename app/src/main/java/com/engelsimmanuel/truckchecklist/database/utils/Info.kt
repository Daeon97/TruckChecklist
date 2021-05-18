package com.engelsimmanuel.truckchecklist.database.utils

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.sql.Timestamp

// data entity class to specify individual data that will be stored
@Entity(tableName = "info_table")
data class Info(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Timestamp,

    // vehicle, driver and truck boy info
    @ColumnInfo(name = "registration_number") val registrationNumber: String,
    @ColumnInfo(name = "identification_number") val identificationNumber: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "driver_name") val driverName: String,
    @ColumnInfo(name = "truck_boy") val truckBoy: String,


    // interior check
    @ColumnInfo(name = "seat_and_seat_belts") val seatAndSeatBelts: Boolean,
    @ColumnInfo(name = "seat_and_seat_belts_comment") val seatAndSeatBeltsComment: String?,

    @ColumnInfo(name = "warning_lamps_control") val warningLampsControl: Boolean,
    @ColumnInfo(name = "warning_lamps_control_comment") val warningLampsControlComment: String?,

    @ColumnInfo(name = "wiper_view_to_front") val wiperViewToFront: Boolean,
    @ColumnInfo(name = "wiper_view_to_front_comment") val wiperViewToFrontComment: String?,

    @ColumnInfo(name = "brake_controls") val brakeControls: Boolean,
    @ColumnInfo(name = "brake_controls_comment") val brakeControlsComment: String?,

    @ColumnInfo(name = "doors_mirror_horn") val doorsMirrorHorn: Boolean,
    @ColumnInfo(name = "doors_mirror_horn_comment") val doorsMirrorHornComment: String?,

    @ColumnInfo(name = "hand_brake_level") val handBrakeLevel: Boolean,
    @ColumnInfo(name = "hand_brake_level_comment") val handBrakeLevelComment: String?,

    @ColumnInfo(name = "speedometer") val speedometer: Boolean,
    @ColumnInfo(name = "speedometer_comment") val speedometerComment: String?,

    @ColumnInfo(name = "driver_license") val driverLicense: Boolean,
    @ColumnInfo(name = "driver_license_comment") val driverLicenseComment: String?,


    // safety check
    @ColumnInfo(name = "basic_tool_set") val basicToolSet: Boolean,
    @ColumnInfo(name = "basic_tool_set_comment") val basicToolSetComment: String?,

    @ColumnInfo(name = "reflector_triangle") val reflectorTriangle: Boolean,
    @ColumnInfo(name = "reflector_triangle_comment") val reflectorTriangleComment: String?,

    @ColumnInfo(name = "fire_extinguishers") val fireExtinguishers: Boolean,
    @ColumnInfo(name = "fire_extinguishers_comment") val fireExtinguishersComment: String?,

    @ColumnInfo(name = "hazard_warning_light") val hazardWarningLight: Boolean,
    @ColumnInfo(name = "hazard_warning_light_comment") val hazardWarningLightComment: String?,

    @ColumnInfo(name = "head_lamp") val headLamp: Boolean,
    @ColumnInfo(name = "head_lamp_comment") val headLampComment: String?,

    @ColumnInfo(name = "brake_light") val brakeLight: Boolean,
    @ColumnInfo(name = "brake_light_comment") val brakeLightComment: String?,

    @ColumnInfo(name = "indicator") val indicator: Boolean,
    @ColumnInfo(name = "indicator_comment") val indicatorComment: String?,

    @ColumnInfo(name = "tyre_condition") val tyreCondition: Boolean,
    @ColumnInfo(name = "tyre_condition_comment") val tyreConditionComment: String?,


    // exterior check
    @ColumnInfo(name = "registration_plates") val registrationPlates: Boolean,
    @ColumnInfo(name = "registration_plates_comment") val registrationPlatesComment: String?,

    @ColumnInfo(name = "turn_table_height") val turnTableHeight: Boolean,
    @ColumnInfo(name = "turn_table_height_comment") val turnTableHeightComment: String?,

    @ColumnInfo(name = "turn_table_hook") val turnTableHook: Boolean,
    @ColumnInfo(name = "turn_table_hook_comment") val turnTableHookComment: String?,

    @ColumnInfo(name = "air_spring") val airSpring: Boolean,
    @ColumnInfo(name = "air_spring_comment") val airSpringComment: String?,

    @ColumnInfo(name = "brake_system") val brakeSystem: Boolean,
    @ColumnInfo(name = "brake_system_comment") val brakeSystemComment: String?,

    @ColumnInfo(name = "fuel_tank_cover") val fuelTankCover: Boolean,
    @ColumnInfo(name = "fuel_tank_cover_comment") val fuelTankCoverComment: String?,

    @ColumnInfo(name = "spared_tyre") val sparedTyre: Boolean,
    @ColumnInfo(name = "spared_tyre_comment") val sparedTyreComment: String?,



    // general needs
    @ColumnInfo(name = "jack_and_handle") val jackAndHandle: Boolean,
    @ColumnInfo(name = "jack_and_handle_comment") val jackAndHandleComment: String?,

    @ColumnInfo(name = "wheel_spanner") val wheelSpanner: Boolean,
    @ColumnInfo(name = "wheel_spanner_comment") val wheelSpannerComment: String?,

    @ColumnInfo(name = "wedge") val wedge: Boolean,
    @ColumnInfo(name = "wedge_comment") val wedgeComment: String?
)