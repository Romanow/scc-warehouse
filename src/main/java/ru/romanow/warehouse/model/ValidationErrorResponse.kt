package ru.romanow.warehouse.model

data class ValidationErrorResponse(
    val message: String,
    val errors: List<ErrorDescription>
)