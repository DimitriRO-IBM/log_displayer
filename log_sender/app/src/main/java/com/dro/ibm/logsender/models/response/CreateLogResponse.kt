package com.dro.ibm.logsender.models.response

data class CreateLogResponse(
    val log: LogResponse
)

data class LogResponse(
    val id: String,
    val message: String,
    val timestamp: String,
    val createdAt: String,
    val updatedAt: String
)
