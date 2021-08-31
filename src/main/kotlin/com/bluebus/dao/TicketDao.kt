package com.bluebus.dao

import com.bluebus.models.tickets.Ticket
import com.bluebus.models.tickets.TicketImpl

class TicketDao {

    private val tickets = FakeTicketDB.tickets

    private fun getNextTicketId():Int {
        var maxId =  FakeTicketDB.tickets.map{ it.id }.maxOrNull() ?: 0
        return maxId++
    }

    fun getTicket(busId: Int, userId: Int,seatCount: Int, bedCount: Int): Int {
        val ticketId = getNextTicketId()
        val ticket = TicketImpl(ticketId, userId, busId, seatCount, bedCount)
        tickets.add(ticket)
        return ticketId;
    }

    fun getTicketWithUserId(personalId: Int): List<Ticket> {
        return tickets.filter{ it.userId == personalId }
    }

    fun getAllTickets(): List<Ticket> {
        return tickets
    }
}