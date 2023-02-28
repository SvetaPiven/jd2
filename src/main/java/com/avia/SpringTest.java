package com.avia;

import com.avia.repository.PassengerRepository;
import com.avia.service.PassengerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.noirix");

        //Object bean = applicationContext.getBean();
//        UserRepository repository = applicationContext.getBean("userRepository", UserRepository.class);
        PassengerRepository passengerRepository = applicationContext.getBean("passengerRepositoryImpl", PassengerRepository.class);
        PassengerService passengerService = applicationContext.getBean("passengerServiceImpl", PassengerService.class);

        System.out.println(passengerRepository.findAll());
        System.out.println(passengerService.findAll());
    }
}
