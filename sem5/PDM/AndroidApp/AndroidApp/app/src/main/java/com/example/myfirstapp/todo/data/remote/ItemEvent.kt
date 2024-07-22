package com.example.myfirstapp.todo.data.remote

import com.example.myfirstapp.todo.data.Series

data class Payload(val updatedSeries: Series)

data class ItemEvent(val event: String, val payload: Payload)
