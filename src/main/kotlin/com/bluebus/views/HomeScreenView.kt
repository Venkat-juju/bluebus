package com.bluebus.views

import com.bluebus.controllers.MainScreenController
import java.io.FileInputStream
import java.util.*

class HomeScreenView {

    private val mainScreenController = MainScreenController()

    fun showMainOption() {
        val mainScreenOptions = mainScreenController.getMainScreenOptions()
        printOptions(mainScreenOptions)
        val validInputs: List<Int> = mainScreenOptions.map{ it.id }
        var response = readLine()?: ""

        while(!isValidInput(validInputs, response)) {
            showInvalidResposeError()
            response = readLine()?: ""
        }

        if (response == "1") {
            if (isAdmin()) {
                AdminPageView().showOptions()
            } else {
                println("Invalid credentials!")
                showMainOption()
            }
        } else if (response == "2") {
            ClientPageView().showOptions()
        }
    }

    fun isAdmin(): Boolean {
        print("Enter your username: ")
        val username = readLine()?: ""
        print("Enter your password: ")
        val password = readLine()?: ""

        val fis = FileInputStream("C:\\Users\\91824\\IdeaProjects\\bluebus\\src\\main\\kotlin\\com\\bluebus\\resources\\credentials.properties")
        val prop = Properties()
        prop.load(fis)

        if (prop.getProperty("username").toString() == username && prop.getProperty("password") == password) {
            return true
        }

        return false
    }
}