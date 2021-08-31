package com.bluebus.views

import com.bluebus.controllers.BusController
import com.bluebus.controllers.ClientScreenController
import com.bluebus.controllers.TicketController
import com.bluebus.models.buses.ACBus
import com.bluebus.models.buses.NormalBus
import com.bluebus.models.buses.SleeperBus
import com.bluebus.models.tickets.Ticket

class ClientPageView {

    private val clientPageController = ClientScreenController()
    private val ticketController = TicketController()
    private val busController = BusController()
    private val homeScreenView = HomeScreenView()

    fun showOptions() {
        val options = clientPageController.getClientScreenOptions()
        printOptions(options)
        val validInputs = options.map{ it.id }
        var response = readLine()?: ""

        while (!isValidInput(validInputs = validInputs, response)) {
            showInvalidResposeError()
            response = readLine()?: ""
        }

        if (response == "1") {
            // book bus
            bookBus()
        } else if (response == "2") {
            // show all buses
            showAllBuses()
        } else if (response == "3") {
            // show my tickets
            showMyTickets()
        } else if (response == "4") {
            homeScreenView.showMainOption()
        } else {
            println("Invalid option... please try again!")
            showOptions()
        }
    }

    private fun showAllBuses() {

        //TODO: implement async operation
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

    private fun bookBus() {

        print("Enter the starting point: ")
        val start = readLine()?: ""
        println("Enter the destination point: ")
        val destination = readLine()?: ""

        val buses = busController.getBusesFor(start, destination)
        if (buses.size == 0) {
            println("No buses available on this start or destination... please try again")
            bookBus()
        }

        println("Choose the bus number: ")
        buses.forEach {
            println("%04d -  $it".format(it.id))
        }
        var selectedBus = readLine()?: ""
        val validInputs = buses.map{ it.id }

        while(!isValidInput(validInputs, selectedBus)) {
            showInvalidResposeError()
            selectedBus = readLine()?: ""
        }

        print("Choose the seating count: ")
        val seatCount = Integer.parseInt(readLine())?: 0
        var bedCount = 0

        if (!(buses.find{ it.id == Integer.parseInt(selectedBus)} is NormalBus)) {
            print("Enter the bed count: ")
            bedCount = Integer.parseInt(readLine())?: 0
        }

        if (seatCount <= 0 && bedCount <= 0) {
            showInvalidResposeError()
            bookBus()
        }

        print("Enter your user id: ")
        var userId = readLine()?: ""

        if (userId.isNullOrEmpty()) {
           showInvalidResposeError()
           userId = readLine()?: ""
        }

        val response = busController.bookBus(Integer.parseInt(selectedBus), seatCount, bedCount, userId)
        println(response)
        showOptions()
    }

    private fun showMyTickets() {
        print("Enter your personal id: ")
        var personalId = Integer.parseInt(readLine())?: -1

        while(personalId == -1) {
            println("Invalid personal id, try again ")
            personalId = Integer.parseInt(readLine())?: -1
        }

        val tickets: List<Ticket>  = ticketController.getTicketsWithUserId(personalId)

        if (tickets.size == 0) {
            println("Currently, you have no tickets booked! Book a ticket now..")
        }

        tickets.forEach {
            println(it)
        }

        showOptions()
    }
}