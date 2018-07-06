package com.hanxiaoli.jvision.model;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.cloud.vision.v1.Vertex;
import com.google.cloud.vision.v1.Vertex.Builder;

public class Triangle {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Map<Integer, Double> sin;

	private Vertex pointLeft;

	private Vertex pointRight;

	private double hypotenuse;

	private double opposite;

	private double adjacent;

	public Triangle(Vertex pointLeft, Vertex pointRight) {
		this.pointLeft = pointLeft;
		this.pointRight = pointRight;
		this.adjacent = pointRight.getX() - pointLeft.getX();
		this.opposite = pointRight.getY() - pointLeft.getY();
		this.hypotenuse = Math.sqrt(this.adjacent * this.adjacent + this.opposite * this.opposite);
	}

	public Triangle(double adjacent, double opposite, Vertex pointLeft, Vertex pointRight) {
		this.adjacent = adjacent;
		this.opposite = opposite;

		if (null == pointLeft && null != pointRight) {
			Builder builder = Vertex.newBuilder();
			builder.setX((int) (pointRight.getX() - adjacent));
			builder.setY((int) (pointRight.getY() - opposite));

			this.pointLeft = builder.build();
			this.pointRight = pointRight;
		} else if (null != pointLeft && null == pointRight) {
			Builder builder = Vertex.newBuilder();
			builder.setX((int) (pointLeft.getX() + adjacent));
			builder.setY((int) (pointLeft.getY() + opposite));

			this.pointLeft = pointLeft;
			this.pointRight = builder.build();
		} else {
			logger.error("create triangle with line was wrong");
		}
	}

	public int getDegree(double sin) {
		int startIndex = Integer.MIN_VALUE;
		int endIndex = Integer.MIN_VALUE;
		if (0 < this.adjacent && 0 <= this.opposite) {
			startIndex = 0;
			endIndex = 90;
		} else if (0 >= this.adjacent && 0 < this.opposite) {
			startIndex = 90;
			endIndex = 180;
		} else if (0 > this.adjacent && 0 >= this.opposite) {
			startIndex = 180;
			endIndex = 270;
		} else {
			startIndex = 270;
			endIndex = 360;
		}

		int index = Integer.MIN_VALUE;
		double closest = Double.NaN;
		for (int i = startIndex; i <= endIndex; i++) {
			if (Double.NaN == closest || Math.abs(sin - closest) > Math.abs(this.sin.get(i) - sin)) {
				index = i;
				closest = this.sin.get(i);
			}
		}

		logger.info("block degree: " + index);
		return index;
	}

	public double getSin() {
		return this.opposite / this.hypotenuse;
	}

	public double getCos() {
		return this.adjacent / this.hypotenuse;
	}

	public double getTan() {
		return this.opposite / this.adjacent;
	}

	public double getCot() {
		return this.adjacent / this.opposite;
	}

	public Vertex getPointLeft() {
		return pointLeft;
	}

	public void setPointLeft(Vertex pointLeft) {
		this.pointLeft = pointLeft;
	}

	public Vertex getPointRight() {
		return pointRight;
	}

	public void setPointRight(Vertex pointRight) {
		this.pointRight = pointRight;
	}

	public double getHypotenuse() {
		return hypotenuse;
	}

	public void setHypotenuse(double hypotenuse) {
		this.hypotenuse = hypotenuse;
	}

	public double getOpposite() {
		return opposite;
	}

	public void setOpposite(double opposite) {
		this.opposite = opposite;
	}

	public double getAdjacent() {
		return adjacent;
	}

	public void setAdjacent(double adjacent) {
		this.adjacent = adjacent;
	}

}
