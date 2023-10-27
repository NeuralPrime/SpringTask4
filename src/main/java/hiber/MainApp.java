package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User();
      user1.setFirstName("Alex");
      user1.setLastName("Pushkin");
      user1.setEmail("7642@mail.ru");
      User user2 = new User();
      user2.setFirstName("Ivan");
      user2.setLastName("Petrov");
      user2.setEmail("13134@mail.ru");
      User user3 = new User();
      user3.setFirstName("Petr");
      user3.setLastName("Ivanov");
      user3.setEmail("654@mail.ru");
      User user4 = new User();
      user4.setFirstName("Marya");
      user4.setLastName("Sidorova");
      user4.setEmail("655456@mail.ru");

      Car car1 = new Car();
      car1.setModel("Shcoda");
      car1.setSeries(2020);
      Car car2 = new Car();
      car2.setModel("Toyota");
      car2.setSeries(2023);
      Car car3 = new Car();
      car3.setModel("Nissan");
      car3.setSeries(2014);
      Car car4 = new Car();
      car4.setModel("BMV");
      car4.setSeries(2018);

      user1.setCar(car1);
      car1.setUser(user1);
      user2.setCar(car2);
      car2.setUser(user2);
      user3.setCar(car3);
      car3.setUser(user3);
      user4.setCar(car4);
      car4.setUser(user4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      System.out.println("-----------------------------");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user + " " + user.getCar());
      }
      System.out.println("-----------------------------");
      System.out.println(userService.getUserByCarModelAndSeries("BMV", 2018));
      System.out.println("-----------------------------");
      try {
         System.out.println(userService.getUserByCarModelAndSeries("BV", 18));
      } catch (NoResultException e) {
         System.out.println("not found");
      }
      context.close();
   }
}