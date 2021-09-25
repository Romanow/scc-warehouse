package ru.romanow.warehouse.extensions

import java.util.regex.Pattern

class PatternsUtils {
    companion object {
        val legoTechnicName: Pattern = Pattern.compile("Lego Technic 42\\d{3}")
    }
}