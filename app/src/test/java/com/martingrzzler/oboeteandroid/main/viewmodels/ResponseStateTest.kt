package com.martingrzzler.oboeteandroid.main.viewmodels

import org.junit.Assert.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import java.util.*

class ResponseStateTest {

    @ParameterizedTest
    @EnumSource
    fun testApiStateEnumsAreIncluded(responseState: ResponseState) {
        assertTrue(EnumSet.of(ResponseState.LOADING, ResponseState.ERROR, ResponseState.DONE).contains(responseState))
    }

    @ParameterizedTest
    @EnumSource
    fun responseStatusIsNotNull(responseState: ResponseState) {
        assertNotNull(responseState)
    }


}