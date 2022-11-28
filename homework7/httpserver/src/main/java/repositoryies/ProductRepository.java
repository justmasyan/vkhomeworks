package repositoryies;

import repositoryies.funcinterfaces.DSLContextExecutor;
import repositoryies.funcinterfaces.DSLContextFetcher;
import repositoryies.funcinterfaces.ResultSetMapper;
import models.Product;

import java.util.ArrayList;
import java.util.List;

import static generated.Tables.FIRMS;
import static generated.Tables.PRODUCTS;

public class ProductRepository {

   public List<Product> getAll(){
       DSLContextFetcher fetcher = (context) ->{
           return context
                   .selectFrom(PRODUCTS)
                   .fetchResultSet();
       };
       ResultSetMapper<List<Product>> mapper = (set) ->{
         List<Product> list = new ArrayList<>();
         while(set.next()){
             Product product = new Product(set.getString("title"),set.getInt("company"),set.getInt("amount"));
             list.add(product);
         }
         return list;
       };

       return (List<Product>)RequestExecutor.query(DataBaseInitializer.DEFAULT,fetcher,mapper);
   }

   public Integer getCompanyId(String company){
        DSLContextFetcher fetcher = (context) ->{
            return context
                    .select(FIRMS.ID)
                    .from(FIRMS)
                    .where(FIRMS.TITLE.equal(company))
                    .fetchResultSet();
        };

        ResultSetMapper<Integer> mapper = (set) ->{
            if(set.next())
                return set.getInt("id");
            else
                return -1;

        };
        return (Integer) RequestExecutor.query(DataBaseInitializer.DEFAULT,fetcher,mapper);
   }

   public void addProduct(Product product){
        DSLContextExecutor executor = (context) ->{
            context
                    .insertInto(PRODUCTS,PRODUCTS.TITLE,PRODUCTS.COMPANY,PRODUCTS.AMOUNT)
                    .values(product.getTitle(), product.getCompanyId(), product.getAmount())
                    .execute();
        };

        RequestExecutor.update(DataBaseInitializer.DEFAULT,executor);
   }

    public void addProvider(String company){
        DSLContextExecutor executor = (context) ->{
            context
                    .insertInto(FIRMS,FIRMS.TITLE)
                    .values(company)
                    .execute();
        };

        RequestExecutor.update(DataBaseInitializer.DEFAULT,executor);
    }
}
