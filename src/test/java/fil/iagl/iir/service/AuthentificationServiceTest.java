package fil.iagl.iir.service;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.core.Condition;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Role;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;

public class AuthentificationServiceTest extends AbstractServiceTest {

  @Test
  public void loadUserByUsernameTestSucces() throws Exception {
    Authentification<Particulier> auth = this.createAuthentificationParticulier();

    String username = auth.getUtilisateur().getMail();
    String password = auth.getPassword();

    Mockito.doReturn(auth).when(authentificationDao).getByUsername(username);

    UserDetails user = authentificationService.loadUserByUsername(username);

    Assertions.assertThat(user).isNotNull();
    Assertions.assertThat(user.getUsername()).isNotNull().isEqualTo(username);
    Assertions.assertThat(user.getPassword()).isNotNull().isEqualTo(password);
    Assertions.assertThat(user.getAuthorities()).isNotNull().hasSize(1).haveExactly(1,
      new Condition<GrantedAuthority>() {

        @Override
        public boolean matches(GrantedAuthority authority) {
          return authority.getAuthority().equals(Role.PARTICULIER.name());
        }
      });

    Mockito.verify(authentificationDao).getByUsername(username);
  }

  @Test(expected = UsernameNotFoundException.class)
  public void loadUserByUsernameTestEchec() throws Exception {
    String username = "usernameNotExist";
    Mockito.when(authentificationDao.getByUsername(username)).thenReturn(null);

    authentificationService.loadUserByUsername(username);

    Mockito.verify(authentificationDao).getByUsername(username);
  }

  @Test
  public void inscriptionTestSucces() throws Exception {
    Authentification<Particulier> auth = createAuthentificationParticulier();

    authentificationService.inscription(auth);

    InOrder order = Mockito.inOrder(utilisateurDao, particulierDao, authentificationDao);

    order.verify(utilisateurDao).sauvegarder(auth.getUtilisateur());
    order.verify(particulierDao).sauvegarder(auth.getUtilisateur());
    order.verify(authentificationDao).sauvegarder(auth);
  }

  @Test(expected = FeedMeException.class)
  public void inscriptionTestEchec() throws Exception {
    Authentification<Particulier> auth = null;

    authentificationService.inscription(auth);

    Mockito.verify(utilisateurDao, Mockito.never()).sauvegarder(Mockito.any());
    Mockito.verify(particulierDao, Mockito.never()).sauvegarder(Mockito.any());
    Mockito.verify(authentificationDao, Mockito.never()).sauvegarder(Mockito.any());
  }

  @Test
  public void logoutTestSucces() throws Exception {
    Assertions.assertThat(FeedMeSession.getIdUtilisateurConnecte()).isNotNull();
    this.authentificationService.logout();
    Assertions.assertThat(FeedMeSession.getIdUtilisateurConnecte()).isNull();
  }
}
