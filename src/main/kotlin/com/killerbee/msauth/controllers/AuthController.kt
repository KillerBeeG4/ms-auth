package com.killerbee.msauth.controllers

import com.killerbee.msauth.models.LoginRequest
import com.killerbee.msauth.services.ADAuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @Autowired
    private lateinit var authService: ADAuthService

    @PostMapping("/auth")
    fun auth(@RequestBody login: LoginRequest) : ResponseEntity<Any> {
        authService.authenticate(login.Username, login.Password)
        return ResponseEntity.ok("ok")
    }
}