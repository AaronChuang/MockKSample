package com.demo

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AdderTest {
    @Test
    fun wantMoney() {
        val spy = spyk(Adder())
        Assertions.assertEquals(9, spy.add(4, 5))
        every { spy.magnify(any()) } answers { firstArg<Int>() * 2 }
        Assertions.assertEquals(14, spy.add(4, 5))
        verify { spy.add(4, 5) }
        verify { spy.magnify(5) }
    }
}