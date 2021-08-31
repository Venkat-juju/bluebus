package com.bluebus.views

import com.bluebus.controllers.AdminScreenController
import com.bluebus.controllers.BusController
import com.bluebus.controllers.TicketController
import com.bluebus.models.options.Options
import com.bluebus.utils.Constants
import com.sun.corba.se.impl.orbutil.closure.Constant
import com.sun.org.apache.bcel.internal.Const

class AdminPageView {

    private val adminPageController = AdminScreenController()
    private val busController = BusController()
    private val homePageView = HomeScreenView()
    private val clientPageView = ClientPageView()
    private val ticketController = TicketController()

    fun showOptions() {
        val options = adminPageController.getAdminScreenOptions()
        printOptions(options)
        val validInputs = options.map{ it.id }

        var response = readLine()?: ""

        while (!isValidInput(validInputs = validInputs, response)) {
            showInvalidResposeError()
            response = readLine()?: ""
        }

        if (response == "1") {
            // add bus
            addBus()
        } else if (response == "2") {
            // remove bus
            removeBus()
        } else if (response == "3") {
            // show All tickets
            showAllTickets()
        } else if (response == "4") {
            showAllBuses()
        } else if (response == "5") {
            homePageView.showMainOption()
        } else {
            println("Something went wrong! Please try again!")
            showOptions()
        }
    }

    fun showAllTickets() {
        val tickets = ticketController.getAllTicket()

        if (tickets.size == 0) {
            println("No tickets booked!")
        }

        tickets.forEach {
            println(it)
        }

        showOptions()
    }

    fun addBus() {
        lateinit var start: String
        lateinit var destination: String
        lateinit var type: String
        var seatCount = 0
        var bedCount = 0

        print("Enter the bus starting point: ")
        start = readLine()?: ""
        print("Enter the destination point: ")
        destination = readLine()?: ""
//        print("Enter the type: ")
//        type = readLine()?: ""
        print("Enter the number of seatings: ")
        seatCount = Integer.parseInt(readLine())

        print("Choose the bus type: ")
        val busTypes: List<Options> = busController.getBusTypes()
        printOptions(busTypes)
        var busType = readLine()?: ""
        val validInputs = busTypes.map{ it.id }

        while(!isValidInput(validInputs, busType)) {
            showInvalidResposeError()
            busType = readLine()?: ""
        }

        val busTypeString = busTypes.find { Integer.parseInt(busType) == it.id }?.text
        if (busTypeString == Constants.BUS_TYPE_AC || busTypeString == Constants.BUS_TYPE_SLEEPER) {
            print("Enter the number of beds: ")
            bedCount = Integer.parseInt(readLine())?: 0
        }

        if (busTypeString != null) {
            val result = busController.addBus(start, destination, busTypeString, seatCount, bedCount)
            if (result > 0) {
                println("Bus added successfully")
            } else {
                println("Something went wrong please try again")
            }
        } else {
            println("Something went wrong please try again")
        }

        showOptions()
    }

    private fun showAllBuses() {
        val  buses = busController.getAllBuses()
        if (buses.size == 0) {
            println("There are currently no buses available")
        } else {
            buses.forEach {
                println("%04d -  $it".format(it.id))
            }
        }


        showOptions()
    }

    fun removeBus() {
        println("Enter the bus Id to be removed: ")
        val response = busController.removeBus(readLine())

        if (Constants.BUS_NOT_FOUND.equals(response)) {
            showInvalidResposeError()
            removeBus()
        } else if (Constants.BUS_REMOVE_SUCCESS.equals(response)) {
            println(response)
            showOptions()
        } else {
            println("Something went wrong! Please try again")
            showOptions()
        }
    }

}