package com.nit.config;

import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.nit.entity.Product;
import com.nit.entity.ProductCategory;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	
	@Autowired
	private EntityManager entityManager;

	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		exposeIds(config);
		HttpMethod[] unsupportedActions= {HttpMethod.DELETE,HttpMethod.POST,HttpMethod.PUT};
		
		config.getExposureConfiguration()
				.forDomainType(Product.class)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
				.withCollectionExposure((metadata,httpMethods) -> httpMethods.disable(unsupportedActions));
		config.getExposureConfiguration()
			   .forDomainType(ProductCategory.class)
			   .withItemExposure((metadata,httpMethods) -> httpMethods.disable(unsupportedActions))
			   .withCollectionExposure((metadata,httpMethods) -> httpMethods.disable(unsupportedActions));
		
		
		//System.out.println(config);
		
		//RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
	}
	
	private void exposeIds(RepositoryRestConfiguration config)
	{
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		//entities.forEach(e->System.out.println(e));
		 Class[] array = entities.stream().map(entityType->{
			
			return entityType.getJavaType();
		}).toArray(Class[]::new);
		//map.forEach(x->System.out.println(x));
		//System.out.println(map);
		 config.exposeIdsFor(array);
	}

}
