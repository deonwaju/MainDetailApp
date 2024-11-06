package com.deontch.core.common.test

import com.deontch.core.common.domain.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatcherProviderImpl @OptIn(ExperimentalCoroutinesApi::class) constructor(
    coroutineDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
) : DispatcherProvider {

    override val ui: CoroutineDispatcher = coroutineDispatcher
    override val io: CoroutineDispatcher = coroutineDispatcher
    override val default: CoroutineDispatcher = coroutineDispatcher
}
