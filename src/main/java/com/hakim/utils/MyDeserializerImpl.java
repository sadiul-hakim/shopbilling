package com.hakim.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hakim.pojo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyDeserializerImpl extends JsonDeserializer<Shop> {
    public static final String NAME="shop_name";
    public static final String CAPITAL="shop_capital";
    public static final String TOTAL_PRODUCT="total_product";

    public static final String LOCATION="shop_location";
    public static final String ADDRESS="address";
    public static final String CITY="city";
    public static final String DISTRICT="district";
    public static final String SUB_DISTRICT="sub_district";
    public static final String ZIP="zip";

    public static final String OWNER="shop_owner";
    public static final String OWNER_NAME="owner_name";
    public static final String OWNER_PIN="owner_pin";
    public static final String OWNER_ROLE="owner_role";

    public static final String EMPLOYEE_LIST="employee_list";
    public static final String EMPLOYEE_NAME="employee_name";
    public static final String EMPLOYEE_PIN="employee_pin";
    public static final String EMPLOYEE_ROLE="employee_role";
    public static final String EMPLOYEE_SALARY="employee_salary";

    public static final String CONTACT="contact";
    public static final String PHONE_LIST="phone_list";
    public static final String EMAIL="email";

    public static final String TRANSACTION="shop_transaction";
    public static final String TOTAL_INCOME="total_income";
    public static final String TOTAL_COST="total_cost";
    public static final String WORKDAY="workday";

    public static final String GIVEN_SALARY="given_salary";
    public static final String SALARY_AMOUNT="salary_amount";
    public static final String GIVEN_DATA="given_date";
    @Override
    public Shop deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        Shop shop=new Shop();

        shop.setName(node.get(NAME).asText());
        shop.setCapital(node.get(CAPITAL).asInt());
        shop.setTotal_product(node.get(TOTAL_PRODUCT).asInt());

        Address location = deserializeAddress(node.get(LOCATION));
        shop.setLocation(location);

        Owner owner = deserializeOwner(node.get(OWNER));
        shop.setOwner(owner);

        List<Employee> employees = deserializeEmployeeList(node.get(EMPLOYEE_LIST));
        shop.setEmployees(employees);

        Transaction transaction= deserializeTransaction(node.get(TRANSACTION));
        shop.setTransaction(transaction);

        List<Salary> salaryList = deserializeSalaryList(node.get(GIVEN_SALARY));
        shop.setGiven_salary(salaryList);

        return shop;
    }

    private List<Salary> deserializeSalaryList(JsonNode node){
        List<Salary> salaryList=new ArrayList<>();

        if(node.isArray()){
            for(JsonNode n:node){
                Salary salary=new Salary();
                salary.setEmployee_name(n.get(EMPLOYEE_NAME).asText());
                salary.setSalary(n.get(SALARY_AMOUNT).asInt());
                salary.setGiven_date(n.get(GIVEN_DATA).asText());

                salaryList.add(salary);
            }
        }

        return salaryList;
    }

    private Transaction deserializeTransaction(JsonNode node){
        Transaction transaction=new Transaction();

        transaction.setTotalCost(node.get(TOTAL_COST).asInt());
        transaction.setTotalIncome(node.get(TOTAL_INCOME).asInt());
        transaction.setWorkday(node.get(WORKDAY).asText());

        return transaction;
    }

    private Owner deserializeOwner(JsonNode node){
        Owner owner=new Owner();

        owner.setName(node.get(OWNER_NAME).asText());

        Address address = deserializeAddress(node.get(ADDRESS));
        owner.setAddress(address);

        Contact contact = desirializeContact(node.get(CONTACT));
        owner.setContact(contact);

        owner.setPin(node.get(OWNER_PIN).asText());
        owner.setRole(node.get(OWNER_ROLE).asText());

        return owner;
    }

    private List<Employee> deserializeEmployeeList(JsonNode node){
        List<Employee> employeeList=new ArrayList<>();

        if(node.isArray()){
            for(JsonNode n:node){
                Employee employee=new Employee();
                employee.setName(n.get(EMPLOYEE_NAME).asText());

                Address address = deserializeAddress(n.get(ADDRESS));
                employee.setAddress(address);

                Contact contact = desirializeContact(n.get(CONTACT));
                employee.setContact(contact);

                employee.setPin(n.get(EMPLOYEE_PIN).asText());
                employee.setRole(n.get(EMPLOYEE_ROLE).asText());
                employee.setSalary(n.get(EMPLOYEE_SALARY).asInt());

                employeeList.add(employee);
            }
        }

        return employeeList;
    }

    private Contact desirializeContact(JsonNode node){
        Contact contact=new Contact();

        JsonNode phoneListNode=node.get(PHONE_LIST);
        if(phoneListNode.isArray()){
            for(JsonNode n:phoneListNode){
                contact.getPhone().add(n.toString());
            }
        }
        contact.setEmail(node.get(EMAIL).asText());

        return contact;
    }

    private Address deserializeAddress(JsonNode node){
        Address address=new Address();
        address.setCity(node.get(CITY).asText());
        address.setDistrict(node.get(DISTRICT).asText());
        address.setSub_district(node.get(SUB_DISTRICT).asText());
        address.setZip(node.get(ZIP).asInt());

        return address;
    }
}
