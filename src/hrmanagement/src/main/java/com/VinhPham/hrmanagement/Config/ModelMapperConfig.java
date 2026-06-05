package com.VinhPham.hrmanagement.Config;

import com.VinhPham.hrmanagement.DTO.DependentDTO;
import com.VinhPham.hrmanagement.DTO.EducationLevelDTO;
import com.VinhPham.hrmanagement.DTO.EmployeeDTO;
import com.VinhPham.hrmanagement.Entity.EducationLevelEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeDependentEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

//        modelMapper.typeMap(EmployeeEntity.class, EmployeeDTO.class)
//                .addMappings(mapper -> {
//                    mapper.map(src -> {
//                        if (src.getEducationLevels() == null) return new ArrayList<String>();
//
//                        return src.getEducationLevels().stream()
//                                .map(EducationLevel::getMajor)
//                                .toList();
//                    }, EmployeeDTO::setMajor);
//                });

        modelMapper.typeMap(EmployeeEntity.class, EmployeeDTO.class)
                .setPostConverter(context -> {
                    EmployeeEntity source = context.getSource();
                    EmployeeDTO destination = context.getDestination();

                    if (source.getDependents() != null && !source.getDependents().isEmpty()) {
                        List<DependentDTO> dependentDTOs = new ArrayList<>();
                        for (EmployeeDependentEntity dependent : source.getDependents()) {
                            DependentDTO dto = new DependentDTO();
                            dto.setName(dependent.getName());
                            dto.setRelationship(dependent.getRelationship());
                            dto.setBirthday(dependent.getBirthday());
                            dependentDTOs.add(dto);
                        }

                        destination.setEmployeeDependents(dependentDTOs);
                    }

                    if(source.getEducationLevels() != null && !source.getEducationLevels().isEmpty()) {
                        List<EducationLevelDTO> educationLevelDTOs = new ArrayList<>();
                        for (EducationLevelEntity educationLevel : source.getEducationLevels()) {
                            EducationLevelDTO dto = new EducationLevelDTO();
                            dto.setId(educationLevel.getId());
                            dto.setInstitution(educationLevel.getInstitution());
                            dto.setMajor(educationLevel.getMajor());
                            dto.setQualification(educationLevel.getQualification());
                            educationLevelDTOs.add(dto);
                        }
                        destination.setEducationLevels(educationLevelDTOs);
                    }

                    if(source.getInsurances() != null && !source.getInsurances().isEmpty()) {
                        List<String> insuranceNames = source.getInsurances().stream()
                                .map(insurance -> insurance.getInsurance().getName())
                                .toList();
                        destination.setInsurances(insuranceNames);
                    }

                    return destination;
                });

        return modelMapper;
    }
}
