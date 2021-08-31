package com.bluebus.dao

import com.bluebus.models.options.Options

class OptionsDao {

    fun getBusTypes(): List<Options> {
        return FakeOptionsDB.busTypeOptions
    }

    fun getMainScreenOptions(): List<Options> {
        return FakeOptionsDB.mainScreenOptions
    }

    fun getAdminScreenOptions(): List<Options> {
        return FakeOptionsDB.adminPageOptions
    }

    fun getClientScreenOptions(): List<Options> {
        return FakeOptionsDB.clientScreenOptions
    }

}