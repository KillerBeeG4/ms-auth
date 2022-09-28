package com.killerbee.msauth.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ADAuthService {

    @Autowired
    private lateinit var authManager: AuthenticationManager

    fun test() {
        val auth = authManager.authenticate(UsernamePasswordAuthenticationToken("clement", "vicart"))

        SecurityContextHolder.getContext().authentication = auth
    }

    fun authenticate(username: String, password: String) {
        val auth = authManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        if(auth != null) {
            SecurityContextHolder.getContext().authentication = auth
        }
    }
}