package com.example.yumecafeteria.internal

import androidx.room.Room
import com.example.yumecafeteria.data.local.database.AppDatabase
import com.example.yumecafeteria.domain.MenuRepository
import com.example.yumecafeteria.presentation.menu.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val DATABASE_NAME = "yumeCafeteria.db"

val appModule = module {

    //database
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().productOrderDao() }
    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().ordersDao() }

    // repository
    factory { MenuRepository() }

    // viewmodels
    viewModel { MenuViewModel(get()) }

}