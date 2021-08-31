package com.bluebus.dao

import com.bluebus.models.buses.ACBus
import com.bluebus.models.buses.Bus
import com.bluebus.models.buses.NormalBus
import com.bluebus.models.buses.SleeperBus

object FakeBusDB {
    val buses: MutableList<Bus> = mutableListOf(
        ACBus("tirunelveli", "chennai", 1, seatCount = 10, bedCount = 10),
        NormalBus(start = "tirunelveli", destination = "coimbatore", id = 2, seatCount = 20),
        SleeperBus(start = "tirunelveli", destination = "chennai", id= 3, seatCount = 10, bedCount = 10)
    )
}