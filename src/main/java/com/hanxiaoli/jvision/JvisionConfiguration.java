package com.hanxiaoli.jvision;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class JvisionConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
