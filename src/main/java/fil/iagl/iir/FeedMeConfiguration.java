package fil.iagl.iir;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.fasterxml.jackson.databind.SerializationFeature;

import fil.iagl.iir.entite.Role;
import fil.iagl.iir.outils.JsonRoleDeserializer;

@Configuration
@MapperScan("fil.iagl.iir.dao")
@ComponentScan({ "fil.iagl.iir.service", "fil.iagl.iir.controller" })
@EnableAutoConfiguration
@EnableWebMvc
public class FeedMeConfiguration extends WebMvcAutoConfiguration {

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);


		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/mapper/*.xml"));

		sessionFactory.setTypeHandlersPackage("fil.iagl.iir.typehandler");
		return sessionFactory.getObject();
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
		builder.deserializerByType(Role.class, new JsonRoleDeserializer());
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return builder;
	}

}
