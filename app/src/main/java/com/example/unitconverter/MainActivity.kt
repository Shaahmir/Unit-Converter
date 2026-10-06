package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlin.math.abs

val bgLight = 0xFFF7F5F0
val fgLight = 0xFF1C1B19

val bgDark = 0xFF161513
val fgDark = 0xFFEDEAE3
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight),
                    contentColor = if (isSystemInDarkTheme()) Color(fgDark) else Color(fgLight)
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Preview
@Composable
fun UnitConverter() {

    var input by remember { mutableStateOf("") }
    var output by remember { mutableDoubleStateOf(0.0) }

    var inputSelection by remember { mutableStateOf("Select Unit") }
    var outputSelection by remember { mutableStateOf("Select Unit") }

    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }

    fun converter() {

        val value = input.toDoubleOrNull() ?: 0.0

        val inputValue = when (inputSelection) {
            "Meter" -> 1.0
            "Centimeter" -> 0.01
            "Millimeter" -> 0.001
            "Kilometer" -> 1000.0
            "Inch" -> 0.0254
            "Foot" -> 0.3048
            else -> 0.0
        }

        val outputValue = when (outputSelection) {
            "Meter" -> 1.0
            "Centimeter" -> 0.01
            "Millimeter" -> 0.001
            "Kilometer" -> 1000.0
            "Inch" -> 0.0254
            "Foot" -> 0.3048
            else -> 0.0
        }

        output = value * (inputValue / outputValue)
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight(850),
            color = if (isSystemInDarkTheme()) Color(fgDark) else Color(fgLight)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = input,
            onValueChange = {input = it; converter()},
            placeholder = {Text("Enter a unit")},
            modifier =  Modifier
                .padding(vertical = 16.dp)
                .width(300.dp)
                .heightIn(max = 150.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = if (isSystemInDarkTheme()) Color(fgDark) else Color(fgLight),
                unfocusedContainerColor = if (isSystemInDarkTheme()) Color(fgDark) else Color(fgLight),
                focusedTextColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight),
                unfocusedTextColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight),
                focusedBorderColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight),
                unfocusedBorderColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight),
                cursorColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight),
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray
            )
        )

        Row {

            Box (modifier = Modifier.padding(end = 16.dp)) {
                Button(
                    modifier = Modifier.width(140.dp),
                    onClick = {inputExpanded = true},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSystemInDarkTheme()) Color(fgDark) else Color(fgLight),
                        contentColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)
                    )
                ){
                    Text(inputSelection)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = inputExpanded,
                    onDismissRequest = {inputExpanded = false},
                    containerColor = if (isSystemInDarkTheme()) Color(fgDark) else Color(fgLight)
                ) {
                    DropdownMenuItem(text = { Text("Meter") },  onClick = {
                        inputSelection = "Meter"
                        inputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        inputSelection = "Centimeter"
                        inputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Inch") }, onClick = {
                        inputSelection = "Inch"
                        inputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Kilometer") }, onClick = {
                        inputSelection = "Kilometer"
                        inputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = {
                        inputSelection = "Millimeter"
                        inputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Foot") }, onClick = {
                        inputSelection = "Foot"
                        inputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                }
            }

            Box {
                Button(
                    modifier = Modifier.width(140.dp),
                    onClick = {outputExpanded = true},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSystemInDarkTheme()) Color(fgDark) else Color(fgLight),
                        contentColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)
                    )
                ){
                    Text(outputSelection)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = outputExpanded,
                    onDismissRequest = {outputExpanded = false},
                    containerColor = if (isSystemInDarkTheme()) Color(fgDark) else Color(fgLight)
                ) {
                    DropdownMenuItem(text = { Text("Meter") },  onClick = {
                        outputSelection = "Meter"
                        outputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        outputSelection = "Centimeter"
                        outputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Inch") }, onClick = {
                        outputSelection = "Inch"
                        outputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Kilometer") }, onClick = {
                        outputSelection = "Kilometer"
                        outputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = {
                        outputSelection = "Millimeter"
                        outputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                    DropdownMenuItem(text = { Text("Foot") }, onClick = {
                        outputSelection = "Foot"
                        outputExpanded = false
                        converter()
                    }, colors = MenuDefaults.itemColors(textColor = if (isSystemInDarkTheme()) Color(bgDark) else Color(bgLight)))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            if (!(output.isNaN()) && outputSelection != "Select Unit" && output != 0.0) {
                Text(
                    "Result: ${formatOutput(output)} $outputSelection"
                )
            }
        }
    }
}

fun formatOutput(value: Double): String {
    if (abs(value) >= 1_000_000 || (value != 0.0 && abs(value) < 0.0001)) {
        return "%.4e".format(value)
    }
    return "%.2f".format(value)
}
