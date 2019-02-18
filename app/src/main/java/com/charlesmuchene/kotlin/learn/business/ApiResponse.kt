package com.charlesmuchene.kotlin.learn.business

sealed class ApiResponse

data class Success<T>(val data: T) : ApiResponse()

class Failure(val throwable: Throwable) : ApiResponse()