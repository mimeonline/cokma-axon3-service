package io.cookma.authservice.infrastructure.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.cookma.authservice.application.UserViewModel
import io.cookma.authservice.infrastructure.security.SecurityConstants.EXPIRATION_TIME
import io.cookma.authservice.infrastructure.security.SecurityConstants.HEADER_STRING
import io.cookma.authservice.infrastructure.security.SecurityConstants.SECRET
import io.cookma.authservice.infrastructure.security.SecurityConstants.TOKEN_PREFIX
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


open class JWTAuthenticationFilter(private val authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest,
                                       response: HttpServletResponse): Authentication {
        try {
            val credentials = ObjectMapper().readValue(request.inputStream, UserViewModel::class.java)

            return authManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            credentials.username,
                            credentials.password,
                            ArrayList<GrantedAuthority>())
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain,
                                          authentication: Authentication) {

        val token = Jwts.builder()
                .setSubject((authentication.principal as User).username)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.toByteArray())
                .compact()
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
        response.addHeader("Access-Control-Expose-Headers", "Authorization")
    }
}