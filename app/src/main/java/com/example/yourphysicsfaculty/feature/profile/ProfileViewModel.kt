package com.example.yourphysicsfaculty.feature.profile

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.yourphysicsfaculty.core.data.network.AuthorizationRequest
import com.example.yourphysicsfaculty.core.data.network.YPFApiService
import com.example.yourphysicsfaculty.core.data.network.retrofitErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val networkService: YPFApiService
): ViewModel() {

    val response = mutableStateOf("")
    private val _profileUiState = MutableStateFlow(ProfileUiState("", ""))
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    fun onPasswordChange(password: String) {
        _profileUiState.update {
            it.copy(password = password)
        }
    }

    fun onEmailChange(email: String) {
        _profileUiState.update {
            it.copy(email = email)
        }
    }

    fun singIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val report = retrofitErrorHandler(networkService.signIn(AuthorizationRequest(email, password)))
                response.value = "Success!"
            } catch (e: Exception) {
                response.value = "Something went wrong"
            }
        }
    }

    fun singUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val report = retrofitErrorHandler(networkService.signUp(AuthorizationRequest(email, password)))
                response.value = "Success!"
            } catch (e: Exception) {
                response.value = "Something went wrong"
            }
        }
    }
}



val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")
data class ProfileUiState(
    var email: String,
    var password: String
)

class Constants {
    companion object {
        val API_KEY = "AIzaSyDibn72sUDfbrvR0Z4Ul7lGMwXNeU77wNg"
        val DATA_STORE_PREFERENCES_NAME = "AUTH_STATE_PREFERENCE"
        val AUTH_STATE = "AUTH_STATE"

        val SCOPE_PROFILE = "profile"
        val SCOPE_EMAIL = "email"
        val SCOPE_OPENID = "openid"
        val SCOPE_BOOKS = "https://www.googleapis.com/auth/books"

        val DATA_PICTURE = "picture"
        val DATA_NAME = "name"
        val DATA_ID = "id"

        val CLIENT_ID =  "876615085299-imqvvp89d1lje0182hsben6muk5689jb.apps.googleusercontent.com"
        val CODE_VERIFIER_CHALLENGE_METHOD = "S256"
        val MESSAGE_DIGEST_ALGORITHM = "SHA-256"

        val URL_AUTHORIZATION = "https://accounts.google.com/o/oauth2/v2/auth"
        val URL_TOKEN_EXCHANGE = "https://www.googleapis.com/oauth2/v4/token"
        val URL_AUTH_REDIRECT = "com.example.mylibrary:/oauth2redirect"
        val URL_API_CALL = "https://www.googleapis.com/books/v1/mylibrary/bookshelves"
        val URL_LOGOUT = "https://accounts.google.com/o/oauth2/revoke?token="

        val URL_LOGOUT_REDIRECT = "com.example.mylibrary:/logout"
    }
}