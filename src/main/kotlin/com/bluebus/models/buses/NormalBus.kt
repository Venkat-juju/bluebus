package com.bluebus.models.buses

data class NormalBus(override val start: String, override val destination: String , override val id: Int, override var seatCount: Int): Bus
