package com.digitallife.invoice;

import com.digitallife.invoice.service.inter.InvoiceServiceInter;
import com.digitallife.invoice.service.inter.ProjectServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run(){
//		CommandLineRunner commandLineRunner = new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				Project project = projectServiceInter.getProjectById(11);
//
//				InvoiceAmount amount = invoiceServiceInter.getAllAmount(project);
//				System.out.println(amount.getNetAmount());
//				System.out.println(amount.getTaxAmount());
//				System.out.println(amount.getTotalAmount());
//			}
//		};
//		return commandLineRunner;
//	}

}
