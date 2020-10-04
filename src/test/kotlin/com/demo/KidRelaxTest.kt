package com.demo

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

//Relaxed 用途：該物件所有的方法都不需要指定
//RelaxUnitFun 用途：不需指定無回傳值的方法，有回傳值的方法仍須指定
internal class KidRelaxTest {

//    @RelaxedMockK
//    lateinit var mother2: Mother

//    @MockK
//    lateinit var mother3: Mother

    @BeforeEach
    fun setUp() {
          // 設定全域
//        MockKAnnotations.init(this, relaxed = true)
//        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun wantMoneyWithVerify() {
        // When
        val mother = mockk<Mother>(relaxed = true)
        val kid = Kid(mother)
        every { mother.giveMoney() } returns 30

        kid.wantMoneyInfoMother()

        // 驗證方法有沒有被呼叫到
        verify { mother.inform(any()) }
        assertEquals(30, kid.money)

        // 驗證呼叫次數0次
//        verify(exactly = 0) { mother.inform(any()) }
        // 驗證呼叫10次
//        verify(exactly = 10) { mother.inform(any()) }
        // 驗證mother.inform() 跟 mother.giveMoney()呼叫1次
        verify {
            mother.inform(any())
            mother.giveMoney()
        }

        // 驗證方法被呼叫的順序性
        //verifySequence 規定 inform() 的下一個執行的方法一定要是 giveMoney()，否則測試失敗。
        //verifyOrder 條件比較寬鬆，inform() 只要在 giveMoney() 之前執行即可，功能跟 Mockito 的 inOrder 一樣。
        verifySequence {
            mother.inform(any())
            mother.giveMoney()
        }
        verifyOrder {
            mother.inform(any())
            mother.giveMoney()
        }

        // Given
        val slot = slot<Int>()
        every { mother.inform(capture(slot)) } just Runs
        kid.wantMoneyInfoMother()
        assertEquals(30, slot.captured)
    }
}