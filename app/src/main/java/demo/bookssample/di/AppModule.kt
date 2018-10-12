/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.bookssample.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import demo.bookssample.api.BooksService
import demo.bookssample.db.BooksDao
import demo.bookssample.db.BooksDb
import demo.bookssample.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module()
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.itbook.store/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
    }

    @Singleton
    @Provides
    fun provideBooksService(retrofit: Retrofit): BooksService {
        return retrofit.create(BooksService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): BooksDb {
        return Room
                .databaseBuilder(app, BooksDb::class.java, "Books.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideBooksDao(db: BooksDb): BooksDao {
        return db.bookDao()
    }
//
//    @Singleton
//    @Provides
//    fun provideRepoDao(db: GithubDb): RepoDao {
//        return db.repoDao()
//    }
}