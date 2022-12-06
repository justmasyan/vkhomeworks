package serversentity;

import models.Product;

import java.util.List;

public class ContentGenerator {

    protected static String content() {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><title>Example</title></head>" +
                "<body><h1>" + "Welcome to the main page.\n List of commands:\n" +
                "<br>'/' - mainpage\n" +
                "<br>'/products' GET - get all products\n" +
                "<br>'/products' POST - add product (don`t forget arguments)\n" + "</h1></body>" +
                "</html>";
    }

    protected static String content(List<Product> list) {
        StringBuilder str =  new StringBuilder("<!DOCTYPE html>" +
                "<html>" +
                "<head><title>Example</title></head>" +
                "<body><h1>" + "List of products:\n");

        for(Product product:list) {
            str.append("<br> Product: " + product.getTitle());
            str.append(" Company: " + product.getCompanyId());
            str.append(" Amount: " + product.getAmount());
        }
         str.append("</h1></body>" +
                "</html>");
        return str.toString();
    }
}
