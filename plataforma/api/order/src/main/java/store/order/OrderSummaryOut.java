
package store.order;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder 
@Accessors(fluent = true)
public record OrderSummaryOut(
    String id,
    LocalDateTime date,
    Double total
) implements Serializable {
    
}