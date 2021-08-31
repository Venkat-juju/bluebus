package com.bluebus.models.tickets

data class TicketImpl(override val id:Int, override val userId: Int, override val busId: Int, override val seatCount: Int,
                      override val bedCount: Int): Ticket