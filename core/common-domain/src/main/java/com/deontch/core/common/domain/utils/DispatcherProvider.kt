package com.deontch.core.common.domain.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val ui: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher
}
