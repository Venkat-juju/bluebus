package com.bluebus.views

import com.bluebus.controllers.MainScreenController
import com.bluebus.models.options.Options


fun printOptions(options: List<Options>) {
    println("Choose one: ")
    options.map{ "\t${it.id}. ${it.text}" }.forEach { println(it) }
}

fun showInvalidResposeError() {
    println("Invalid input... please try again!")
}

fun isValidInput(validInputs: List<Int>, input: String): Boolean {
    val inputInteger: Int = Integer.parseInt(input)

    return validInputs.contains(inputInteger)
}
