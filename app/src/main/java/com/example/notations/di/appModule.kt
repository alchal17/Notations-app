package com.example.notations.di

import android.app.Application
import androidx.room.Room
import com.example.notations.database.daos.NotationDao
import com.example.notations.database.daos.NotationDatabase
import com.example.notations.datetime_formaters.DateTimeFormater
import com.example.notations.datetime_formaters.MainDateTimeFormater
import com.example.notations.viewmodels.NotationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(get<Application>(), NotationDatabase::class.java, "notation_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<NotationDao> { get<NotationDatabase>().dao }
    single<DateTimeFormater> { MainDateTimeFormater() }
    viewModel { NotationViewModel(get()) }
}