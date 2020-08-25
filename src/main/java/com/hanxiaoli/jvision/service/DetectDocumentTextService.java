package com.hanxiaoli.jvision.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.BoundingPoly;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Vertex;
import com.google.cloud.vision.v1.Vertex.Builder;
import com.google.cloud.vision.v1.Word;
import com.hanxiaoli.jvision.model.Area;
import com.hanxiaoli.jvision.model.MynumberBack;
import com.hanxiaoli.jvision.model.MynumberFront;
import com.hanxiaoli.jvision.model.Triangle;

@Service
public class DetectDocumentTextService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static double cardWidth = 85.5;

	public static double cardHeight = 54;

	// simple
	private TextAnnotation annotation;

	// pages
	private Page simplePages;

	// blocks
	private List<Block> simpleBlocks;

	// symbols
	private List<Symbol> simpleSymbols;

	// 是否包含正面
	private boolean hasFront = false;

	// 是否包含背面
	private boolean hasBack = false;

	// 正面参照symbol array
	private Map<String, Symbol> comparativeSymbolsFront;

	// 背面参照symbol array
	private Map<String, Symbol> comparativeSymbolsBack;

	// 正面参照symbol
	private Symbol referenceSymbolFront;

	// 背面参照symbol
	private Symbol referenceSymbolBack;

	// 正面参照block
	private Block referenceBlockFront;

	// 背面参照block
	private Block referenceBlockBack;

	// 正面以x坐标计算得出的pix/mm
	private double widthPixMmFront = Double.NaN;

	// 正面以y坐标计算得出的pix/mm
	private double heightPixMmFront;

	// 背面以x坐标计算得出的pix/mm
	private double widthPixMmBack;

	// 背面以y坐标计算得出的pix/mm
	private double heightPixMmBack;

	//
	private double degreeFront;

	//
	private double degreeBack;

	public void handle(TextAnnotation annotation) {
		this.annotation = annotation;
		this.simplePages = annotation.getPages(0);
		this.simpleBlocks = annotation.getPages(0).getBlocksList();
		this.simpleSymbols = new ArrayList<>();
		this.comparativeSymbolsFront = new HashMap<>();
		this.comparativeSymbolsBack = new HashMap<>();

		int matchingTimesFront = 0;
		int matchingTimesBack = 0;

		for (Block block : annotation.getPages(0).getBlocksList()) {
			for (Paragraph paragraph : block.getParagraphsList()) {
				for (Word word : paragraph.getWordsList()) {
					for (Symbol symbol : word.getSymbolsList()) {
						simpleSymbols.add(symbol);

						if (new MynumberFront().getReferenceTextFront().contains(symbol.getText())) {
							this.comparativeSymbolsFront.put(symbol.getText(), symbol);
							if (null == this.referenceSymbolFront
									|| symbol.getConfidence() > this.referenceSymbolFront.getConfidence()) {
								this.referenceSymbolFront = symbol;
								this.referenceBlockFront = block;
							}
							matchingTimesFront++;
						}

						if (MynumberBack.referenceTextBack.contains(symbol.getText())) {
							this.comparativeSymbolsBack.put(symbol.getText(), symbol);
							if (null == this.referenceSymbolBack
									|| symbol.getConfidence() > this.referenceSymbolBack.getConfidence()) {
								this.referenceSymbolBack = symbol;
								this.referenceBlockBack = block;
							}
							matchingTimesBack++;
						}
					}
				}
			}

		}

		if (matchingTimesFront < 3 && matchingTimesBack < 3) {
			logger.warn("无法找到足够的参照物");
		}

		if (matchingTimesFront >= 3 && matchingTimesBack >= 3) {
			logger.warn("两面的识别请联系公司代表");
		}

		if (matchingTimesFront >= 3) {
			// TODO 记得测试完删掉
			// foreach ($instance->simpleSymbols as $abc) {
			// if ($abc->getText() === "停") {
			// $instance->referenceSymbolFront = $abc;
			// }
			// }
			// TODO END
			this.hasFront = true;

			logger.info("Reference symbol front: " + messageVertices(this.referenceSymbolFront.getBoundingBox()));
			logger.info(
					"Reference block front vertices: " + messageVertices(this.referenceBlockFront.getBoundingBox()));

			setWidthPixMmFront();
		} else {
			this.hasFront = false;
			this.referenceSymbolFront = null;
		}

		if (matchingTimesBack >= 3) {
			this.hasBack = true;

			logger.info("Reference symbol back: " + messageVertices(this.referenceSymbolBack.getBoundingBox()));
			logger.info("ReferenceBlockBack Bounds: " + messageVertices(this.referenceBlockBack.getBoundingBox()));
		} else {
			this.hasBack = false;
			this.referenceSymbolBack = null;
		}

	}

	/**
	 * 取得卡区域的剪切坐标
	 *
	 * @param Array $area
	 */
	public Map<String, Integer> cutArea(List<Vertex> vertices) {
		List<Integer> xs = new ArrayList<Integer>();
		List<Integer> ys = new ArrayList<Integer>();

		for (Vertex vertex : vertices) {
			xs.add(vertex.getX());
			ys.add(vertex.getY());
		}

		Map<String, Integer> result = new HashMap<String, Integer>();

		result.put("x", Collections.min(xs));
		result.put("y", Collections.min(ys));
		result.put("width", Collections.max(xs) - Collections.min(xs));
		result.put("height", Collections.max(ys) - Collections.min(ys));

		return result;
	}

	public static String messageVertices(BoundingPoly boundingBox) {
		String message = "";
		for (Vertex vertex : boundingBox.getVerticesList()) {
			message = message + "(" + vertex.getX() + ", " + vertex.getY() + ")";
		}

		return message;
	}

	public static String messageVertices(List<Vertex> vertices) {
		String message = "";
		for (Vertex vertex : vertices) {
			message = message + "(" + vertex.getX() + ", " + vertex.getY() + ")";
		}

		return message;
	}

	/**
	 *
	 * @param mixed $widthPixMmFront
	 */
	public void setWidthPixMmFront() {
		double widthPixMmFront = Double.NaN;
		Map<String, Double> comparativeSymbols = new MynumberFront().getComparativeSymbolsDistance()
				.get(this.referenceSymbolFront.getText()).get("width");

		for (Entry<String, Double> comparativeSymbol : comparativeSymbols.entrySet()) {
			if (this.comparativeSymbolsFront.containsKey(comparativeSymbol.getKey())) {
				logger.info("comparative symbol:" + messageVertices(
						this.comparativeSymbolsFront.get(comparativeSymbol.getKey()).getBoundingBox()));
			}
			;

			Triangle triangle = new Triangle(this.referenceSymbolFront.getBoundingBox().getVertices(0),
					this.comparativeSymbolsFront.get(comparativeSymbol.getKey()).getBoundingBox().getVertices(0));

			widthPixMmFront = triangle.getHypotenuse() / comparativeSymbol.getValue();
			logger.info("widthPixMmFront:" + widthPixMmFront);
			break;
		}

		if (Double.NaN == widthPixMmFront) {
			logger.info("setWidthPixMmFront error 1");
		}

		this.widthPixMmFront = widthPixMmFront;
	}

	/**
	 * 取得某区域到参照物之间的距离（像素单位）
	 *
	 * @param Simple $simple
	 * @param String $areaType
	 */
	public Area distanceFromReferenceSymbol(String areaType) {
		MynumberFront mynumberFront = new MynumberFront();

		Triangle widthTriangleBlock = new Triangle(this.getReferenceBlockFront().getBoundingBox().getVertices(0),
				this.getReferenceBlockFront().getBoundingBox().getVertices(1));

		Triangle heightTriangleBlock = new Triangle(this.getReferenceBlockFront().getBoundingBox().getVertices(0),
				this.getReferenceBlockFront().getBoundingBox().getVertices(3));

		double widthPixMm = getWidthPixMmFront();
		Symbol referenceSymbol = getReferenceSymbolFront();

		double hypotenuse = widthPixMm
				* mynumberFront.getLengthToReferenceSymbol().get(referenceSymbol.getText()).get(areaType).getLeft();
		double leftX = referenceSymbol.getBoundingBox().getVertices(0).getX()
				- widthTriangleBlock.getCos() * hypotenuse;
		double leftY = referenceSymbol.getBoundingBox().getVertices(0).getY()
				- widthTriangleBlock.getSin() * hypotenuse;
		Builder leftBuilder = Vertex.newBuilder();
		leftBuilder.setX((int) leftX);
		leftBuilder.setY((int) leftY);

		hypotenuse = widthPixMm
				* mynumberFront.getLengthToReferenceSymbol().get(referenceSymbol.getText()).get(areaType).getUp();
		double upX = referenceSymbol.getBoundingBox().getVertices(0).getX() - heightTriangleBlock.getCos() * hypotenuse;
		double upY = referenceSymbol.getBoundingBox().getVertices(0).getY() - heightTriangleBlock.getSin() * hypotenuse;
		Builder upBuilder = Vertex.newBuilder();
		upBuilder.setX((int) upX);
		upBuilder.setY((int) upY);

		hypotenuse = widthPixMm
				* mynumberFront.getLengthToReferenceSymbol().get(referenceSymbol.getText()).get(areaType).getRight();
		double rightX = referenceSymbol.getBoundingBox().getVertices(0).getX()
				+ widthTriangleBlock.getCos() * hypotenuse;
		double rightY = referenceSymbol.getBoundingBox().getVertices(0).getY()
				+ widthTriangleBlock.getSin() * hypotenuse;
		Builder rightBuilder = Vertex.newBuilder();
		rightBuilder.setX((int) rightX);
		rightBuilder.setY((int) rightY);

		hypotenuse = widthPixMm
				* mynumberFront.getLengthToReferenceSymbol().get(referenceSymbol.getText()).get(areaType).getDown();
		double downX = referenceSymbol.getBoundingBox().getVertices(0).getX()
				+ heightTriangleBlock.getCos() * hypotenuse;
		double downY = referenceSymbol.getBoundingBox().getVertices(0).getY()
				+ heightTriangleBlock.getSin() * hypotenuse;
		Builder downBuilder = Vertex.newBuilder();
		downBuilder.setX((int) downX);
		downBuilder.setY((int) downY);

		Area area = new Area();
		area.setAreaName(areaType);
		area.setLeftUp(leftBuilder.build());
		area.setRightUp(upBuilder.build());
		area.setRightDown(rightBuilder.build());
		area.setLeftDown(downBuilder.build());

		return area;
	}

	/**
	 * 取得某区域的四点坐标
	 *
	 * @param Simple $simple
	 * @param String $areaType
	 */
	public List<Vertex> getArea(String areaType) {
		Area referenceArea = this.distanceFromReferenceSymbol(areaType);
		Symbol referenceSymbol = this.getReferenceSymbolFront();

		Triangle triangleLeftUp = new Triangle(referenceArea.getLeftUp(),
				referenceSymbol.getBoundingBox().getVertices(0));
		Triangle triangleRightUp = new Triangle(referenceArea.getRightUp(),
				referenceSymbol.getBoundingBox().getVertices(0));
		Triangle triangleRightDown = new Triangle(referenceArea.getRightDown(),
				referenceSymbol.getBoundingBox().getVertices(0));
		Triangle triangleLeftDown = new Triangle(referenceArea.getLeftDown(),
				referenceSymbol.getBoundingBox().getVertices(0));

		List<Vertex> areas = new ArrayList<>();
		areas.add(new Triangle(triangleLeftUp.getAdjacent(), triangleLeftUp.getOpposite(), null,
				referenceArea.getRightUp()).getPointLeft());
		areas.add(new Triangle(triangleRightUp.getAdjacent(), triangleRightUp.getOpposite(), null,
				referenceArea.getRightDown()).getPointLeft());
		areas.add(new Triangle(triangleRightDown.getAdjacent(), triangleRightDown.getOpposite(), null,
				referenceArea.getLeftDown()).getPointLeft());
		areas.add(new Triangle(triangleLeftDown.getAdjacent(), triangleLeftDown.getOpposite(), null,
				referenceArea.getLeftUp()).getPointLeft());

		for (Vertex vertex : areas) {
			if (0 > vertex.getX()) {
				Builder vertexBuilder = Vertex.newBuilder();
				vertexBuilder.setX(0);
				vertexBuilder.setY(vertex.getY());

				vertex = vertexBuilder.build();
			}

			if (this.getSimplePages().getWidth() < vertex.getX()) {
				Builder vertexBuilder = Vertex.newBuilder();
				vertexBuilder.setX(this.getSimplePages().getWidth());
				vertexBuilder.setY(vertex.getY());

				vertex = vertexBuilder.build();
			}

			if (0 > vertex.getY()) {
				Builder vertexBuilder = Vertex.newBuilder();
				vertexBuilder.setX(vertex.getX());
				vertexBuilder.setY(0);

				vertex = vertexBuilder.build();
			}

			if (this.getSimplePages().getHeight() < vertex.getY()) {
				Builder vertexBuilder = Vertex.newBuilder();
				vertexBuilder.setX(vertex.getX());
				vertexBuilder.setY(this.getSimplePages().getHeight());

				vertex = vertexBuilder.build();
			}

		}

		return areas;
	}

	/**
	 * 取得某区域内的内容（画面辅正后）
	 *
	 * @param Simple $simple
	 * @param String $areaType
	 */
	public String getContent(String areaType) {
		List<Symbol> symbols = this.getSimpleSymbols();
		List<Vertex> area = getArea(areaType);
		List<Symbol> content = new ArrayList<>();
		String text = "";

		for (Symbol symbol : symbols) {
			List<Vertex> vertices = symbol.getBoundingBox().getVerticesList();
			if (vertices.get(0).getX() >= area.get(0).getX() && vertices.get(1).getX() <= area.get(1).getX()
					&& vertices.get(0).getY() >= area.get(0).getY() && vertices.get(3).getY() <= area.get(3).getY()) {
				content.add(symbol);
			}
		}

		content.sort(new Comparator<Symbol>() {
			@Override
			public int compare(Symbol o1, Symbol o2) {
				int o1x = o1.getBoundingBox().getVertices(0).getX();
				int o2x = o2.getBoundingBox().getVertices(0).getX();
				if (o1x == o2x) {
					return 0;
				}
				return o1x < o2x ? -1 : 1;
			}
		});

		content = sortContent(content);

		for (Symbol symbol : content) {
			text = text + symbol.getText();

			if (null != symbol.getProperty() && null != symbol.getProperty().getDetectedBreak()) {
				text = text + " ";
			}
		}

		return text;
	}

	/**
	 * 整理内容的文字顺序
	 *
	 * @param Simple $simple
	 */
	public List<Symbol> sortContent(List<Symbol> content) {
		Map<Integer, List<Symbol>> lines = new TreeMap<>();
		List<Symbol> sorted = new ArrayList<>();

		for (Symbol symbol : content) {
			List<Vertex> vertices = symbol.getBoundingBox().getVerticesList();
			boolean set = false;

			for (Entry<Integer, List<Symbol>> entry : lines.entrySet()) {
				if (vertices.get(3).getY() > entry.getValue().get(0).getBoundingBox().getVertices(0).getY()
						&& vertices.get(0).getY() < entry.getValue().get(0).getBoundingBox().getVertices(3).getY()) {
					entry.getValue().add(symbol);
					set = true;
					break;
				}
			}

			if (!set) {
				lines.put(vertices.get(0).getY(), Arrays.asList(symbol));
			}

			for (Entry<Integer, List<Symbol>> entry : lines.entrySet()) {
				sorted.addAll(entry.getValue());
			}

		}

		return sorted;
	}

	/**
	 * @return the cardWidth
	 */
	public static double getCardWidth() {
		return cardWidth;
	}

	/**
	 * @param cardWidth the cardWidth to set
	 */
	public static void setCardWidth(double cardWidth) {
		DetectDocumentTextService.cardWidth = cardWidth;
	}

	/**
	 * @return the cardHeight
	 */
	public static double getCardHeight() {
		return cardHeight;
	}

	/**
	 * @param cardHeight the cardHeight to set
	 */
	public static void setCardHeight(double cardHeight) {
		DetectDocumentTextService.cardHeight = cardHeight;
	}

	/**
	 * @return the annotation
	 */
	public TextAnnotation getAnnotation() {
		return annotation;
	}

	/**
	 * @param annotation the annotation to set
	 */
	public void setAnnotation(TextAnnotation annotation) {
		this.annotation = annotation;
	}

	/**
	 * @return the simplePages
	 */
	public Page getSimplePages() {
		return simplePages;
	}

	/**
	 * @param simplePages the simplePages to set
	 */
	public void setSimplePages(Page simplePages) {
		this.simplePages = simplePages;
	}

	/**
	 * @return the simpleBlocks
	 */
	public List<Block> getSimpleBlocks() {
		return simpleBlocks;
	}

	/**
	 * @param simpleBlocks the simpleBlocks to set
	 */
	public void setSimpleBlocks(List<Block> simpleBlocks) {
		this.simpleBlocks = simpleBlocks;
	}

	/**
	 * @return the simpleSymbols
	 */
	public List<Symbol> getSimpleSymbols() {
		return simpleSymbols;
	}

	/**
	 * @param simpleSymbols the simpleSymbols to set
	 */
	public void setSimpleSymbols(List<Symbol> simpleSymbols) {
		this.simpleSymbols = simpleSymbols;
	}

	/**
	 * @return the hasFront
	 */
	public boolean isHasFront() {
		return hasFront;
	}

	/**
	 * @param hasFront the hasFront to set
	 */
	public void setHasFront(boolean hasFront) {
		this.hasFront = hasFront;
	}

	/**
	 * @return the hasBack
	 */
	public boolean isHasBack() {
		return hasBack;
	}

	/**
	 * @param hasBack the hasBack to set
	 */
	public void setHasBack(boolean hasBack) {
		this.hasBack = hasBack;
	}

	/**
	 * @return the comparativeSymbolsFront
	 */
	public Map<String, Symbol> getComparativeSymbolsFront() {
		return comparativeSymbolsFront;
	}

	/**
	 * @param comparativeSymbolsFront the comparativeSymbolsFront to set
	 */
	public void setComparativeSymbolsFront(Map<String, Symbol> comparativeSymbolsFront) {
		this.comparativeSymbolsFront = comparativeSymbolsFront;
	}

	/**
	 * @return the comparativeSymbolsBack
	 */
	public Map<String, Symbol> getComparativeSymbolsBack() {
		return comparativeSymbolsBack;
	}

	/**
	 * @param comparativeSymbolsBack the comparativeSymbolsBack to set
	 */
	public void setComparativeSymbolsBack(Map<String, Symbol> comparativeSymbolsBack) {
		this.comparativeSymbolsBack = comparativeSymbolsBack;
	}

	/**
	 * @return the referenceSymbolFront
	 */
	public Symbol getReferenceSymbolFront() {
		return referenceSymbolFront;
	}

	/**
	 * @param referenceSymbolFront the referenceSymbolFront to set
	 */
	public void setReferenceSymbolFront(Symbol referenceSymbolFront) {
		this.referenceSymbolFront = referenceSymbolFront;
	}

	/**
	 * @return the referenceSymbolBack
	 */
	public Symbol getReferenceSymbolBack() {
		return referenceSymbolBack;
	}

	/**
	 * @param referenceSymbolBack the referenceSymbolBack to set
	 */
	public void setReferenceSymbolBack(Symbol referenceSymbolBack) {
		this.referenceSymbolBack = referenceSymbolBack;
	}

	/**
	 * @return the referenceBlockFront
	 */
	public Block getReferenceBlockFront() {
		return referenceBlockFront;
	}

	/**
	 * @param referenceBlockFront the referenceBlockFront to set
	 */
	public void setReferenceBlockFront(Block referenceBlockFront) {
		this.referenceBlockFront = referenceBlockFront;
	}

	/**
	 * @return the referenceBlockBack
	 */
	public Block getReferenceBlockBack() {
		return referenceBlockBack;
	}

	/**
	 * @param referenceBlockBack the referenceBlockBack to set
	 */
	public void setReferenceBlockBack(Block referenceBlockBack) {
		this.referenceBlockBack = referenceBlockBack;
	}

	/**
	 * @return the widthPixMmFront
	 */
	public double getWidthPixMmFront() {
		return widthPixMmFront;
	}

	/**
	 * @param widthPixMmFront the widthPixMmFront to set
	 */
	public void setWidthPixMmFront(double widthPixMmFront) {
		this.widthPixMmFront = widthPixMmFront;
	}

	/**
	 * @return the heightPixMmFront
	 */
	public double getHeightPixMmFront() {
		return heightPixMmFront;
	}

	/**
	 * @param heightPixMmFront the heightPixMmFront to set
	 */
	public void setHeightPixMmFront(double heightPixMmFront) {
		this.heightPixMmFront = heightPixMmFront;
	}

	/**
	 * @return the widthPixMmBack
	 */
	public double getWidthPixMmBack() {
		return widthPixMmBack;
	}

	/**
	 * @param widthPixMmBack the widthPixMmBack to set
	 */
	public void setWidthPixMmBack(double widthPixMmBack) {
		this.widthPixMmBack = widthPixMmBack;
	}

	/**
	 * @return the heightPixMmBack
	 */
	public double getHeightPixMmBack() {
		return heightPixMmBack;
	}

	/**
	 * @param heightPixMmBack the heightPixMmBack to set
	 */
	public void setHeightPixMmBack(double heightPixMmBack) {
		this.heightPixMmBack = heightPixMmBack;
	}

	/**
	 * @return the degreeFront
	 */
	public double getDegreeFront() {
		return degreeFront;
	}

	/**
	 * @param degreeFront the degreeFront to set
	 */
	public void setDegreeFront(double degreeFront) {
		this.degreeFront = degreeFront;
	}

	/**
	 * @return the degreeBack
	 */
	public double getDegreeBack() {
		return degreeBack;
	}

	/**
	 * @param degreeBack the degreeBack to set
	 */
	public void setDegreeBack(double degreeBack) {
		this.degreeBack = degreeBack;
	}

}
