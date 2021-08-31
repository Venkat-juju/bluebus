package com.bluebus.controllers

import com.bluebus.dao.TicketDao
import com.bluebus.models.tickets.Ticket

class TicketController {

    private val ticketsDao = TicketDao()

    fun getTicketsWithUserId(personalId: Int): List<Ticket> {
        return ticketsDao.getTicketWithUserId(personalId)
    }

    fun getAllTicket(): List<Ticket> {
        return ticketsDao.getAllTickets()
    }

    fun getTicket(busId: Int, userId: Int, seatCount: Int, bedCount: Int): Int {
        return ticketsDao.getTicket(busId, userId, seatCount, bedCount)
    }
}