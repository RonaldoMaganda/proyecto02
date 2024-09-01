package cursoSpringBoot.controllers;

import cursoSpringBoot.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Ronaldo Maganda", "Ronaldo", "contrasena123"),
            new Customer(456, "Martha Salinas", "MarthaS", "contrasena456"),
            new Customer(789, "Leslie Maganda", "LeslieM", "contrasena789"),
            new Customer(234, "Evelyn Maganda", "EveMs", "contrasena234")
    ));

    //@RequestMapping (method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){

        return ResponseEntity.ok(customers);
        // return customers;
    }

/*    @GetMapping("/{name}")
    public Customer getName(@PathVariable String name){
        for(Customer c : customers){
            if (c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }
*/
    /*   @GetMapping("/{Id}")
    public Customer getId(@PathVariable int Id){
        for(Customer c : customers){
            if (c.getID() == Id){
                return c;
            }
        }
        return null;
    }*/

    //@RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @GetMapping("/{username}")
    public ResponseEntity<?> getCliente(@PathVariable String username){
        for(Customer c : customers){
            if(c.getUsername().equalsIgnoreCase(username)){
                return ResponseEntity.ok(c);
                //return c;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Cliente no encontrado con username: " + username);
        //return null;
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente creado exitosamente: " + customer.getUsername());
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> putCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if(c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity
                        .ok("Cliente modificado con exitoso: " + customer.getUsername());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Cliente no encontrado: " + customer.getUsername());
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClientes(@PathVariable int id){
        for(Customer c : customers){
            if(c.getID() == id){
                customers.remove(c);
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("El cliente con el ID: " + id + " fue eliminado");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID: " + id + " no existe");

    }

    //@RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping
    public ResponseEntity<?> patchClientes(@RequestBody Customer customer){
        for(Customer c : customers){
            if(c.getID() == customer.getID()){
                if (customer.getName() != null){
                    c.setName(customer.getName());
                }
                if(customer.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }
                if (customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }
                return ResponseEntity
                        .ok("Cliente modificado satisfactoriamente: " + customer.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Cliente no encontrado con el ID: " + customer.getID());
    }

}
