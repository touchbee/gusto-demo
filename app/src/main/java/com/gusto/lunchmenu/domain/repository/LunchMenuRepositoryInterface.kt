package com.gusto.lunchmenu.domain.repository

import com.gusto.lunchmenu.data.model.LunchMenuSchedule
import com.gusto.lunchmenu.data.common.DataState
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for [LunchMenuSchedule] repository
 */
interface LunchMenuRepositoryInterface {

    /**
     * Observable current menu schedule
     */
    val menuSchedule: StateFlow<DataState<LunchMenuSchedule>>

    /**
     * Refreshes the data
     */
    fun refresh()
}

