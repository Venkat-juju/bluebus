package com.bluebus.models.tickets

interface Ticket {
    val id: Int
    val userId: Int
    val busId: Int
    val seatCount: Int
    val bedCount: Int
}