package com.example.notations.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notations.datetime_formaters.DateTimeFormater
import com.example.notations.models.Notation
import org.koin.compose.koinInject

@Composable
fun NotationCard(notation: Notation, onDeleteClick: () -> Unit, onEditClick: (id: Long) -> Unit) {
    val dateTimeFormater: DateTimeFormater = koinInject()

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                notation.name,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                maxLines = 2
            )
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = { onEditClick(notation.id) }) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit notation"
                    )
                }
                IconButton(onClick = { onDeleteClick() }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete notation"
                    )
                }
            }
        }
        Text(notation.description, modifier = Modifier.weight(1f))
        Text(
            dateTimeFormater.formatDateToString(notation.setTo), modifier = Modifier
                .align(Alignment.End)
                .padding(3.dp)
                .background(
                    color = Color.Green.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }
}