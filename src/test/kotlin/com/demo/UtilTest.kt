package com.demo

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UtilTest {

    @Test
    fun ok() {
        // Given
        val util = Util()
        mockkStatic(UtilKotlin::class)
        every { UtilKotlin.ok() } returns "Test"

        util.ok()

        verify { UtilKotlin.ok() }

        assertEquals("Test", UtilKotlin.ok())
    }
}