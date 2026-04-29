package com.edri.api_empleado.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class EmployeeDTO{
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    private String middleName;

    @NotBlank(message = "First last name is mandatory")
    private String paternalLastName;

    @NotBlank(message = "Second last name is  mandatory")
    private String maternalLastName;

    @Min(18) @Max(65)
    private Integer age;

    @NotBlank
    private String sex;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @NotBlank
    private String position;

    private Boolean isActive;
}