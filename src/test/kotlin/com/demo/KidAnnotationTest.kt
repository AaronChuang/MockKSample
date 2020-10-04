package com.demo

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class KidAnnotationTest {

    @MockK
    lateinit var mother: Mother

    lateinit var kid: Kid

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        kid = Kid(mother)
    }

    @Test
    fun wantMoney() {
        every { mother.giveMoney() } returns 30
        kid.wantMoney()
        assertEquals(30, kid.money)
    }
}