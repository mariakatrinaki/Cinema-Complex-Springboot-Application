package gr.mariakapa.cinema.Controller;

import gr.mariakapa.cinema.Entity.*;
import gr.mariakapa.cinema.Repository.*;
import gr.mariakapa.cinema.RequestResponse.CartItemDao;
import gr.mariakapa.cinema.RequestResponse.OrderResponse;
import gr.mariakapa.cinema.RequestResponse.ShoppingCartDao;
import gr.mariakapa.cinema.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/")
public class ProductsController {

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;


    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;


    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    OrderSevice orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartItemService cartItemService;
    ShoppingCart shoppingCart;
    private static List<CartItem> cartItems = new ArrayList<>();


    @GetMapping("Products")
    public String productsPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());


        List<Product> products = productService.getProducts();

        OrderResponse orderResponse = new OrderResponse();


        model.addAttribute("orderResponse", new OrderResponse());

        model.addAttribute("productList", products);


        return "Visitor/Products";
    }


    @PostMapping("addProducts")
    public String addProducts(@ModelAttribute("product") OrderResponse orderResponse,
                            @RequestParam(name = "quantities", required = false) String[] quantities,

                            BindingResult result,
                            @AuthenticationPrincipal UserDetails currentUser, Model model) {

        User user = userRepository.findByUserName(currentUser.getUsername());



        Product product = null;

        int count = 0;


        for (int i = 0; i < quantities.length; i++) {

            System.out.println("Quantity: " + quantities[i]);
        }
        int i = 0;

        Map<Product, Integer> products = new HashMap<>();

        for (Product p : orderResponse.getProducts()) {

            for (i = count; i < quantities.length; i++) {
                if (!quantities[i].equals("")) {
                    if (Integer.parseInt(quantities[i]) > 0) {

                        product = productService.findProductById(p.getIdProduct());
                        product.setIdProduct(p.getIdProduct());
                        products.put(product, Integer.parseInt(quantities[i]));
                        count = i;

                        break;
                    }
                }


            }

            count = i + 1;
            System.out.println("\nCount: " + count);

        }


        for (Map.Entry<Product, Integer> me :
                products.entrySet()) {
            if (me.getValue() != 0) {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(me.getKey());
                cartItem.setQuantity(me.getValue());
                cartItem.setCost(me.getKey().getPrice() * (double) me.getValue());

                cartItems.add(cartItem);
            }

        }


        if (user.getShoppingCart() != null) {

            //fench the cart id for the particular user
            // do a search for cart id to shopping cart object
            // before save method call

            shoppingCart = shoppingCartRepository.findByIdShoppingCart(user.getShoppingCart().getIdShoppingCart());

            System.out.println("Shopping cart id: " + user.getShoppingCart().getIdShoppingCart());


            for (CartItem cartItem1 : cartItems) {

                cartItemService.saveCartItem(cartItem1, shoppingCart);

            }

            cartItems.clear();

            shoppingCartRepository.save(shoppingCart);

        } else if (user.getShoppingCart() == null) {

            shoppingCart = new ShoppingCart();


            shoppingCart.setUser(user);

            for (CartItem cartItem1 : cartItems) {


                cartItemService.saveCartItem(cartItem1, shoppingCart);


            }
            cartItems.clear();


            shoppingCartRepository.save(shoppingCart);

        }

        String url = "redirect:/Products";

        return url;
    }


    @GetMapping("ShoppingCartProducts")
    public String shoppingCart(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());
        List<Product> products;
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();


        if (user.getShoppingCart() != null) {
            shoppingCart = shoppingCartRepository.findByIdShoppingCart(user.getShoppingCart().getIdShoppingCart());

            List<CartItem> listCartItems = cartItemRepository.findByShoppingCart(shoppingCart);


            if (shoppingCart == null) {
                products = productRepository.findByCartItems_ShoppingCart_IdShoppingCart(0);
            } else {
                products = productRepository.findByCartItems_ShoppingCart_IdShoppingCart(shoppingCart.getIdShoppingCart());
            }

            List<CartItemDao> cartItemDaoList = new ArrayList<>();


            int totalCostCartItem = 0;
            for (CartItem item : listCartItems) {
                totalCostCartItem += item.getCost();

            }

            shoppingCartDao.setCartItems(listCartItems);
        }


        model.addAttribute("editShoppingCart", shoppingCartDao);


        return "Visitor/ShoppingCartProducts";
    }

    @PostMapping("editProduct")
    public String editProduct(Model model,

            @ModelAttribute("editShoppingCart") ShoppingCartDao editShoppingCart,
            @RequestParam(name = "ids", required = false) int[] ids,
            @RequestParam(value = "action", required = false) String action,
            @AuthenticationPrincipal UserDetails currentUser) {


        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());


        switch (action) {
            case "update":


                for (CartItem cartItem : editShoppingCart.getCartItems()) {
                    if (cartItem.getProduct() != null) {
                        cartItemRepository.updateAllByIdProduct(cartItem.getProduct().getIdProduct(),
                                cartItem.getQuantity(), user.getShoppingCart().getIdShoppingCart());
                        cartItemRepository.updateAllByIdProduct(cartItem.getProduct().getIdProduct(),
                                (double) cartItem.getQuantity() * cartItem.getProduct().getPrice(),
                                user.getShoppingCart().getIdShoppingCart());

                    }
                }
                // System.out.println("Update Total cost: "+updateTotalCost);
                break;
            case "delete":
                for (Integer i : ids) {
                    if (i != null) {
                        cartItemRepository.deleteAllByIdCartItem(user.getShoppingCart().getIdShoppingCart(), i);

                    }
                }


                break;


        }


        String url = "redirect:/ShoppingCartProducts";

        return url;
    }


    @PostMapping("createOrder")
    public String createOrder(@RequestParam(value = "action", required = false) String action,
                              @AuthenticationPrincipal UserDetails currentUser,
                              @ModelAttribute("editShoppingCart") ShoppingCartDao editShoppingCart
    ) {


        for (CartItem cartItem : editShoppingCart.getCartItems()) {
            if (cartItem.getProduct() != null) {
                System.out.println("Name: " + cartItem.getProduct().getProductName());
                System.out.println("Id: " + cartItem.getProduct().getIdProduct());
                System.out.println("Quantity: " + cartItem.getQuantity());
                System.out.println("Cost every item: " + cartItem.getCost());
            }
        }
        System.out.println("TotalCost: " + editShoppingCart.getTotalCost());

         User user = userRepository.findByUserName(currentUser.getUsername());
          shoppingCart = shoppingCartRepository.findByIdShoppingCart(user.getShoppingCart().getIdShoppingCart());

        List<CartItem> listCartItems = cartItemRepository.findByShoppingCart(shoppingCart);

           // orderService.addOrder(order);
            List<OrderProduct> orderProductList = new ArrayList<>();

        switch(action) {
            case "Pay":
             if(!editShoppingCart.getCartItems().isEmpty()) {
                 for (CartItem item : editShoppingCart.getCartItems()) {
                     orderProductList.add(new OrderProduct(item.getProduct(), item.getQuantity(),
                             item.getCost()));
                 }
                 Order order = new Order();
                 order.setUser(user);
                 order.setOrderDetails(orderProductList);
                 order.setOrderStatus("Paid");
                 order.setTotalCost(editShoppingCart.getTotalCost());

                 orderService.saveOrder(order);

                 cartItemRepository.deleteByShoppingCart(user.getShoppingCart().getIdShoppingCart());

             }

                break;
            case "Register":

                if(!listCartItems.isEmpty()) {
                    for (CartItem item : editShoppingCart.getCartItems()) {
                        orderProductList.add(new OrderProduct(item.getProduct(), item.getQuantity(),
                                item.getCost()));
                    }

                    Order order = new Order();
                    order.setUser(user);
                    order.setOrderDetails(orderProductList);
                    order.setOrderStatus("Not Paid");
                    order.setTotalCost(editShoppingCart.getTotalCost());

                    orderService.saveOrder(order);

                    cartItemRepository.deleteByShoppingCart(user.getShoppingCart().getIdShoppingCart());
                }

                break;


        }



        String url = "redirect:/ShoppingCartProducts";

        return url;

    }




    @GetMapping( "OrdersProducts")
    public String OrderProducts(Model model, @AuthenticationPrincipal UserDetails currentUser
                          ) {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        List<Order> orders = orderRepository.findByUser(user);
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());
        model.addAttribute("orders",orders);

        for(Order o :orders){
            for(OrderProduct or:o.getOrderDetails()){
                System.out.println(or.getProduct().getProductName());
            }
        }

       // model.addAttribute("order", new Order());


        return "Visitor/OrdersProducts";
    }



    @PostMapping("changeStatusOrder")
    public String changeStatusOrder(@AuthenticationPrincipal UserDetails currentUser,
                                          @RequestParam(value="action", required=false)  String action,
                                    @RequestParam("idOrder") int id){

        if(action.equals("Pay")){
            orderRepository.updateByIdOrder(id,"Paid");
        }





        String url = "redirect:/OrdersProducts";

        return url;

    }




}
