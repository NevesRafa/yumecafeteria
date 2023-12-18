package com.example.yumecafeteria.internal

import androidx.room.Room
import com.example.yumecafeteria.data.local.database.AppDatabase
import com.example.yumecafeteria.domain.CartRepository
import com.example.yumecafeteria.domain.MenuRepository
import com.example.yumecafeteria.presentation.cart.CartViewModel
import com.example.yumecafeteria.presentation.description.DescriptionViewModel
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
    single { MenuRepository(get()) }
    single { CartRepository(get()) }

    // viewmodels
    viewModel { MenuViewModel(get(), get()) }
    viewModel { DescriptionViewModel(get()) }
    viewModel { CartViewModel(get()) }

}