package com.bluebus.controllers

import com.bluebus.dao.BusDao
import com.bluebus.dao.OptionsDao
import com.bluebus.models.buses.ACBus
import com.bluebus.models.buses.Bus
import com.bluebus.models.buses.NormalBus
import com.bluebus.models.buses.SleeperBus
import com.bluebus.models.options.Options
import com.bluebus.utils.Constants

class BusController {

    private val options = OptionsDao()
    private val busDao = BusDao()
    private val ticketController = TicketController()

    fun getBusTypes(): List<Options> {
        return options.getBusTypes()
    }

    fun addBus(start: String, end: String, type: String, seatCount:Int = 0, bedCount: Int = 0): Int {
        return busDao.addBus(start, end, type, seatCount, bedCount)
    }

    fun getBusesFor(start: String, destination: String): List<Bus> {
        return busDao.getBuses(start, destination)
    }

    fun getBusType(busId: Int):String? {

        val bus = busDao.getBus(busId)
        if (bus == null) {
            return null
        } else {
            if (bus is ACBus) {
                return Constants.BUS_TYPE_AC
            } else if (bus is SleeperBus) {
                return Constants.BUS_TYPE_SLEEPER
            } else if (bus is NormalBus){
                return Constants.BUS_TYPE_NORMAL
            }
        }

        return null
    }

    fun bookBus(selectedBusId: Int, seatCount: Int, bedCount: Int, userId: String): String {
        val busType = getBusType(selectedBusId) ?: return Constants.BUS_NOT_FOUND

        if (!busType.equals(Constants.BUS_TYPE_NORMAL)) {
            val bedCountReduceResponse = busDao.reduceSeatingCount(Constants.BED_TICKET, selectedBusId, bedCount)
            if (bedCountReduceResponse != Constants.TICKET_REDUCED) {
                return bedCountReduceResponse
            }
        }

        val seatCountReduceResponse = busDao.reduceSeatingCount(Constants.SEAT_TICKET, selectedBusId, seatCount)
        if (seatCountReduceResponse != Constants.TICKET_REDUCED) {
            return seatCountReduceResponse
        }

        val ticketId = ticketController.getTicket(selectedBusId, Integer.parseInt(userId), seatCount, bedCount)


        return "Bus booking successful. your ticket id is: $ticketId"
    }

    fun removeBus(busIdString: String?): String {
        val busId = Integer.parseInt(busIdString)

        if (busDao.getAllBuses().find{it.id == busId} == null) {
            return Constants.BUS_NOT_FOUND
        } else {
            busDao.removeBus(busId)
            return Constants.BUS_REMOVE_SUCCESS
        }
    }

    fun getAllBuses(): List<Bus> {

        // server / local db
        // long running -
        return busDao.getAllBuses()
    }
}