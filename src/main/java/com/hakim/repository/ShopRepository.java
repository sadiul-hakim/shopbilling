package com.hakim.repository;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hakim.pojo.*;
import com.hakim.utils.MyDeserializerImpl;
import com.hakim.utils.MySerializerImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShopRepository {
    private  final File file = new File("D:\\java_code\\shopBilling\\src\\main\\java\\com\\hakim\\data.json");
    //    private  final File file = new File(resource != null ? resource.toString().substring(6)+File.separator+"hakim"+File.separator+"data.json" : "");
    private  final ObjectMapper mapper = new ObjectMapper();
    private final Shop shop;
    public ShopRepository() throws IOException {
        shop = deserializeShop();
    }
    private  Shop deserializeShop() throws IOException {
        SimpleModule module = new SimpleModule("MyDeserializerImpl", new Version(0, 0, 0, null, null, null));
        module.addDeserializer(Shop.class, new MyDeserializerImpl());
        mapper.registerModule(module);

        return mapper.readValue(file, Shop.class);
    }

    public Shop getShop(){
        return shop;
    }

    public void serializeInitialData() throws IOException {
        Owner hakim = new Owner();
        hakim.setName("Sadiul Hakim");
        hakim.setAddress(new Address("Kushtia", "Kushtia", "Kumarkhali", 7010));
        hakim.setContact(new Contact(List.of("88016374869008"), "hakim@gmail.com"));
        hakim.setPin("hakim@123");
        hakim.setRole("Owner");

        Employee employee1 = new Employee();
        employee1.setName("Rohim Hossain");
        employee1.setAddress(new Address("Kushtia", "Kushtia", "Kumarkhali", 7010));
        employee1.setContact(new Contact(List.of("88017234567"), null));
        employee1.setPin("rohim@123");
        employee1.setRole("Employee");

        Employee employee2 = new Employee();
        employee2.setName("Korim Hossain");
        employee2.setAddress(new Address("Kushtia", "Kushtia", "Kumarkhali", 7010));
        employee2.setContact(new Contact(List.of("88017234567"), null));
        employee2.setPin("korim@123");
        employee2.setRole("Employee");

        Transaction transaction=new Transaction();
        transaction.setTotalCost(10_000);
        transaction.setTotalIncome(15_000);

        Shop shop = new Shop();
        shop.setOwner(hakim);
        shop.setLocation(new Address("Kushtia", "Kushtia", "Kumarkhali", 7010));
        shop.setEmployees(List.of(employee1, employee2));
        shop.setTransaction(transaction);

        SimpleModule module = new SimpleModule("MySerializerImpl", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Shop.class, new MySerializerImpl());
        mapper.registerModule(module);
        mapper.writeValue(file, shop);
    }

    public  void updateJson(Shop shop) throws IOException {
        SimpleModule module = new SimpleModule("MySerializerImpl", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Shop.class, new MySerializerImpl());
        mapper.registerModule(module);
        mapper.writeValue(file, shop);
    }
    
}
