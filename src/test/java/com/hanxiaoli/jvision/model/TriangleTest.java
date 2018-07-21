package com.hanxiaoli.jvision.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.cloud.vision.v1.Vertex;
import com.google.cloud.vision.v1.Vertex.Builder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TriangleTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void testGetDegree() {

		Builder pointLeftBuilder = Vertex.newBuilder();
		pointLeftBuilder.setX(50);
		pointLeftBuilder.setY(50);

		Builder pointRightBuilder = Vertex.newBuilder();
		pointRightBuilder.setX(100);
		pointRightBuilder.setY(100);

		Triangle triangle = new Triangle(pointLeftBuilder.build(), pointRightBuilder.build());
		double degree = triangle.getDegree(triangle.getSin());

		logger.info(Double.toString(degree));

//		fail("Not yet implemented");
	}

}
