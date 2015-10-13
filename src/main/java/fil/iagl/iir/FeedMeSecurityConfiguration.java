package fil.iagl.iir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fil.iagl.iir.service.AuthentificationService;

@Configuration
@EnableWebSecurity
public class FeedMeSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Lazy
	@Autowired
	AuthentificationService authenticationService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		// Desactiver le certificat pour autoriser PUT/POST/DELETE
		http.csrf().disable();

		// L'accès au autre ressources est sécurisé
		http.authorizeRequests().anyRequest().authenticated();

		// Redirection en cas de tentative non identifié
		http.formLogin().defaultSuccessUrl("/resources/accueil.html").permitAll();

		/*
		 * 
		 * TODO : Voir comment faire une custom login page avec Angular
		 * 
		 * https://spring.io/guides/tutorials/spring-security-and-angular-js/
		 * https://spring.io/blog/2015/01/12/the-login-page-angular-js-and-
		 * spring-security-part-ii
		 * 
		 */
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		super.configure(web);

		// Autoriser l'accès au ressources sans etre authentifier
		web.ignoring().antMatchers("/");
		web.ignoring().antMatchers("/resources/**");

	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		final PasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.userDetailsService(this.authenticationService).passwordEncoder(encoder);
	}

}
