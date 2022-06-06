package com.lpnu.easysmarthome.model;

import com.lpnu.easysmarthome.model.enums.Building;
import com.lpnu.easysmarthome.model.enums.ControlFunctions;
import com.lpnu.easysmarthome.model.enums.SecurityFunctions;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = true)
    private User employee;

    @Enumerated(EnumType.STRING)
    private Building buildingType = Building.OTHER;
    @ElementCollection(targetClass = ControlFunctions.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "order_control_functions", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private Set<ControlFunctions> controlFunctions = new HashSet<>() ;
    @ElementCollection(targetClass = SecurityFunctions.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "order_security_functions", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private Set<SecurityFunctions> securityFunctions = new HashSet<>();
    @Min(value = 1, message = "Area should be more than 1")
    @Max(value = 1000, message = "Area should be smaller than 1000")
    private int squareMeter;
    @NotNull
    @NotBlank(message = "Address is required")
    private String objectAddress;
    @NotNull
    @Pattern(regexp = "^(?:\\+38)?(0\\d{9})$", message = "You should type valid phone number. Example: +380900000000")
    private String phoneNumber;
    @Size(max = 256,message = "Message length can be up to 256")
    private String customerMessage;
    @Size(max = 256,message = "Message length can be up to 256")
    private String employeeMessage;

    private boolean active;

    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order() {
        controlFunctions.add(ControlFunctions.OTHER);
        securityFunctions.add(SecurityFunctions.OTHER);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<SecurityFunctions> getSecurityFunctions() {
        return securityFunctions;
    }

    public void setSecurityFunctions(Set<SecurityFunctions> securityFunctions) {
        this.securityFunctions = securityFunctions;
    }

    public String getObjectAddress() {
        return objectAddress;
    }

    public void setObjectAddress(String objectAddress) {
        this.objectAddress = objectAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public String getEmployeeMessage() {
        return employeeMessage;
    }

    public void setEmployeeMessage(String employeeMessage) {
        this.employeeMessage = employeeMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Building getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Building buildingType) {
        this.buildingType = buildingType;
    }

    public Set<ControlFunctions> getControlFunctions() {
        return controlFunctions;
    }

    public void setControlFunctions(Set<ControlFunctions> controlFunctions) {
        this.controlFunctions = controlFunctions;
    }

    public int getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(int squareMeter) {
        this.squareMeter = squareMeter;
    }
}
