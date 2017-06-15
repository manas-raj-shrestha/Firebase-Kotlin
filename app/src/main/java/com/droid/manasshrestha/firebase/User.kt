package com.droid.manasshrestha.firebase

data class User(var userName: String, var userId: String) {
    constructor() : this("", "")
}