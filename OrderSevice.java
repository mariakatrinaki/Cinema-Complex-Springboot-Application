package gr.mariakapa.cinema.Service;

import gr.mariakapa.cinema.Entity.*;
import gr.mariakapa.cinema.Repository.OrderRepository;
import gr.mariakapa.cinema.Repository.UserRepository;
import gr.mariakapa.cinema.RequestResponse.OrderResponse;
import gr.mariakapa.cinema.RequestResponse.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderSevice {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;


    public void addOrder(Order order){
         orderRepository.save(order);
    }



    public Order saveOrder(Order order) {
        Order newOrder = new Order();
        newOrder.setOrderStatus(order.getOrderStatus());
        newOrder.setTotalCost(order.getTotalCost());
        newOrder.setUser(order.getUser());
        newOrder.getOrderDetails().addAll((order.getOrderDetails()
                .stream()
                .map(productOrder -> {
                    Product product = productService.findProductById(productOrder.getProduct().getIdProduct());
                   // OrderProduct orderProduct = OrderProductRepository.findByIdOrderAndIdProduct(Long id)

                    OrderProduct newOrderProduct = new OrderProduct();
                    newOrderProduct.setProduct(product);
                    newOrderProduct.setOrder(newOrder);
                    newOrderProduct.setQuantity(productOrder.getQuantity());
                    newOrderProduct.setCost(productOrder.getCost());
                    return newOrderProduct;
                })
                .collect(Collectors.toList())
        ));
        return orderRepository.save(newOrder);
    }








/*
    public Order saveOrder(Order order){
        Order newOrder  = new Order();
        newOrder.setUser(order.getUser());

        newOrder.setProducts(order
                .getProducts()
                .stream()
                .map( p-> {

                    Product pp = productService.findProductByName(p.getProductName());

                    pp.addOrder(newOrder);
                    return pp;

                }).collect(Collectors.toSet()));



        return orderRepository.save(newOrder);


    }


 */
    /*
   public List<OrderResponse> findOrderById(int id){
        List<User> listUser = userRepository.findAll();
        List<OrderResponse> listOrderResponse = new ArrayList<>();
        for(User u: listUser){
             u.getOrders().stream().forEach(o->{
                 if(o.getIdOrder()==id){
                     OrderResponse orderResponse= new OrderResponse();
                     orderResponse.setName(u.getName());
                     orderResponse.setProducts(o.getProducts());
                     listOrderResponse.add(orderResponse);
                 }
            });
        }

        return listOrderResponse;
   }

*/



    public List<UserResponse> findUserOrderById(int id){
        List<User> list = userRepository.findAll();
        List<UserResponse> listUsersResponse =  new ArrayList<>();
        List<Order> listOrders = new ArrayList<>() ;
        list.stream().
                forEach(u ->{
                    u.getOrders().stream().forEach(o->{
                        if(o.getIdOrder() == id){

                          listOrders.add(o);
                          UserResponse userResponse = new UserResponse();
                          userResponse.setIdUser(u.getIdUser());
                          userResponse.setName(u.getName());
                            userResponse.setName(u.getName());
                            userResponse.setUserName(u.getUserName());
                            userResponse.setPassword(u.getPassword());
                            userResponse.setType(u.getUsertype().getType());
                            userResponse.setOrders(Collections.singletonList(o.getIdOrder()));
                            listUsersResponse.add(userResponse);
                        }
                    });



                });
        return listUsersResponse;

    }

    public void deleteOrderById(@PathVariable int idOrder ){
        Order order = orderRepository.findOrderByIdOrder(idOrder);
        if(order.getOrderDetails().isEmpty()){
            orderRepository.deleteAllByIdOrder(order.getIdOrder());
        }

    }





}
