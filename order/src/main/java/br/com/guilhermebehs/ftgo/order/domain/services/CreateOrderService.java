package br.com.guilhermebehs.ftgo.order.domain.services;

import br.com.guilhermebehs.ftgo.order.domain.commands.ValidateOrderPaymentCommand;
import br.com.guilhermebehs.ftgo.order.domain.entities.Address;
import br.com.guilhermebehs.ftgo.order.domain.entities.Order;
import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.CreateOrderDto;
import br.com.guilhermebehs.ftgo.order.domain.entities.exceptions.InvalidItemException;
import br.com.guilhermebehs.ftgo.order.domain.ports.KitchenService;
import br.com.guilhermebehs.ftgo.order.domain.ports.OrderRepository;
import br.com.guilhermebehs.ftgo.order.domain.ports.ValidateOrderPaymentNotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class CreateOrderService {

    private final KitchenService kitchenService;
    private final OrderRepository orderRepository;
    private final ValidateOrderPaymentNotificationService validateOrderPaymentNotificationService;

    public CreateOrderService(KitchenService kitchenService,
                              OrderRepository orderRepository,
                              ValidateOrderPaymentNotificationService validateOrderPaymentNotificationService) {
        this.kitchenService = kitchenService;
        this.orderRepository = orderRepository;
        this.validateOrderPaymentNotificationService = validateOrderPaymentNotificationService;
    }

    public String create(CreateOrderDto createOrderDto){

        var orderId = Order.generateId();

        var address = new Address(
                createOrderDto.getCustomerAddress().getDescription(),
                createOrderDto.getCustomerAddress().getNeighbourhood(),
                createOrderDto.getCustomerAddress().getComplement(),
                createOrderDto.getCustomerAddress().getCity(),
                createOrderDto.getCustomerAddress().getState(),
                createOrderDto.getCustomerAddress().getCountry()
        );

        var items = createOrderDto.getItems()
                .stream()
                .map(item -> new OrderItem(item.getDescription(), item.getPrice(), item.getAmount()))
                .collect(Collectors.toList());

        var newOrder = new Order(
                orderId,
                createOrderDto.getCustomerName(),
                address,
                LocalDateTime.now(),
                createOrderDto.getDeliveryDateForecast(),
                createOrderDto.getKitchen(),
                items,
                createOrderDto.getCreditCard()
        );


        for(OrderItem orderItem: newOrder.getItems()){
            var item = kitchenService
                    .getProductByNameAndKitchen(orderItem.getDescription(), createOrderDto.getKitchen())
                    .orElseThrow(()-> new InvalidItemException("Product '"+orderItem.getDescription()+"' not found"));

            if(!item.isActive() || item.getAvailableAmount() < orderItem.getAmount())
                throw new InvalidItemException("Product '"+orderItem.getDescription()+"' not available");
        }

        orderRepository.save(newOrder);

        var validateOrderPaymentCommand = new ValidateOrderPaymentCommand(
                newOrder.getOrderId(),
                newOrder.getCreditCard(),
                newOrder.calculateTotalPrice(),
                newOrder.getCustomerName(),
                LocalDateTime.now()
        );

        validateOrderPaymentNotificationService.notify(validateOrderPaymentCommand);

        for(OrderItem orderItem: newOrder.getItems()) {
            kitchenService.bookProductAmount(orderItem.getDescription(), createOrderDto.getKitchen(), orderItem.getAmount());
        }

        return orderId;

    }
}
