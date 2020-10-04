package com.demo

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class KidTest {

    @Test
    fun wantMoney() {

        // mock class
        val mother = mockk<Mother>()
        val kid = Kid(mother)
        // Mockito's when().thenReturn()
        every {
            mother.giveMoney()
        } returns 30


        kid.wantMoney()
        assertEquals(30, kid.money)
    }

    @Test
    fun wantMoneyWithVerify() {
        // When
        val mother = mockk<Mother>()
        val kid = Kid(mother)
        every { mother.giveMoney() } returns 30
        // 需定義每個方法, 否則報錯
        every { mother.inform(any()) } just Runs

        kid.wantMoneyInfoMother()

        // 驗證方法有沒有被呼叫到
        verify { mother.inform(any()) }
        assertEquals(30, kid.money)
    }
}