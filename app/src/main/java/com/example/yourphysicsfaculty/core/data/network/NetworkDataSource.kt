package com.example.yourphysicsfaculty.core.data.network

/**
 * Main entry point for accessing news data from the network.
 */
interface NetworkDataSource {

    suspend fun getNewsResources(): List<NetworkNewsResource>
}