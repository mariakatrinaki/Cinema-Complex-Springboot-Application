package gr.mariakapa.cinema.Service;

import gr.mariakapa.cinema.Entity.Product;
import gr.mariakapa.cinema.Repository.OrderRepository;
import gr.mariakapa.cinema.Repository.ProductRepository;
import gr.mariakapa.cinema.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    @Autowired
    OrderRepository orderRepository;


    public Product saveProduct(Product product){
        return productRepository.save(product);

    }


    public List<Product> getProducts(){

        return productRepository.findAll();
    }
    public Product findProductById(int productId){

        return productRepository.findAllByIdProduct(productId);
    }

    public Product findProductByName(String productName){

        return productRepository.findAllByProductName(productName);
    }

    public void deleteProductById(int idOrder,int idProduct){




        productRepository.deleteAllByIdProduct(idOrder,idProduct);
    }


    public boolean isFound(int id){
       boolean found = false;

        Product product = productRepository.findAllByIdProduct(id);
        if(product!=null){
            found= true;
        }
        return found;
    }

}
