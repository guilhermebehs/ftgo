package br.com.guilhermebehs.ftgo.order.domain.entities.dtos;

import br.com.guilhermebehs.ftgo.order.domain.entities.Address;
import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderDto {

    @NotBlank(message = "invalid value for 'customer_name'")
    private String customerName;

    @Valid
    @NotNull(message = "invalid value for 'customer_address'")
    private AddressDto customerAddress;

    @NotNull(message = "invalid value for 'delivery_date_forecast'")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime deliveryDateForecast;

    @Valid
    @NotEmpty(message = "invalid value for 'items'")
    private List<OrderItemDto> items;

    @NotBlank(message = "invalid value for 'kitchen'")
    private String kitchen;
}
