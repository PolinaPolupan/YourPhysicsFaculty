package com.example.yourphysicsfaculty.feature.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yourphysicsfaculty.R
import com.example.yourphysicsfaculty.core.designSystem.component.YPFBackground
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme

@Composable
fun ProfileScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.profileUiState.collectAsStateWithLifecycle()
    ProfileScreenContent(
        response = viewModel.response.value,
        password = uiState.value.password,
        email = uiState.value.email,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignIn = viewModel::singIn,
        onSignUp = viewModel::singUp,
        onShowSnackbar = onShowSnackbar
    )
}

@Composable
fun ProfileScreenContent(
    response: String,
    email: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSignUp: (String, String) -> Unit,
    onSignIn: (String, String) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    var shouldShowSnackBar by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(shouldShowSnackBar) {
        if (shouldShowSnackBar) {
           val snackBarResult = onShowSnackbar(response, "Dismiss")
        }
    }

    YPFBackground(contentAlignment = Alignment.Center, alpha = 0.2f) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_to_account),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
            SearchTextField(
                onSearchQueryChanged = onEmailChange,
                searchQuery = email,
                onSearchTriggered = {},
                text = R.string.enter_email,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            SearchTextField(
                onSearchQueryChanged = onPasswordChange,
                searchQuery = password,
                visualTransformation = PasswordVisualTransformation(),
                onSearchTriggered = {},
                text = R.string.enter_password,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Button(
                onClick = {
                    onSignIn(email, password)
                    shouldShowSnackBar =! shouldShowSnackBar
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.sign_in), style = MaterialTheme.typography.titleMedium)
            }
            Button(
                onClick = {
                    onSignUp(email, password)
                    shouldShowSnackBar =! shouldShowSnackBar
                          },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.sign_up), style = MaterialTheme.typography.titleMedium)
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.forgot_password), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            }
            Text(text = response)
        }
    }
}

@Composable
private fun SearchTextField(
    @StringRes text: Int,
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String,
    onSearchTriggered: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
    }

    OutlinedTextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
            disabledIndicatorColor = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.9f),
            focusedContainerColor = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.8f),
            unfocusedContainerColor = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.5f),
            disabledContainerColor = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.5f)
        ),
        placeholder = { Text(text = stringResource(text))},
        onValueChange = {
            if ("\n" !in it) onSearchQueryChanged(it)
        },
        visualTransformation = visualTransformation,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchExplicitlyTriggered()
                    true
                } else {
                    false
                }
            }
            .testTag("searchTextField"),
        shape = RoundedCornerShape(8.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
}

@Preview
@Composable
fun ProfileScreenContentPreview() {
    YPFTheme {
        ProfileScreenContent(
            onSignIn = {it, u -> },
            onSignUp = {it, u -> },
            onPasswordChange = {},
            onEmailChange = {},
            email = "hjvghjvhj",
            password = "vgyuf",
            response = "huh",
            onShowSnackbar = {it, i -> return@ProfileScreenContent false }
        )
    }
}