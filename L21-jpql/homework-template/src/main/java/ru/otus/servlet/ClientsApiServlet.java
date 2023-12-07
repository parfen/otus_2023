package ru.otus.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Phone;
import ru.otus.crm.service.DBServiceClient;
import ru.otus.services.TemplateProcessor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientsApiServlet extends HttpServlet {

    private static final int ID_PATH_PARAM_POSITION = 1;
    private static final String TEMPLATE_ATTR_CLIENT = "clients";
    private static final String USERS_PAGE_TEMPLATE = "clients.html";
    private final DBServiceClient clientDao;
    public ClientsApiServlet(DBServiceClient clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Client> clients = clientDao.findAll();
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        StringBuilder tableBody= new StringBuilder();
        for(Client c: clients){
            tableBody.append("</tr>");
            tableBody.append("<td>"+c.getId()+"</td>");
            tableBody.append("<td>"+c.getName()+"</td>");
            tableBody.append("<td>"+c.getAddress().getStreet()+"</td>");
            List<Phone> phones = c.getPhones();
            for(Phone p :phones){
                tableBody.append("<td>"+p.getNumber()+"</td>");
            }
            tableBody.append("</tr>");
        }
        out.print(tableBody.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Client cl = new Client(null,name,new Address(null,address), List.of(new Phone(null, phone)));
        clientDao.saveClient(cl);
    }

}
