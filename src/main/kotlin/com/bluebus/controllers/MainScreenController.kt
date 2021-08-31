package com.bluebus.controllers

import com.bluebus.dao.FakeOptionsDB
import com.bluebus.dao.OptionsDao
import com.bluebus.models.options.Options

class MainScreenController {

    private val options = OptionsDao()

    fun getMainScreenOptions(): List<Options> {
        return options.getMainScreenOptions()
    }
}