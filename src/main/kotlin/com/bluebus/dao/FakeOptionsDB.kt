package com.bluebus.dao

import com.bluebus.models.options.BusTypes
import com.bluebus.models.options.Options
import com.bluebus.models.options.ScreenOption
import com.bluebus.utils.Constants
import com.sun.corba.se.impl.orbutil.closure.Constant
import sun.security.ec.point.ProjectivePoint

object FakeOptionsDB {
    val mainScreenOptions: MutableList<Options> = mutableListOf(ScreenOption(1, "Admin Page","main"),
                                                                ScreenOption(2, "Client Page", "main"))

    val clientScreenOptions: MutableList<Options> = mutableListOf(ScreenOption(1, "Book Bus", "client"),
                                                                ScreenOption(2,"Show All Buses", "client"),
                                                                ScreenOption(3, "Show my tickets", "client"),
                                                                ScreenOption(4, "go back to main page", "client")
    )

    val adminPageOptions: MutableList<Options> = mutableListOf(ScreenOption(1, "Add Bus", "admin"),
                                                                ScreenOption(2, "Remove Bus", "admin"),
                                                                ScreenOption(3, "Show All Tickets", "admin"),
                                                                ScreenOption(4, "Show all buses", "client"),
                                                                ScreenOption(5, "go back to main page", "admin"))


    val busTypeOptions: MutableList<Options> = mutableListOf(BusTypes(1, Constants.BUS_TYPE_AC),
                                                            BusTypes(2, Constants.BUS_TYPE_NORMAL),
                                                            BusTypes(3, Constants.BUS_TYPE_SLEEPER))
}