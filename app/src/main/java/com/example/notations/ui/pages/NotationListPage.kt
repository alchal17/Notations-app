package com.example.notations.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notations.models.Notation
import com.example.notations.ui.elements.DeletionDialog
import com.example.notations.ui.elements.NotationCard
import com.example.notations.viewmodels.NotationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotationListPage(
    viewModel: NotationViewModel = koinViewModel(),
    onAddClick: () -> Unit,
    onEditClick: (Long) -> Unit
) {
    val notations by viewModel.notations.collectAsState()
    var notationToBeDeleted by remember { mutableStateOf<Notation?>(null) }

    notationToBeDeleted?.let {
        DeletionDialog(
            onDismissRequest = {
                notationToBeDeleted = null
            },
            onConfirmation = {
                viewModel.deleteNotation(it)
                notationToBeDeleted = null
            },
            dialogText = "Are you sure you want to delete \"${it.name}\" notation?"
        )
    }

    Scaffold(containerColor = Color.Black, floatingActionButton = {
        FloatingActionButton(
            containerColor = FloatingActionButtonDefaults.containerColor.copy(alpha = 0f),
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            onClick = onAddClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                modifier = Modifier.size(40.dp),
                contentDescription = "Create a notation", tint = Color.White
            )
        }
    }) { paddingValues ->
        LazyVerticalGrid(
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = 5.dp,
                end = 5.dp
            ),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(notations) {
                NotationCard(
                    notation = it,
                    onEditClick = onEditClick,
                    onDeleteClick = { notationToBeDeleted = it })
            }
        }
    }
}

