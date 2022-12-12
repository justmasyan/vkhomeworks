package serversentity;

import models.Product;
import repositoryies.ProductRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class ResourceController {

    private final ProductRepository repository = new ProductRepository();

    @GET
    public String mainPage(){
        return ContentGenerator.content();
    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allProductsOfCompany(@QueryParam("company") String company){
        List<Product> list;
        if(company == null)
            list = repository.getAll();
        else
            list = repository.allProductsOfCompany(company);

        return Response.ok(list)
                .build();
    }

    @POST
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(Product product){
        int companyId = repository.getCompanyId(product.company);

        if (companyId == -1) {
            repository.addProvider(product.company);
            companyId = repository.getCompanyId(product.company);
        }

        repository.addProduct(product,companyId);
    }

    @POST
    @Path("/delete")
    public Response deleteProductByTitle(@QueryParam("title") String title){
        if(repository.productIsExist(title)) {
            repository.deleteProductByTitle(title);
            return Response.ok()
                    .build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

    }

}
