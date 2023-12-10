package com.mahilamandal;

import com.mahilamandal.utils.Logger;
import com.mahilamandal.utils.enums.PrintType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MahalaMandalaApplication {
	public static void main(String[] args) {
		SpringApplication.run(MahalaMandalaApplication.class, args);
		Logger.EnableLogger();
		Logger.printMessage("Application Strted", PrintType.None);
	}
}