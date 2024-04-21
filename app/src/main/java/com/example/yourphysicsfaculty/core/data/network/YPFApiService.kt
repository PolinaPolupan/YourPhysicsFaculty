package com.example.yourphysicsfaculty.core.data.network

import kotlinx.serialization.Serializable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


data class AuthorizationRequest(
    var email: String,
    var password: String,
    var scopes: List<String> = listOf(), // Add necessary scopes here
    var session_name: String = "string" // doesn't matter
)

@Serializable
data class AuthorizationResponse(
    val token: String,
    val expires: String,
    val id: Int,
    val user_id: Int,
    val session_scopes: List<String>
)

data class AuthorizationErrorResponse(
    var email: String,
    var password: String,
    var scopes: List<String> = listOf(),
    var session_name: String = "string"
)

interface YPFApiService {

    @POST("auth/email/login")
    suspend fun signIn(
        @Body raw: AuthorizationRequest
    ) : Response<AuthorizationResponse>

    @POST("auth/email/registration")
    suspend fun signUp(
        @Body raw: AuthorizationRequest
    ) :  Response<AuthorizationResponse>
}

fun <T> retrofitErrorHandler(res: Response<T>): T {
    if (res.isSuccessful) {
        return res.body()!!
    } else {
        val errMsg = res.errorBody()?.string()?.let {
            JSONObject(it).getString("msg")
        } ?: run {
            res.code().toString()
        }

        throw Exception(errMsg)
    }
}