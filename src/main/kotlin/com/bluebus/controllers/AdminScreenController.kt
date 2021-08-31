package com.bluebus.controllers

import com.bluebus.dao.FakeOptionsDB
import com.bluebus.dao.OptionsDao
import com.bluebus.models.options.Options

class AdminScreenController {

    private val options = OptionsDao()

    fun getAdminScreenOptions(): List<Options> {
        return options.getAdminScreenOptions()
    }
}