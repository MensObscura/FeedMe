package fil.iagl.iir.dao;

import java.util.List;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.core.Condition;
import org.junit.Test;

import fil.iagl.iir.entite.TypeCuisine;

public class TypeCuisineDaoTest extends AbstractDaoTest {

  private static final int NB_TYPE_CUISINE = 14;

  @Test
  public void testGetAll() throws Exception {
    // Etant donne des types de cuisine definis en base
    // Quand on recupere la liste des typeCuisine existants
    List<TypeCuisine> list = typeCuisineDao.getAll();

    // Alors on verifie que l'on recupere le bon nombre de typeCuisine
    Assertions.assertThat(list).isNotNull().isNotEmpty().hasSize(NB_TYPE_CUISINE);
    // et que chaque type de cuisine n'est pas null et a son ID > 0
    Assertions.assertThat(list).have(new Condition<TypeCuisine>() {

      @Override
      public boolean matches(TypeCuisine typeCuisine) {
        return typeCuisine.getId() != null && typeCuisine.getId() > 0 && typeCuisine.getType() != null && !typeCuisine.getType().isEmpty();
      }
    });

  }

}
