package fil.iagl.iir.dao.image;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Image;

public interface ImageDao {

  /**
   * Sauvegarde une image en base de donnée ( son path )
   * 
   * @param image l'image à sauvegarder
   * @return le nombre de ligne sauvegarder
   */
  Integer sauvegarder(@Param("image") Image image);

  /**
   * Sauvegarde une image pour la table d'association des offres
  
   * @param idImage
   * @param idOffre
   * @return le nombre de ligne sauvegarder  
   */
  Integer sauvegarderPourOffre(@Param("idImage") Integer idImage, @Param("idOffre") Integer idOffre);

  /**
   * Recupere l'image d'id passé en parametre
   * 
   * @param idImage l'id de l'image
   * @return l'image
   */
  Image getById(@Param("idImage") Integer idImage);
}
