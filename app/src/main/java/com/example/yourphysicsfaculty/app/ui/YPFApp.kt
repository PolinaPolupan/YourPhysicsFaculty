package com.example.yourphysicsfaculty.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.yourphysicsfaculty.R
import com.example.yourphysicsfaculty.app.navigation.TopLevelDestination
import com.example.yourphysicsfaculty.app.navigation.YPFNavGraph
import com.example.yourphysicsfaculty.core.designSystem.component.YPFTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YPFApp(
   appState: YPFAppState = rememberYPFAppState()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val destination = appState.currentTopLevelDestination
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            if (destination != null) {
                YPFTopAppBar(titleRes = destination.titleTextId)
            }
        },
        bottomBar = {
            YPFBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        }
    ) { innerPadding ->
        YPFNavGraph(
            appState = appState,
            onShowSnackbar = { message, action ->
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = action,
                    duration = SnackbarDuration.Short,
                ) == SnackbarResult.ActionPerformed
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun YPFBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    BottomAppBar {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                if (selected) {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null
                    )
                }
            },
                label = { Text(stringResource(destination.iconTextId)) },
                modifier = modifier
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
