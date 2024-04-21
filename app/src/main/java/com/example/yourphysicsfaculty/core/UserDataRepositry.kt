package com.example.yourphysicsfaculty.core


import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.yourphysicsfaculty.feature.profile.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

data class UserData(
    val id: Int?,
    val email: String?,
    val displayName: String?,
    val profilePictureUri: String?
)

@Singleton
class UserDataRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    val userData: Flow<UserData> = context.dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            UserData(
                id = preferences[ID],
                email = preferences[EMAIL],
                displayName = preferences[DISPLAY_NAME],
                profilePictureUri = preferences[PROFILE_PICTURE_URI]
            )
        }
    private companion object {
        val ID = intPreferencesKey("id")
        val DISPLAY_NAME = stringPreferencesKey("display_name")
        val PROFILE_PICTURE_URI = stringPreferencesKey("data_picture")
        val EMAIL = stringPreferencesKey("email")
        const val TAG = "Preferences error"
    }
}

