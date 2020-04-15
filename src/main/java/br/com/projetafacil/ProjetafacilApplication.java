package br.com.projetafacil;

import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.projetafacil.storage.StorageProperties;
import br.com.projetafacil.storage.StorageService;
import br.com.projetafacil.thymeleaf.BrewerDialect;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ProjetafacilApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ProjetafacilApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProjetafacilApplication.class);
	}
	
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR")); 
	}
	
	@Configuration
	public static class MvcConfig extends WebMvcConfigurerAdapter {
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/index2");
		}
		
	}
	
	@Bean
	public BrewerDialect brewerDialect() {
		return new BrewerDialect();
	}
	
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
//            storageService.deleteAll();
//            storageService.init();
		};
	}
}
