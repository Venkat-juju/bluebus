package com.bluebus.dao

import com.bluebus.models.buses.ACBus
import com.bluebus.models.buses.Bus
import com.bluebus.models.buses.NormalBus
import com.bluebus.models.buses.SleeperBus
import com.bluebus.models.options.Options

import com.bluebus.utils.Constants
import com.sun.org.apache.bcel.internal.Const


class BusDao {

    /**
     * create and a new bus in the database with the given parameters
     * @return busId
     */
    fun addBus(start: String, destination: String, type: String, seatCount: Int, bedCount: Int): Int {
        var id = FakeBusDB.buses.map{ it.id }.maxOrNull() ?: 0
        id ++

        FakeBusDB.buses.add(
            when(type) {
                Constants.BUS_TYPE_AC -> ACBus(start, destination, id, seatCount, bedCount)
                Constants.BUS_TYPE_NORMAL -> NormalBus(start, destination, id, seatCount)
                Constants.BUS_TYPE_SLEEPER -> SleeperBus(start, destination, id, seatCount, bedCount)
                else -> return -1
            }
        )

        return id
    }

    /**
     * removed the bus with the given id from the database
     */
    fun removeBus(id: Int) {
        FakeBusDB.buses.removeIf { it.id == id }
    }

    /**
     * returns the bus list with the given start and end points
     */
    fun getBuses(start: String, destination: String): List<Bus>  {
        return FakeBusDB.buses.filter {
            it.start == start && it.destination == destination
        }
    }

    fun getBus(id: Int): Bus? {
        return FakeBusDB.buses.find{ id == it.id }
    }

    /**
     * reduces the available seat counts with the given number in the given bus
     */
    fun reduceSeatingCount(type: String, busId: Int, numberOfSeats: Int): String {
        val bus = FakeBusDB.buses.find { it.id == busId } ?: return Constants.BUS_NOT_FOUND

        if (type == Constants.SEAT_TICKET) {
            if (bus.seatCount < numberOfSeats) {
                return Constants.NOT_ENOUGH_SEATS
            } else {
                bus.seatCount -= numberOfSeats
            }
        } else if (type == Constants.BED_TICKET) {
            if (bus is SleeperBus) {
                if (bus.bedCount < numberOfSeats) {
                    return Constants.NOT_ENOUGH_BEDS
                } else {
                    bus.bedCount -=  numberOfSeats
                }
            } else if (bus is ACBus) {
                if (bus.bedCount < numberOfSeats) {
                    return Constants.NOT_ENOUGH_BEDS
                } else {
                    bus.bedCount -= numberOfSeats
                }
            } else {
                return Constants.NO_BEDS
            }
        } else {
            return Constants.INVALID_SEATING_TYPE
        }

        return Constants.TICKET_REDUCED
    }

    fun getAllBuses(): List<Bus> {
        return FakeBusDB.buses
    }
}