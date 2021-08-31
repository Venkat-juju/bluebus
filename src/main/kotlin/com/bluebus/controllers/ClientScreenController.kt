package com.bluebus.controllers

import com.bluebus.dao.FakeOptionsDB
import com.bluebus.models.options.Options

class ClientScreenController {

    fun getClientScreenOptions(): List<Options> {
        return FakeOptionsDB.clientScreenOptions;
    }
}