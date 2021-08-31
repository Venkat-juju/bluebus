package com.bluebus.dao

import com.bluebus.models.tickets.Ticket

object FakeTicketDB {
    val tickets: MutableList<Ticket> = mutableListOf()
}