package Abusers.ACwDI;

import java.util.*;

public class Manager
{
    private List<Underling> subordinates;
    private Long employeeNo;
    private String foreName;
    private String surName;
    private Manager lineManager;

    public Long getEmployeeNo() {
        return employeeNo;
    }
    public void setEmployeeNo(Long eNo) {
        this.employeeNo = eNo;
    }
    public String getForeName() {
        return foreName;
    }
    public void setForeName(String foreName) {
        this.foreName = foreName;
    }
    public String getSurNameName() {
        return surName;
    }
    public void setSurName(String surNameName) {
        this.surName = surName;
    }

    public List<Underling> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Underling> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        return "Manager [subordinates=" + subordinates + ", details=" + super.toString() + "]";
    }
}