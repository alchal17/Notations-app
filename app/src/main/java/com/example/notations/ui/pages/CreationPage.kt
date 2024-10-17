package com.example.notations.ui.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notations.R
import com.example.notations.datetime_formaters.DateTimeFormater
import com.example.notations.models.Notation
import com.example.notations.ui.elements.DatePickerModal
import com.example.notations.ui.elements.TimePickerModal
import com.example.notations.viewmodels.NotationViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationPage(
    viewModel: NotationViewModel = koinViewModel(),
    dateTimeFormater: DateTimeFormater = koinInject(),
    navController: NavController,
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedTimeMinutes by remember { mutableStateOf<Int?>(null) }

    var isDateDialogVisible by remember { mutableStateOf(false) }
    var isTimePickerModalVisible by remember { mutableStateOf(false) }

    val textFieldColor = TextFieldDefaults.colors(
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White,
        focusedContainerColor = Color.Black,
        unfocusedContainerColor = Color.Black,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
    )
    val textFieldShape = RoundedCornerShape(0.dp)

    val context = LocalContext.current

    if (isDateDialogVisible) {
        DatePickerModal(onDismiss = {
            isDateDialogVisible = false
        }, onDateSelected = {
            selectedDate = it
            isDateDialogVisible = false
        },
            initialSelectedDateMillis = selectedDate
        )
    }

    if (isTimePickerModalVisible) {
        TimePickerModal(
            onDismiss = { isTimePickerModalVisible = false },
            onConfirm = {
                selectedTimeMinutes = it.hour * 60 + it.minute
                isTimePickerModalVisible = false
            },
            initialTimeInMinutes = selectedTimeMinutes
        )
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(bottom = 10.dp, top = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(),
        ) {
            TextField(
                shape = textFieldShape,
                colors = textFieldColor,
                label = { Text("Set name") },
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableText(
                    text = AnnotatedString(
                        "Select date",
                        spanStyle = SpanStyle(color = Color.White)
                    )
                ) {
                    isDateDialogVisible = true
                }
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                        .width(1.dp)
                        .background(color = Color.White)
                )
                ClickableText(
                    text = AnnotatedString(
                        "Select time",
                        spanStyle = SpanStyle(color = Color.White)
                    )
                ) {
                    isTimePickerModalVisible = true
                }
            }
            TextField(
                shape = textFieldShape,
                colors = textFieldColor,
                label = { Text("Set description") },
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { isDateDialogVisible = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Text(
                            if (selectedDate != null) dateTimeFormater.formatDateToString(
                                selectedDate!!
                            ) else "Set date"
                        )
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Set date"
                        )
                    }
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { isTimePickerModalVisible = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Text(
                            if (selectedTimeMinutes != null) {
                                "${(selectedTimeMinutes ?: 0) / 60}:${(selectedTimeMinutes ?: 0) % 60}"
                            } else "Set time"
                        )
                        Icon(
                            painter = painterResource(R.drawable.baseline_access_time_24),
                            contentDescription = "Set time"
                        )
                    }
                }
            }
            Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Black), onClick = {
                if (listOf(name, description).contains("") || listOf(
                        selectedDate,
                        selectedTimeMinutes
                    ).contains(null)
                ) {
                    Toast.makeText(context, "Please fill in all the fields", Toast.LENGTH_LONG)
                        .show()
                } else {
                    selectedDate?.let { date ->
                        selectedTimeMinutes?.let { time ->
                            viewModel.addNotation(
                                Notation(
                                    name = name,
                                    description = description,
                                    createdAt = Date().time / 1000,
                                    setTo = dateTimeFormater.combineDateAndTime(date, time).time
                                )
                            )
                            navController.navigateUp()
                        }
                    }
                }
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) { Text("Create notation") }
        }
    }
}