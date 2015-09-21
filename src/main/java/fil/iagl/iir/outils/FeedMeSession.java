package fil.iagl.iir.outils;

import org.springframework.security.core.context.SecurityContextHolder;

public class FeedMeSession {

	public static Integer getIdUtilisateurConnecte() {
		FeedMeAuthentificationToken auth = (FeedMeAuthentificationToken) SecurityContextHolder.getContext()
				.getAuthentication();
		return auth.getAuthentification().getUtilisateur().getIdUtilisateur();
	}

}
