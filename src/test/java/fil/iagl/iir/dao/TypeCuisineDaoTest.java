package fil.iagl.iir.dao;

import java.util.List;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.core.Condition;
import org.junit.Test;

import fil.iagl.iir.entite.TypeCuisine;

public class TypeCuisineDaoTest extends AbstractDaoTest{
	
	private static final int NB_TYPE_CUISINE = 14;

	@Test
	public void testGetAll() throws Exception{
		List<TypeCuisine> list = typeCuisineDao.getAll();
		
		Assertions.assertThat(list).isNotNull().isNotEmpty().hasSize(NB_TYPE_CUISINE);
		Assertions.assertThat(list).have(new Condition<TypeCuisine>() {

			@Override
			public boolean matches(TypeCuisine type) {
				return type.getId() != null && type.getId() > 0 && type.getTypeCuisine() != null && !type.getTypeCuisine().isEmpty();
			}
		});
		
	}
	
}
