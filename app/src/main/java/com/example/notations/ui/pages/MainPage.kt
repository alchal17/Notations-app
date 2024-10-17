package com.example.notations.ui.pages

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.notations.models.Notation
import com.example.notations.viewmodels.NotationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainPage(viewModel: NotationViewModel = koinViewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.NotationListPage
    ) {
        composable<Routes.NotationListPage> {
            NotationListPage(
                onAddClick = {
                    navController.navigate(
                        Routes.CreationPage
                    )
                },
                onEditClick = { navController.navigate(Routes.EditionPage(it)) }
            )
        }
        composable<Routes.CreationPage> { CreationPage(navController = navController) }
        composable<Routes.EditionPage> { it ->
            val idArg = it.toRoute<Routes.EditionPage>().id
            val context = LocalContext.current
            var notation by remember { mutableStateOf<Notation?>(null) }

            LaunchedEffect(Unit) {
                viewModel.findNotationById(idArg) { result ->
                    notation = result
                    if (result == null) {
                        Toast.makeText(context, "Notation not found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            notation?.let {
                EditionPage(notation = it, navController = navController)
            }
        }
    }

}