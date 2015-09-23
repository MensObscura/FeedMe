package fil.iagl.iir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class FeedMeSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Lazy
    @Autowired
    UserDetailsService authenticationService;

    @Override
    protected void configure( final HttpSecurity http ) throws Exception {

        // Desactiver le certificat pour autoriser PUT/POST/DELETE
        http.csrf().disable();

        // L'accès au autre ressources est sécurisé
        http.authorizeRequests().anyRequest().authenticated();

        // Redirection en cas de tentative non identifié
        http.formLogin().defaultSuccessUrl( "/accueil.html" ).permitAll();

    }

    @Override
    public void configure( final WebSecurity web ) throws Exception {
        super.configure( web );

        // Autoriser l'accès au ressources sans etre authentifier
        web.ignoring().antMatchers( "/" );
        web.ignoring().antMatchers( "/index.html" );
        web.ignoring().antMatchers( "/login.html" );
        web.ignoring().antMatchers( "/inscription.html" );
        web.ignoring().antMatchers( "/js/**" );
        web.ignoring().antMatchers( "/css/**" );
    }

    @Autowired
    public void configureGlobal( final AuthenticationManagerBuilder auth ) throws Exception {
        final ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        auth.userDetailsService( this.authenticationService ).passwordEncoder( encoder );
    }

}
