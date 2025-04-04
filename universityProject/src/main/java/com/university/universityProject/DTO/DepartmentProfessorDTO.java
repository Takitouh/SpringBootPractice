package com.university.universityProject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class DepartmentProfessorDTO {
    private Long departmentId;
    private List<Long> professorsId = new ArrayList<>();
}
