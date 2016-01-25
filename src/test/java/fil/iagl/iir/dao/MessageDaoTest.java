package fil.iagl.iir.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.dao.message.MessageDao;
import fil.iagl.iir.entite.Message;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.outils.SQLCODE;

public class MessageDaoTest extends AbstractDaoTest {

  @Autowired
  MessageDao messageDao;

  private static final int NB_MESSAGES_POUR_UTILISATEUR_2 = 3;

  private static final Integer NB_MESSAGES_NON_LU_POUR_UTILISATEUR_2 = 2;

  @Test
  public void sauvegarderTestSuccess() throws Exception {
    // Etant donne un message
    Message message = this.createMessage();

    // Quand on le sauvegarde en base
    messageDao.sauvegarder(message);

    // Alors un ID est bien généré
    Assertions.assertThat(message.getId()).isNotNull().isPositive();

    List<Message> messages = messageDao.getAll(message.getDestinataire().getIdUtilisateur());
    for (Message m : messages) {
      if (m.getId() == message.getId()) {
        // Et que la date de création a été mise a jour lors de la création
        Assertions.assertThat(m.getDate()).isNotNull();

        // Et que le message est marque en tant que non lu
        Assertions.assertThat(m.getLu()).isNotNull().isFalse();
      }
    }
  }

