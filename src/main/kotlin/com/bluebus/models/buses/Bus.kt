package com.bluebus.models.buses

interface Bus {
    val start: String
    val destination: String
    val id: Int
    var seatCount: Int
}