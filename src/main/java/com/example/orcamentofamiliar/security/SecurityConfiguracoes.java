package com.example.orcamentofamiliar.security;

import com.example.orcamentofamiliar.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Profile(value = {"prod","test"})
public class SecurityConfiguracoes {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Bean
    protected PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    protected SecurityFilterChain filterChain  (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/usuario").permitAll()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .antMatchers(HttpMethod.POST,"/despesas" ).hasAuthority("USUARIO")
                .antMatchers(HttpMethod.GET,"/despesas/**").hasAuthority("USUARIO")
                .antMatchers(HttpMethod.DELETE, "/despesas/*").hasAuthority("USUARIO")
                .antMatchers(HttpMethod.PUT,"/despesas/*").hasAuthority("USUARIO")
                .antMatchers(HttpMethod.GET,"/resumo/*/*").hasAuthority("USUARIO")
                .antMatchers(HttpMethod.PUT, "/receitas/*").hasAuthority("USUARIO")
                .antMatchers(HttpMethod.POST,"/receitas" ).hasAuthority("USUARIO")
                .antMatchers(HttpMethod.GET,"/receitas/**").hasAuthority("USUARIO")
                .antMatchers(HttpMethod.DELETE, "receitas/*").hasAuthority("USUARIO")
                .antMatchers(HttpMethod.PUT,"/usuario/*").hasAuthority("USUARIO")
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaToken(usuarioRepository,tokenService),
                UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean //configurações de recursos estaticos (imagens, etc.)
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

}
