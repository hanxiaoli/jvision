package com.hanxiaoli.jvision.model;

import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.Vertex;
import com.google.cloud.vision.v1.Vertex.Builder;

public class Area {

	private String areaName;

	private Vertex leftUp;

	private Vertex rightUp;

	private Vertex rightDown;

	private Vertex leftDown;

	private double leftLength;

	private double rightLength;

	private double upLength;

	private double downLength;

	// mm
	private double left;

	// mm
	private double right;

	// mm
	private double up;

	// mm
	private double down;

	public Area() {
	}

	public Area(Symbol referenceSymbol) {
		Builder pointLeftBuilder = Vertex.newBuilder();
		pointLeftBuilder.setX(50);
		pointLeftBuilder.setY(50);
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Vertex getLeftUp() {
		return leftUp;
	}

	public void setLeftUp(Vertex leftUp) {
		this.leftUp = leftUp;
	}

	public Vertex getRightUp() {
		return rightUp;
	}

	public void setRightUp(Vertex rightUp) {
		this.rightUp = rightUp;
	}

	public Vertex getRightDown() {
		return rightDown;
	}

	public void setRightDown(Vertex rightDown) {
		this.rightDown = rightDown;
	}

	public Vertex getLeftDown() {
		return leftDown;
	}

	public void setLeftDown(Vertex leftDown) {
		this.leftDown = leftDown;
	}

	public double getLeftLength() {
		return leftLength;
	}

	public void setLeftLength(double leftLength) {
		this.leftLength = leftLength;
	}

	public double getRightLength() {
		return rightLength;
	}

	public void setRightLength(double rightLength) {
		this.rightLength = rightLength;
	}

	public double getUpLength() {
		return upLength;
	}

	public void setUpLength(double upLength) {
		this.upLength = upLength;
	}

	public double getDownLength() {
		return downLength;
	}

	public void setDownLength(double downLength) {
		this.downLength = downLength;
	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		this.right = right;
	}

	public double getUp() {
		return up;
	}

	public void setUp(double up) {
		this.up = up;
	}

	public double getDown() {
		return down;
	}

	public void setDown(double down) {
		this.down = down;
	}

}
