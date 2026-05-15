package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Project;
import com.capgemini.onboarding.service.ProjectService;



public class IdToProjectConverter  implements Converter<String, Project>{

	@Autowired
	ProjectService projectService;
	
	@Override
	public Project convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
			return projectService.getProjectById(Integer.parseInt(id));
    	}
		return null;
	}
}
