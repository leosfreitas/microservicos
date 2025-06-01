// OrderItemIn.java
package store.order;

import java.io.Serializable;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder 
@Accessors(fluent = true)
public record OrderItemIn(
    String idProduct,
    Integer quantity
) implements Serializable {
    
}