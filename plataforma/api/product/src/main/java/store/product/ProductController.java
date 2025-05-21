// ProductController.java
package store.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "product", url = "http://product:8080")
public interface ProductController {

    @PostMapping("/product")
    public ResponseEntity<ProductOut> create(
        @RequestBody ProductIn productIn
    );

    @GetMapping("/product")
    public ResponseEntity<List<ProductOut>> findAll();
    
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductOut> findById(
        @PathVariable("id") String id
    );
    
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteById(
        @PathVariable("id") String id
    );
    
    @GetMapping("/product/whoami")
    public ResponseEntity<ProductOut> whoami(
        @RequestHeader(value = "id-product", required = true) String idProduct
    );
}