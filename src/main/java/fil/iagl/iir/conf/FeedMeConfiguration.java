package fil.iagl.iir.conf;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.SerializationFeature;

import fil.iagl.iir.entite.Role;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.JsonRoleDeserializer;

/**
 * @author RMS
 * 
 *         Configuration du projet Permet à Spring de faire l'injection de
 *         dependences Permet à Spring de definir la DataSource ( connection
 *         avec la base de donnée ) Permet à MyBatis de trouver les mappers (
 *         .xml ) Permet à Jackson de definir comment serializer/deserialize du
 *         JSON
 */

@Configuration
@MapperScan("fil.iagl.iir.dao")
@ComponentScan({ "fil.iagl.iir.service", "fil.iagl.iir.controller", "fil.iagl.iir.outils" })
@EnableAutoConfiguration
@EnableWebMvc
public class FeedMeConfiguration extends WebMvcAutoConfigurationAdapter {

	/**
	 * Creation de la Bean "transactionManager" Spring va appeler cette method
	 * avec la Bean "dataSource" defini dans application.yml
	 * 
	 * @param dataSource
	 *            Representation de la base de donnée
	 * @return La bean "transactionManager"
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * Creation de la Bean "sqlSessionFactory" pour MyBatis Spring va appeler
	 * cette method avec la Bean "dataSource" defini dans application.yml
	 * 
	 * @throws IOException
	 *             Si le revolver ne trouve rien dans le classpath
	 * @param dataSource
	 *            Representation de la base de donnée
	 * @return La bean "sqlSessionFactory"
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/mapper/*Mapper.xml"));
		
		sessionFactory.setTypeHandlersPackage("fil.iagl.iir.typehandler");
		try {
			return sessionFactory.getObject();
		} catch (Exception e) {
			throw new FeedMeException(e);
		}
	}

	/**
	 * Creation de la Bean "jacksonBuilder" pour Jackson
	 *
	 * @return La bean "jacksonBuilder"
	 */
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
		builder.deserializerByType(Role.class, new JsonRoleDeserializer());
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return builder;
	}

}
