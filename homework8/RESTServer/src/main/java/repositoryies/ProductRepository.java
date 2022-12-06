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
                   .select(PRODUCTS.TITLE.as("product"),
                           FIRMS.TITLE.as("company"),
                           PRODUCTS.AMOUNT)
                   .from(PRODUCTS)
                   .innerJoin(FIRMS).on(PRODUCTS.COMPANY.equal(FIRMS.ID))
                   .fetchResultSet();
       };
       ResultSetMapper<List<Product>> mapper = (set) ->{
         List<Product> list = new ArrayList<>();
         while(set.next()){
             Product product = new Product(set.getString("product"),set.getString("company"),set.getInt("amount"));
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

   public void addProduct(Product product,int idProduct){
        DSLContextExecutor executor = (context) ->{
            context
                    .insertInto(PRODUCTS,PRODUCTS.TITLE,PRODUCTS.COMPANY,PRODUCTS.AMOUNT)
                    .values(product.title, idProduct, product.amount)
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

    public Boolean productIsExist(String product){
        DSLContextFetcher fetcher = (context) ->{
            return context
                    .selectFrom(PRODUCTS)
                    .where(PRODUCTS.TITLE.equal(product))
                    .fetchResultSet();
        };

        ResultSetMapper<Boolean> mapper = (set) ->{
           return set.next();
        };

        return (Boolean) RequestExecutor.query(DataBaseInitializer.DEFAULT,fetcher,mapper);
    }

    public void deleteProductByTitle(String product){
        DSLContextExecutor executor = (context) ->{
            context
                    .deleteFrom(PRODUCTS)
                    .where(PRODUCTS.TITLE.equal(product))
                    .execute();
        };
        RequestExecutor.update(DataBaseInitializer.DEFAULT,executor);
    }

    public List<Product> allProductsOfCompany(String company){

       DSLContextFetcher fetcher = (context) ->{
            return
                    context
                            .select(PRODUCTS.TITLE.as("product"),
                            FIRMS.TITLE.as("company"),
                            PRODUCTS.AMOUNT)
                            .from(PRODUCTS)
                            .innerJoin(FIRMS).on(PRODUCTS.COMPANY.equal(FIRMS.ID))
                            .where(FIRMS.TITLE.equal(company))
                            .fetchResultSet();

       };

       ResultSetMapper<List<Product>> mapper = (set) ->{
           List<Product> list = new ArrayList<>();
           while(set.next()){
               Product product = new Product(set.getString("product"),set.getString("company"),set.getInt("amount"));
               list.add(product);
           }
           return list;
       };

       return (List<Product>) RequestExecutor.query(DataBaseInitializer.DEFAULT,fetcher,mapper);
    }
}
