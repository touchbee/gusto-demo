package com.gusto.lunchmenu.di

import com.gusto.lunchmenu.domain.usecase.GetCurrentWeekDayUseCase
import com.gusto.lunchmenu.domain.usecase.GetCurrentWeekUseCase
import com.gusto.lunchmenu.domain.usecase.GetFormattedTitleDateUseCase
import com.gusto.lunchmenu.domain.usecase.GetMenuForWeekUseCase
import com.gusto.lunchmenu.domain.usecase.GetNextWeekUseCase
import com.gusto.lunchmenu.domain.usecase.GetPreviousWeekUseCase
import com.gusto.lunchmenu.domain.usecase.GetWeekUseCase
import com.gusto.lunchmenu.data.LunchMenuDataSource
import com.gusto.lunchmenu.data.LunchMenuRepository
import com.gusto.lunchmenu.domain.usecase.GetWeekStartDateUseCase
import com.gusto.lunchmenu.domain.usecase.IsThisWeekUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LunchMenuModule {

    @Provides
    fun provideLunchMenuDataSource(): LunchMenuDataSource {
        return LunchMenuDataSource()
    }

    @Provides
    fun provideGetWeekUseCase(): GetWeekUseCase {
        return GetWeekUseCase()
    }

    @Provides
    fun provideGetCurrentWeekUseCase(
        getWeekUseCase: GetWeekUseCase
    ): GetCurrentWeekUseCase {
        return GetCurrentWeekUseCase(
            getWeekUseCase
        )
    }

    @Provides
    fun provideGetMenuForWeekUseCase(): GetMenuForWeekUseCase {
        return GetMenuForWeekUseCase()
    }

    @Provides
    fun provideGetFormattedTitleDateUseCase(): GetFormattedTitleDateUseCase {
        return GetFormattedTitleDateUseCase()
    }

    @Provides
    fun provideGetPreviousWeekUseCase(): GetPreviousWeekUseCase {
        return GetPreviousWeekUseCase()
    }

    @Provides
    fun provideGetNextWeekUseCase(): GetNextWeekUseCase {
        return GetNextWeekUseCase()
    }

    @Provides
    fun provideGetCurrentWeekDayUseCase(): GetCurrentWeekDayUseCase {
        return GetCurrentWeekDayUseCase()
    }

    @Provides
    fun provideIsThisWeekUseCase(
        getWeekUseCase: GetWeekUseCase
    ): IsThisWeekUseCase {
        return IsThisWeekUseCase(
            getWeekUseCase
        )
    }

    @Provides
    fun provideGetWeekStartDateUseCase(): GetWeekStartDateUseCase {
        return GetWeekStartDateUseCase()
    }
}