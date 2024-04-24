package com.foogaro.services;

import com.foogaro.entities.*;
import com.foogaro.repositories.CustomerRepository;
import com.foogaro.repositories.OrderDetailsRepository;
import com.foogaro.repositories.OrderRepository;
import com.foogaro.repositories.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Component
public class LoadDataService {

	private static final Logger log = Logger.getLogger(LoadDataService.class.getName());

	private final int CUSTOMERS = 1000;
	private final int PRODUCTS = 1000;
	private final int ORDERS = 100;
	private final int ORDER_DETAILS = 1_000;

	public LoadDataService() {
	}

	public void loadAllData() {
		log.info("Initializing database.");
		loadSomeCustomers(CUSTOMERS);
		log.info("Customers loaded.");
		loadSomeProducts(PRODUCTS);
		log.info("Products loaded.");
		loadSomeOrders(ORDERS);
		log.info("Orders loaded.");
		loadSomeOrderDetails(ORDER_DETAILS);
		log.info("Order Details loaded.");
		log.info("Database initialized!");
	}

	@Autowired
	private CustomerRepository customerRepository;
	public void loadSomeCustomers(int numberOfCustomers) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Faker faker = new Faker();
		for (int i = 0; i < numberOfCustomers; i++) {
			AtomicInteger index = new AtomicInteger(i);
			try {
				executor.submit(() -> {
					Customer customer = new Customer();
					customer.setId(index.incrementAndGet());
					customer.setCustomerName(faker.company().name());
					customer.setContactFirstName(faker.name().firstName());
					customer.setContactLastName(faker.name().lastName());
					customer.setPhone(faker.phoneNumber().phoneNumber());
					customer.setAddressLine1(faker.address().streetAddress());
					customer.setAddressLine2(faker.address().secondaryAddress());
					customer.setPostalCode(faker.address().zipCode());
					customer.setCountry(faker.address().country());
					customerRepository.save(customer);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			// wait for all tasks to finish
		}
	}

	@Autowired
	private ProductRepository productRepository;
	public void loadSomeProducts(int numberOfProducts) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Faker faker = new Faker();

		String[] PRODUCT_SCALES = { "1:10", "1:12", "1:16", "1:18" };
		String[] PRODUCT_LINES = { "Classic Cars", "Motorcycles", "Planes", "Ships", "Trains", "Trucks and Buses", "Vintage Cars" };
		for (int i = 0; i < numberOfProducts; i++) {
			AtomicInteger index = new AtomicInteger(i);
			try {
				executor.submit(() -> {
					Product product = new Product();
					product.setId(index.incrementAndGet());
					product.setProductName(faker.commerce().productName());
					product.setQuantityInStock(faker.random().nextInt(5, 5000));
					product.setMsrp(faker.number().randomDouble(2, 15, 1000));
					product.setBuyPrice(product.getMsrp() * .6);
					product.setProductVendor(faker.company().name());
					product.setProductLine(PRODUCT_LINES[faker.random().nextInt(PRODUCT_LINES.length)]);
					product.setProductScale(PRODUCT_SCALES[faker.random().nextInt(PRODUCT_SCALES.length)]);
					product.setProductDescription(faker.lorem().paragraph(2));
					productRepository.save(product);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			// wait for all tasks to finish
		}
	}

	@Autowired
	private OrderRepository orderRepository;
	public void loadSomeOrders(int numberOfOrders) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Faker faker = new Faker();

		String ORDER_STATUS_SHIPPED = "Shipped";
		String[] ORDER_STATUSES = { "In Process", ORDER_STATUS_SHIPPED, "Disputed", "Resolved" };

		for (int i = 0; i < numberOfOrders; i++) {
			AtomicInteger index = new AtomicInteger(i);
			try {
				executor.submit(() -> {
					Date orderDate = faker.date().past(750, 1, TimeUnit.DAYS);
					Calendar requiredDate = Calendar.getInstance();
					requiredDate.setTime(orderDate);
					requiredDate.add(Calendar.DAY_OF_MONTH, faker.random().nextInt(3, 10));
					String status = ORDER_STATUSES[faker.random().nextInt(ORDER_STATUSES.length)];
					Date shippedDate = status.equals(ORDER_STATUS_SHIPPED) ? requiredDate.getTime() : null;
					int customerNumber = faker.random().nextInt(1, CUSTOMERS);
					Customer customer = new Customer();
					customer.setId(customerNumber);

					Order order = new Order();
					order.setId(index.incrementAndGet());
					order.setOrderDate(sqlDate(orderDate));
					order.setRequiredDate(sqlDate(requiredDate.getTime()));
					order.setShippedDate(sqlDate(shippedDate));
					order.setStatus(status);
					order.setCustomerNumber(customer);
					orderRepository.save(order);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			// wait for all tasks to finish
		}
	}

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	public void loadSomeOrderDetails(int numberOfOrderDetails) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Faker faker = new Faker();

		for (int i = 1; i <= numberOfOrderDetails; i++) {
			try {
				executor.submit(() -> {
					int orderNumber = faker.random().nextInt(1, ORDERS);
					int productCode = faker.random().nextInt(1, PRODUCTS);

					OrderDetailId orderDetailId = new OrderDetailId();
					orderDetailId.setOrderNumber(orderNumber);
					orderDetailId.setProductCode(productCode);
					Order order = new Order();
					order.setId(orderNumber);
					Product product = new Product();
					product.setId(productCode);

					OrderDetails orderDetails = new OrderDetails();
					orderDetails.setId(orderDetailId);
					orderDetails.setOrderNumber(order);
					orderDetails.setProductCode(product);
					orderDetails.setQuantityOrdered(faker.random().nextInt(1, 10));
					orderDetails.setOrderLineNumber(1);
					orderDetails.setPriceEach(faker.number().randomDouble(2, 15, 1000));
					orderDetailsRepository.save(orderDetails);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			// wait for all tasks to finish
		}
	}


	private java.sql.Date sqlDate(Date date) {
		if (date == null) {
			return null;
		}
		return java.sql.Date.valueOf(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}

}
