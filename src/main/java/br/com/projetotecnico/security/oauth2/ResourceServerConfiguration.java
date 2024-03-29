package br.com.projetotecnico.security.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "restservice";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(this.RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                logout().logoutSuccessUrl("/usuarios").permitAll()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and().authorizeRequests()
                .antMatchers("/usuarios/{id}/roles").hasAnyRole("USUARIO")
                .antMatchers("/telefones").hasAnyRole("USUARIO")
                .antMatchers("/telefones/{id}").hasAnyRole("USUARIO")
                .antMatchers("/clientes").hasAnyRole("USUARIO")
                .antMatchers("/clientes/{id}").hasAnyRole("USUARIO")
                .antMatchers("/enderecos").hasAnyRole("USUARIO")
                .antMatchers("/enderecos/{estadoId}/cidades").hasAnyRole("USUARIO")
                .antMatchers("/enderecos/{ciddaeId}/cidade").hasAnyRole("USUARIO")
                .antMatchers("/logs/filter").hasAnyRole("USUARIO")
                .antMatchers("/logs/classes").hasAnyRole("USUARIO")
                .antMatchers("/logs/propriedades").hasAnyRole("USUARIO")
                .anyRequest().denyAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }


}
