package fil.iagl.iir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
		http.authorizeRequests().anyRequest().anonymous();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		auth.userDetailsService(authenticationService).passwordEncoder(encoder);
	}
}
