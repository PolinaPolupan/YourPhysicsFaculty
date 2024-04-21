/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.yourphysicsfaculty.core.data.di

import android.content.Context
import androidx.room.Room
import com.example.yourphysicsfaculty.core.data.DefaultUserNewsResourceRepository
import com.example.yourphysicsfaculty.core.data.UserNewsResourceRepository
import com.example.yourphysicsfaculty.core.data.local.NewsResourceDatabase
import com.example.yourphysicsfaculty.core.data.local.NewsResourcesDao
import com.example.yourphysicsfaculty.core.data.network.NetworkDataSource
import com.example.yourphysicsfaculty.core.data.network.YPFApiService
import com.example.yourphysicsfaculty.core.data.network.fake.FakeYPFNetworkDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(repository: DefaultUserNewsResourceRepository): UserNewsResourceRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: FakeYPFNetworkDataSource): NetworkDataSource
}
@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideYPFDatabase(
        @ApplicationContext context: Context,
    ): NewsResourceDatabase = Room.databaseBuilder(
        context,
        NewsResourceDatabase::class.java,
        "ypf-database.db",
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providesNewsResourceDao(
        database: NewsResourceDatabase,
    ): NewsResourcesDao = database.NewsResourcesDao()

}

const val USER_DATA_NAME = "user_data"
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideAnalyticsService(
        // Potential dependencies of this type
    ): YPFApiService  {
        val baseUrl = "https://api.test.profcomff.com"
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(YPFApiService::class.java)
    }
}