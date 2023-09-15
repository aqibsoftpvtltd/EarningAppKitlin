package com.example.earningappkotlin.models

class User {

    private var name =""
    private var age =0
    private var email = ""
    private var password = ""

    constructor()
    constructor(name: String, age: Int, email: String, password: String) {
        this.name = name
        this.age = age
        this.email = email
        this.password = password
    }
}