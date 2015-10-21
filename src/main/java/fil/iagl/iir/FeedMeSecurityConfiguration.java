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

import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.AuthentificationService;

/**
 * @author RMS
 *
 * Configuration pour la Securité ( Spring Security )
 */

@Configuration
@EnableWebSecurity
public class FeedMeSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Lazy
  @Autowired
  AuthentificationService authenticationService;

  /**
   * Configuration global de la sécurité
   */
  @Override
  protected void configure(final HttpSecurity http) {

    try {
      // Desactiver le certificat pour autoriser PUT/POST/DELETE
      http.csrf().disable();

      // L'accès au autre ressources est sécurisé
      http.authorizeRequests().anyRequest().authenticated();

      // Redirection en cas de tentative non identifié
      http.formLogin().defaultSuccessUrl("/resources/accueil.html").permitAll();
    } catch (Exception e) {
      throw new FeedMeException(e);
    }

  }

  /**
   * Defini les routes accessibles sans etre authentifié
   */
  @Override
  public void configure(final WebSecurity web) {
    try {
      super.configure(web);
    } catch (Exception e) {
      throw new FeedMeException(e);
    }

    // Autoriser l'accès au ressources sans etre authentifier
    web.ignoring().antMatchers("/");
    web.ignoring().antMatchers("/resources/**");
    web.ignoring().antMatchers("/utilisateur/particulier");

  }

  /**
   * Defini comment le mot de passe doit etre crypter avant de tester la valeur en base de donnée
   * @param auth le gestionnaire d'authentification
   */
  @Autowired
  public void configureGlobal(final AuthenticationManagerBuilder auth) {
    final PasswordEncoder encoder = new BCryptPasswordEncoder();
    try {
      auth.userDetailsService(this.authenticationService).passwordEncoder(encoder);
    } catch (Exception e) {
      throw new FeedMeException(e);
    }
  }

}
