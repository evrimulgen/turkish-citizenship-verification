package online.citizenshipverification.turkish.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static online.citizenshipverification.turkish.utils.ProjectConstants.PROJECT_CONTEXT_PATH;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Configuration
@EnableSwagger2
@PropertySource(value = "classpath:swagger-info.properties")
public class SwaggerConfiguration {

	@Value("${swagger.app-info.name}")
	private String appName;

	@Value("${swagger.app-info.description}")
	private String appDescription;

	@Value("${swagger.app-info.version}")
	private String appVersion;

	@Value("${swagger.app-info.license}")
	private String licence;

	@Value("${swagger.app-info.license-url}")
	private String licenceUrl;

	@Value("${swagger.contact.name}")
	private String contactName;

	@Value("${swagger.contact.url}")
	private String contactUrl;

	@Value("${swagger.contact.email}")
	private String contactEmail;

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(PROJECT_CONTEXT_PATH))
				.paths(PathSelectors.regex("/.*"))
				.build()
				.apiInfo(getApiInformation());
	}

	private ApiInfo getApiInformation() {

		final Contact contact = new Contact(contactName, contactUrl, contactEmail);

		return new ApiInfoBuilder()
				.title(appName)
				.version(appVersion)
				.description(appDescription)
				.license(licence).licenseUrl(licenceUrl)
				.contact(contact)
				.build();
	}

}