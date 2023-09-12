package com.hakim.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hakim.pojo.*;

import java.io.IOException;
import java.util.List;

public class MySerializerImpl extends JsonSerializer<Shop> {
    public static final String NAME = "shop_name";
    public static final String TOTAL_PRODUCT = "total_product";
    public static final String CAPITAL = "shop_capital";
    public static final String LOCATION = "shop_location";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String DISTRICT = "district";
    public static final String SUB_DISTRICT = "sub_district";
    public static final String ZIP = "zip";

    public static final String OWNER = "shop_owner";
    public static final String OWNER_NAME = "owner_name";
    public static final String OWNER_PIN = "owner_pin";
    public static final String OWNER_ROLE = "owner_role";

    public static final String EMPLOYEE_LIST = "employee_list";
    public static final String EMPLOYEE_NAME = "employee_name";
    public static final String EMPLOYEE_PIN = "employee_pin";
    public static final String EMPLOYEE_ROLE = "employee_role";
    public static final String EMPLOYEE_SALARY = "employee_salary";

    public static final String CONTACT = "contact";
    public static final String PHONE_LIST = "phone_list";
    public static final String EMAIL = "email";

    public static final String TRANSACTION = "shop_transaction";
    public static final String TOTAL_INCOME = "total_income";
    public static final String TOTAL_COST = "total_cost";
    public static final String WORKDAY = "workday";
    public static final String PROFIT = "profit";
    public static final String SELL_HISTORY = "sell_history";
    public static final String BUY_HISTORY = "buy_history";
    public static final String HISTORY_AMOUNT = "amount";
    public static final String HISTORY_DATE = "date";
    public static final String HISTORY_PROFIT = "profit";
    public static final String HISTORY_PERSON = "seller";

    public static final String GIVEN_SALARY = "given_salary";
    public static final String SALARY_AMOUNT = "salary_amount";
    public static final String GIVEN_DATA = "given_date";

    @Override
    public void serialize(Shop shop, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(NAME, shop.getName());
        jsonGenerator.writeNumberField(CAPITAL, shop.getCapital());
        jsonGenerator.writeNumberField(TOTAL_PRODUCT, shop.getTotal_product());

        jsonGenerator.writeFieldName(LOCATION);
        serializeAddress(shop.getLocation(), jsonGenerator);

        jsonGenerator.writeFieldName(OWNER);
        serializeOwner(shop.getOwner(), jsonGenerator);

        jsonGenerator.writeFieldName(EMPLOYEE_LIST);
        serializeEmployeeList(shop.getEmployees(), jsonGenerator);

        jsonGenerator.writeFieldName(GIVEN_SALARY);
        serializeSalaryList(shop.getGiven_salary(), jsonGenerator);

        jsonGenerator.writeFieldName(TRANSACTION);
        serializeTransaction(shop.getTransaction(), jsonGenerator);

        jsonGenerator.writeEndObject();
    }

    private void serializeSalaryList(List<Salary> salaryList, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (Salary salary : salaryList) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField(EMPLOYEE_NAME, salary.getEmployee_name());
            jsonGenerator.writeNumberField(SALARY_AMOUNT, salary.getSalary());
            jsonGenerator.writeStringField(GIVEN_DATA, salary.getGiven_date());

            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }

    private void serializeTransaction(Transaction transaction, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField(TOTAL_COST, transaction.getTotalCost());
        jsonGenerator.writeNumberField(TOTAL_INCOME, transaction.getTotalIncome());
        jsonGenerator.writeNumberField(PROFIT, transaction.getProfit());
        jsonGenerator.writeStringField(WORKDAY, transaction.getWorkday());

        jsonGenerator.writeFieldName(SELL_HISTORY);
        jsonGenerator.writeStartArray();
        for (TransactionHistory history : transaction.getSellHistory()) {
            serializeTransactionHistory(history, jsonGenerator);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeFieldName(BUY_HISTORY);
        jsonGenerator.writeStartArray();
        for (TransactionHistory history : transaction.getBuyHistory()) {
            serializeTransactionHistory(history, jsonGenerator);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }

    private void serializeTransactionHistory(TransactionHistory history, JsonGenerator jsonGenerator) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField(HISTORY_AMOUNT, history.getAmount());
        jsonGenerator.writeStringField(HISTORY_DATE, history.getDate());
        if (history instanceof SellHistory sellHistory) {
            jsonGenerator.writeStringField(HISTORY_PERSON, sellHistory.getSeller_name());
            jsonGenerator.writeNumberField(HISTORY_PROFIT, sellHistory.getProfit());
        }
        jsonGenerator.writeEndObject();

    }

    private void serializeAddress(Address address, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField(CITY, address.getCity());
        jsonGenerator.writeStringField(DISTRICT, address.getDistrict());
        jsonGenerator.writeStringField(SUB_DISTRICT, address.getSub_district());
        jsonGenerator.writeNumberField(ZIP, address.getZip());

        jsonGenerator.writeEndObject();
    }

    private void serializeContact(Contact contact, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeFieldName(PHONE_LIST);
        jsonGenerator.writeStartArray();
        for (String num : contact.getPhone()) {
            jsonGenerator.writeString(num);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeStringField(EMAIL, contact.getEmail());
        jsonGenerator.writeEndObject();
    }

    private void serializeOwner(Owner owner, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField(OWNER_NAME, owner.getName());

        jsonGenerator.writeFieldName(ADDRESS);
        serializeAddress(owner.getAddress(), jsonGenerator);

        jsonGenerator.writeFieldName(CONTACT);
        serializeContact(owner.getContact(), jsonGenerator);

        jsonGenerator.writeStringField(OWNER_PIN, owner.getPin());
        jsonGenerator.writeStringField(OWNER_ROLE, owner.getRole());

        jsonGenerator.writeEndObject();
    }

    private void serializeEmployee(Employee employee, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField(EMPLOYEE_NAME, employee.getName());

        jsonGenerator.writeFieldName(ADDRESS);
        serializeAddress(employee.getAddress(), jsonGenerator);

        jsonGenerator.writeFieldName(CONTACT);
        serializeContact(employee.getContact(), jsonGenerator);

        jsonGenerator.writeStringField(EMPLOYEE_PIN, employee.getPin());
        jsonGenerator.writeStringField(EMPLOYEE_ROLE, employee.getRole());
        jsonGenerator.writeNumberField(EMPLOYEE_SALARY, employee.getSalary());

        jsonGenerator.writeEndObject();
    }

    private void serializeEmployeeList(List<Employee> employees, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (Employee employee : employees) {
            serializeEmployee(employee, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }
}
