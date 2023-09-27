package com.example.earningappkotlin.models

class HistoryModel {

    var timeDate : String =""
    var coin : String=""
    var isWithDrawal :Boolean = false

    constructor()
    constructor(timeDate: String, coin: String, isWithDrawal: Boolean) {
        this.timeDate = timeDate
        this.coin = coin
        this.isWithDrawal = isWithDrawal
    }

}