package fil.iagl.iir.dao.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Message;

public interface MessageDao {

  Integer sauvegarder(@Param("message") Message message);

  Integer supprimer(@Param("idMessage") Integer id);

  List<Message> getAllNonLuParId(@Param("idDestinataire") Integer id);

  List<Message> getAll(@Param("idDestinataire") Integer id);

}
