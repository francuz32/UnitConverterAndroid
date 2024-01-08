package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LinearGradient()
                    UnitConverter()

                }
            }
        }
    }
}

@Composable
fun LinearGradient(){
    val gradient = Brush.linearGradient(
        0.0f to colorResource(R.color.lavender_purple),
        500.0f to colorResource(R.color.cotton_candy),
        start = Offset.Zero,
        end = Offset.Infinite
    )
    
    Box(modifier = Modifier.background(gradient))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {
    val fontFamily = FontFamily(
        Font(R.font.concertone_regular)
    )

    //Variables for distance
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Kilometers") }
    var outputUnit by remember { mutableStateOf("Kilometers") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember{ mutableStateOf(1000.00) }
    var oConversionFactor = remember { mutableStateOf(1000.00) }


    //Variables for weight
    var inputWeight by remember { mutableStateOf("Kilograms") }
    var outputWeight by remember { mutableStateOf("Kilograms") }

    fun convertUnits() {
        // ?: Elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        var result = (((inputValueDouble * (conversionFactor.value * 100)) / oConversionFactor.value) / 100)
        outputValue = result.toString()
    }

    //Making Length and Weight conversions match when selected
  /*  if(inputUnit == "Milligrams" || inputUnit == "Kilograms" || inputUnit == "Grams" || inputUnit == "Pounds" || inputUnit == "Ounces"){
        result = "Unable to transform from $inputUnit to $outputUnit"
    }
    if(outputUnit == "Milligrams" || inputUnit == "Kilograms" || inputUnit == "Grams" || inputUnit == "Pounds" || inputUnit == "Ounces"){
        inputUnit = "Kilograms"
    }
*/

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here all the UI elements will be stacked below each other

        Text("Constanza's Personalized",
            style = MaterialTheme.typography.headlineLarge,
            color = colorResource(R.color.white),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            modifier = Modifier.padding(32.dp))
        Text("Unit Converter",
            color = colorResource(R.color.pastel_blue),
            fontSize = 32.sp,
            )
        Spacer(modifier = Modifier.height(16.dp))
        //TextField, BasicTextField, OutlinedTextField to get user text
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            //Here goes what should happen when the value of our OutlinedTextField Changes})
                inputValue = it
                convertUnits()
            },
        label = {Text("Enter Value", color = Color.White, fontFamily = fontFamily) })
        Spacer(modifier = Modifier.height(32.dp))

        Row {
            //Input Box
            Box{
                //Input Button
                Button(onClick = { iExpanded = true}) {
                    Text(inputUnit)
                    Spacer(modifier = Modifier.width(8.dp))
                    androidx.compose.material3.Icon(Icons.Default.KeyboardArrowDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded,
                    onDismissRequest = { iExpanded = false },
                    Modifier.padding(12.dp)) {
                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {
                         iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Kilometers")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Kilometers"
                            conversionFactor.value = 1000.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Inches")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Inches"
                            conversionFactor.value = 0.0254
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Miles")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Miles"
                            conversionFactor.value = 1609.344
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Milligram")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milligram"
                            conversionFactor.value = 0.000001
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Kilograms")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Kilograms"
                            conversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Grams")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Grams"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Pounds")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Pounds"
                            conversionFactor.value = 0.4535
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Ounces")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Ounces"
                            conversionFactor.value = 0.02834
                            convertUnits()
                        })


                }
            }
            androidx.compose.material3.Icon(Icons.Default.ArrowForward,
                contentDescription = "Arrow Right",
                modifier = Modifier.padding(12.dp),
                colorResource(R.color.pastel_blue))

            //Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box{
                //Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Spacer(modifier = Modifier.width(8.dp))
                    androidx.compose.material3.Icon(Icons.Default.KeyboardArrowDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded,
                    onDismissRequest = {oExpanded = false },
                    Modifier.padding(12.dp)) {
                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Kilometers")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Kilometers"
                            oConversionFactor.value = 1000.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Inches")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Inches"
                            oConversionFactor.value = 0.0254
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Miles")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Miles"
                            oConversionFactor.value = 1609.344
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Milligrams")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milligrams"
                            oConversionFactor.value = 0.000001
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Grams")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Grams"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Kilograms")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Kilograms"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Pounds")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Pounds"
                            oConversionFactor.value = 0.4535
                            convertUnits()
                        })
                    DropdownMenuItem(text = {Text("Ounces")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Ounces"
                            oConversionFactor.value =  0.02834
                            convertUnits()
                        })

                }
            }
            /* val context = LocalContext.current
            Button(onClick = { Toast.makeText(context,
                "Thanks for clicking",
                Toast.LENGTH_LONG).show()}) {
                Text("MyButton")
            } */
        }



        //Spacer(modifier = Modifier.height(64.dp))
        Spacer(modifier =  Modifier.padding(12.dp))

        //Result Text
        Text("Result:",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.width(256.dp),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily)

        if((inputUnit == "Milligrams" || inputUnit == "Kilograms" || inputUnit == "Grams"
                    || inputUnit == "Pounds" || inputUnit == "Ounces")
                && (outputUnit == "Kilometers" || outputUnit == "Millimeters" || outputUnit == "Centimeters"
            || outputUnit == "Feet" || outputUnit == "Miles" || outputUnit == "Inches" || outputUnit == "Meters")){
                Text("Unable to convert weight units into distance units")
        }else if ((inputUnit == "Kilometers" || inputUnit == "Millimeters" || inputUnit == "Centimeters"
            || inputUnit == "Feet" || inputUnit == "Miles" || inputUnit == "Inches") && (outputUnit == "Milligrams"
                    || outputUnit == "Grams" || outputUnit == "Kilograms" || outputUnit == "Pounds"
                    || outputUnit == "Ounces")){
            Text("Unable to convert distance units into weight units")
        }
        else{
            Text("$outputValue $outputUnit",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = fontFamily,
                textAlign = TextAlign.Center)
        }




    }


}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}