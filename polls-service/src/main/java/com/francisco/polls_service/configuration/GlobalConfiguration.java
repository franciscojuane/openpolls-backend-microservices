package com.francisco.polls_service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO) 
// Needed for consistent serialization across different Spring versions  
public class GlobalConfiguration {

}
