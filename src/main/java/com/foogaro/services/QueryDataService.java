package com.foogaro.services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class QueryDataService {

	private static final Logger log = Logger.getLogger(QueryDataService.class.getName());

	private static final String QUERY = "SELECT orders.orderNumber, orders.orderDate, orders.requiredDate, orders.shippedDate, orders.status, orders.customerNumber, customers.customerName, orderdetails.productCode, products.productName, orderdetails.quantityOrdered FROM orders JOIN customers ON orders.customerNumber = customers.customerNumber JOIN orderdetails ON orders.orderNumber = orderdetails.orderNumber JOIN products ON orderdetails.productCode = products.productCode, (SELECT SLEEP(?)) as sleep WHERE orders.orderNumber = ?";

	private ProgressBar progressBar;

	public QueryDataService() {
	}

	public void query() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl("jdbc:redis://localhost:6379");
		hikariConfig.setDriverClassName("com.redis.smartcache.Driver");
		hikariConfig.addDataSourceProperty("smartcache.driver.class-name", "com.mysql.cj.jdbc.Driver");
		hikariConfig.addDataSourceProperty("smartcache.driver.url", "jdbc:mysql://localhost:3306/redis");
		hikariConfig.setUsername("redis");
		hikariConfig.setPassword("redis");
		try (HikariDataSource dataSource = new HikariDataSource(hikariConfig)) {

			ExecutorService executor = Executors.newFixedThreadPool(10);

			final Random random = new Random();
			ProgressBarBuilder progressBarBuilder = new ProgressBarBuilder();
			progressBarBuilder.setTaskName("Querying");
			progressBarBuilder.showSpeed();
			progressBar = progressBarBuilder.build();
			for (int i = 1; i <= 100; i++) {
				final AtomicInteger index = new AtomicInteger(i);
				try {
					executor.submit(() -> {
						try (Connection connection = dataSource.getConnection();
							 PreparedStatement statement = connection.prepareStatement(QUERY)) {
							int orderNumber = index.getAndIncrement();
							statement.setInt(1, random.nextInt(5));
							statement.setInt(2, orderNumber);
							try (ResultSet resultSet = statement.executeQuery()) {
								while (resultSet.next()) {
									for (int col = 0; col < resultSet.getMetaData().getColumnCount(); col++) {
										resultSet.getObject(col + 1);
									}
								}
							}
							progressBar.step();
						} catch (SQLException e) {
							log.log(Level.SEVERE, "Could not run query", e);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
			}
			executor.shutdown();
		}
	}

}
