package com.roadrunner.pathfinder;

import java.io.IOException;

import com.roadrunner.pathfinder.service.PathFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PathFinderApplication extends SpringBootServletInitializer {
	public static void main(String[] args) throws IOException {

		SpringApplication.run(PathFinderApplication.class, args);

		PathFinder.loadPathFromFile(args[0]);
	}

}
