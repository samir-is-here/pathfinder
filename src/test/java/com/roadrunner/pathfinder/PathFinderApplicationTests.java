package com.roadrunner.pathfinder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.roadrunner.pathfinder.service.PathFinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PathFinderApplicationTests {

	@Test
	public void testPath() throws IOException {

		PathFinder.loadPathFromFile("./city.txt");

		assertFalse(PathFinder.isPathExists("Albany", "Philadelphia"));

	}

	@Test
	public void testPath1() throws IOException {
		PathFinder.loadPathFromFile("./city.txt");
		assertTrue(PathFinder.isPathExists("Boston", "Newark"));

	}

	@Test
	public void testPath2() throws IOException {
		PathFinder.loadPathFromFile("./city.txt");
		assertTrue(PathFinder.isPathExists("Boston", "Philadelphia"));
	}

}
