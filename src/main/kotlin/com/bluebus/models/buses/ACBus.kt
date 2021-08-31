package com.bluebus.models.buses

data class ACBus(override val start: String, override val destination: String , override val id: Int, override var seatCount: Int, var bedCount: Int): Bus
