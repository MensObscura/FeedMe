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

import fil.iagl.iir.service.AuthentificationService;

@Configuration
@EnableWebSecurity
public class FeedMeSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Lazy
	@Autowired
	AuthentificationService authenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Desactiver le certificat pour autoriser PUT/POST/DELETE
		http.csrf().disable();

		// L'accès au autre ressources est sécurisé
		http.authorizeRequests().anyRequest().authenticated();

		// Redirection en cas de tentative non identifié
		http.formLogin().loginPage("/signin").passwordParameter("password").usernameParameter("username").permitAll();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Autoriser l'accès au ressources sans etre authentifier
		web.ignoring().antMatchers("/");
		web.ignoring().antMatchers("/signin");
		web.ignoring().antMatchers("/*.html");
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/css/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		auth.userDetailsService(authenticationService).passwordEncoder(encoder);
	}
}
