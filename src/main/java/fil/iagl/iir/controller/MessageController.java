package fil.iagl.iir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.entite.Message;
import fil.iagl.iir.outils.DataReturn;
import fil.iagl.iir.service.MessageService;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
	 @Autowired
	  private MessageService messageService;
	 
	 @RequestMapping(value = "/{idUser}/nonLus", method = RequestMethod.GET)
	 public DataReturn<List<Message>> getMessagesNonLus(@PathVariable("idUser") Integer idUser) {
		 return new DataReturn<List<Message>>(messageService.getAllNonLuParId(idUser));
	 }
	 
	 @RequestMapping(value = "/{idUser}", method = RequestMethod.GET)
	 public DataReturn<List<Message>> getMessages(@PathVariable("idUser") Integer idUser) {
		 return new DataReturn<List<Message>>(messageService.getAll(idUser));
	 }
	 
	 @RequestMapping(value = "/{idMsg}", method = RequestMethod.DELETE)
	 public DataReturn<?> supprimerMessage(@PathVariable("idMsg") Integer idMsg) {
		 messageService.supprimer(idMsg);
		 return new DataReturn<Message>();
	 }
	 
}
