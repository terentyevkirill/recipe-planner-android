package com.terentiev.recipeplanner.ui.calendar

import androidx.lifecycle.ViewModel
import com.terentiev.recipeplanner.RecipePlanner

class CalendarViewModel : ViewModel() {
    fun logout() {
        RecipePlanner.instance.setUserToken("")
        RecipePlanner.instance.setUsername("")
    }
}