  @Test
  public void sauvegarderTestEchec_messageNull() throws Exception {
    // Etant donné un message null
    // Quand on veut le sauvegarder
    try {
      messageDao.sauvegarder(null);
      Assertions.fail("devrait soulever une exception");
    } catch (DataIntegrityViolationException e) {
      // Alors une exception SQL est levée
      assertSQLCode(e, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTest_texteNull() throws Exception {
    // Etant donné un message qui a un texte null
    Message message = createMessage();
    message.setTexte(null);

    // Quand on veut le saugarder
    try {
      messageDao.sauvegarder(message);
      Assertions.fail("une exception devrait être levée");
    } catch (DataIntegrityViolationException e) {
      // Alors une exception SQL est levée
      assertSQLCode(e, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_objetNull() throws Exception {
    // Etant donné un message qui a un objet null
    Message message = createMessage();
    message.setObjet(null);

    // Quand on veut le saugarder
    try {
      messageDao.sauvegarder(message);
      Assertions.fail("une exception devrait être levée");
    } catch (DataIntegrityViolationException e) {
      // Alors une exception SQL est levée
      assertSQLCode(e, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_expediteurNull() throws Exception {
    // Etant donné un message qui a un expéditeur null
    Message message = createMessage();
    message.setExpediteur(null);

    // Quand on veut le saugarder
    try {
      messageDao.sauvegarder(message);
      Assertions.fail("une exception devrait être levée");
    } catch (DataIntegrityViolationException e) {
      // Alors une exception SQL est levée
      assertSQLCode(e, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_destinataireNull() throws Exception {
    // Etant donné un message qui a un destinataire null
    Message message = createMessage();
    message.setDestinataire(null);

    // Quand on veut le saugarder
    try {
      messageDao.sauvegarder(message);
      Assertions.fail("une exception devrait être levée");
    } catch (DataIntegrityViolationException e) {
      // Alors une exception SQL est levée
      assertSQLCode(e, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void supprimerTestSuccess() throws Exception {
    // Etant donné un message d'id 2 qui est enregistré en base de donné
    Integer idMessage = 2;
    // Quand je supprime le test qui a pour id 2
    messageDao.supprimer(idMessage);

    // Alors il y a un message de moins
    Assertions.assertThat(messageDao.getAll(idMessage).size()).isEqualTo(NB_MESSAGES_POUR_UTILISATEUR_2 - 1);

    // Et le message d'id 2 n'est plus présent
    for (Message m : messageDao.getAll(idMessage)) {
      if (m.getId() == idMessage) {
        Assertions.fail("ce message aurait du être supprimé");
      }
    }
  }

  @Test
  public void getAllTestSuccess() throws Exception {
    // Etant donne que la base de donnée contient un certain nombre de messages envoyés à l'utilisateur 2
    // Quand on récupère cette liste de message
    List<Message> messages = messageDao.getAll(2);

    // Alors la liste a la taille attendue
    Assertions.assertThat(messages.size()).isEqualTo(NB_MESSAGES_POUR_UTILISATEUR_2);

  }

  @Test
  public void getAllTestEchec_IdNull() throws Exception {
    // Etant donne un id de destinataire null
    // Quand on récupère la liste des messages envoyés à cet utilisateur
    List<Message> messages = messageDao.getAll(null);

    // Alors la liste est vide
    Assertions.assertThat(messages).isNotNull().isEmpty();
  }

  @Test
  public void getAllTestEchec_IdNonExistant() throws Exception {
    // Etant donné un id de destinataire qui n'existe pas en base
    // Quand on récupère la liste des messages envoyés à cet utilisateur
    List<Message> messages = messageDao.getAll(Integer.MAX_VALUE);

    // Alors la liste est vide
    Assertions.assertThat(messages).isNotNull().isEmpty();
  }

  @Test
  public void getAllNonLuTestSuccess() throws Exception {
    // Etant donné que la base contient un certain nombre de messages envoyés à un utilisateur
    // Quand on récupère cette liste de messages
    List<Message> messages = messageDao.getAllNonLuParId(2);

    // Alors la liste n'est pas nulle et a la taille attendue
    Assertions.assertThat(messages).isNotNull();
    Assertions.assertThat(messages.size()).isEqualTo(NB_MESSAGES_NON_LU_POUR_UTILISATEUR_2);
  }

  @Test
  public void getAllNonLuTestEchec_IdNull() throws Exception {
    // Etant donne un id de destinataire null
    // Quand on récupère la liste des messages envoyés non lus à cet utilisateur
    List<Message> messages = messageDao.getAllNonLuParId(null);

    // Alors la liste est vide
    Assertions.assertThat(messages).isNotNull().isEmpty();
  }

  @Test
  public void testGetAllNonLuTestEchec_IdNonExistant() throws Exception {
    // Etant donné un id de destinataire qui n'existe pas en base
    // Quand on récupère la liste des messages non lus envoyés à cet utilisateur
    List<Message> messages = messageDao.getAllNonLuParId(Integer.MAX_VALUE);

    // Alors la liste est vide
    Assertions.assertThat(messages).isNotNull().isEmpty();
  }
  
  @Test
  public void testMarquerCommeLuSucces() throws Exception {
	  //Etant donné que j'ai un message non lu d'id idMsg 
	  // et que je suis l'utilisateur destinataire d'id idDest
	  Integer idMsg = 1;
	  Integer idDest = 2;
	 
	  List<Message> messagesNonLus = messageDao.getAllNonLuParId(2);
	  int nbMessagesNonLus = messagesNonLus.size();
	  
	  // Quand je le lis mon message non lu idMsg
	  // Alors une seule ligne correspondant à mon message lu est modifiée
	  assertTrue(1==messageDao.marquerCommeLu(idMsg));
	  messagesNonLus = messageDao.getAllNonLuParId(2);
	  
	  // Et le message ne doit plus apparaître.
	  assertEquals(nbMessagesNonLus-1,messagesNonLus.size());
	  for (Message m : messagesNonLus) {
		  if (m.getId() == idMsg) {
			  fail("Le message d'id " + idMsg + "ne devrait plus apparaître.");
		  }
	  }
  }
	  
  @Test
  public void testMarquerCommeLuEchecIdMsgNull() throws Exception {
	  // Etant donné un id message null
	  Integer idMsg = null;
	  
	  // Quand je déclare ce message d'id null comme lu
	  // Aucune ligne ne doit être modifiée
	  assertTrue(0==messageDao.marquerCommeLu(idMsg));
	  
  }
}
