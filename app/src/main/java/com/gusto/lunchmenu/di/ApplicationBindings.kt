package com.gusto.lunchmenu.di

import com.gusto.lunchmenu.data.LunchMenuRepository
import com.gusto.lunchmenu.domain.repository.LunchMenuRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LunchMenuBindings {

    @Binds
    abstract fun bindLunchMenuRepository(binds: LunchMenuRepository): LunchMenuRepositoryInterface
}