package com.demo

class Kid(private val mother: Mother) {
    var money = 0
        private set

    fun wantMoney() {
        money += mother.giveMoney()
    }

    fun wantMoneyInfoMother() {
        mother.inform(money)
        money += mother.giveMoney()
    }
}