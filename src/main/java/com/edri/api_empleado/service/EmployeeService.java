package com.edri.api_empleado.service;

import com.edri.api_empleado.dto.EmployeeDTO;
import com.edri.api_empleado.entity.Employee;
import com.edri.api_empleado.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService {
    private final  EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees(){
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("employee not found with id: " + id));
        return mapToDTO(employee);
    }

    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO dto){
        Employee employee = mapToEntity(dto);
        return mapToDTO(repository.save(employee));
    }

    @Transactional
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto){
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setFirstName(dto.getFirstName());
        existing.setMiddleName(dto.getMiddleName());
        existing.setPaternalLastName(dto.getPaternalLastName());
        existing.setMaternalLastName(dto.getMaternalLastName());
        existing.setAge(dto.getAge());
        existing.setSex(dto.getSex());
        existing.setBirthDate(dto.getBirthDate());
        existing.setPosition(dto.getPosition());
        existing.setIsActive(dto.getIsActive());

        return mapToDTO(repository.save(existing));
    }

    @Transactional
    public void deleteEmployee(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Cannot delete. Employee not found with id: " + id);
        }

        repository.deleteById(id);
    }

    private  EmployeeDTO mapToDTO(Employee entity){
        return EmployeeDTO.builder()
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .paternalLastName(entity.getPaternalLastName())
                .maternalLastName(entity.getMaternalLastName())
                .age(entity.getAge())
                .sex(entity.getSex())
                .birthDate(entity.getBirthDate())
                .position(entity.getPosition())
                .isActive(entity.getIsActive())
                .build();
    }

    private Employee mapToEntity(EmployeeDTO dto){
        return Employee.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .paternalLastName(dto.getPaternalLastName())
                .maternalLastName(dto.getMaternalLastName())
                .age(dto.getAge())
                .sex(dto.getSex())
                .birthDate(dto.getBirthDate())
                .position(dto.getPosition())
                .isActive(dto.getIsActive())
                .build();
    }

    public List<EmployeeDTO> searchEmployeesByName(String name) {
        return repository.findByNameContaining(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
