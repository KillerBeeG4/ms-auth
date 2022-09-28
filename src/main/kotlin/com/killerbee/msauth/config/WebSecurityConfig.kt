package com.killerbee.msauth.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ldap.core.DirContextOperations
import org.springframework.ldap.core.support.BaseLdapPathContextSource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper
import org.springframework.security.web.SecurityFilterChain
import java.util.Arrays
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${ad.domain}")
    private lateinit var AD_DOMAIN: String

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().cors().disable().authorizeRequests().anyRequest().permitAll()
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return ProviderManager(activeDirectoryLdapAuthenticationProvider())
    }

    @Bean
    fun activeDirectoryLdapAuthenticationProvider() : AuthenticationProvider {
        val provider = ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN, "ldap://adserv.killerbee.com:389")
        provider.setConvertSubErrorCodesToExceptions(true)
        provider.setUseAuthenticationRequestCredentials(true)
        return provider
    }
}