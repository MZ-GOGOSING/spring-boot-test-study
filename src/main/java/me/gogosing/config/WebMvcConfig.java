package me.gogosing.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.findAndRegisterModules();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		return objectMapper;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		final MappingJackson2HttpMessageConverter converter =
			new MappingJackson2HttpMessageConverter(this.objectMapper());

		converters.add(converter);
		converters.add(new StringHttpMessageConverter());
	}
}
