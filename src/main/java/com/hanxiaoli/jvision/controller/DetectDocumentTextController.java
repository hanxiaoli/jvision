package com.hanxiaoli.jvision.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageContext;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Vertex;
import com.google.cloud.vision.v1.Vertex.Builder;
import com.google.cloud.vision.v1.Word;
import com.google.protobuf.ByteString;
import com.hanxiaoli.jvision.model.Mynumber;
import com.hanxiaoli.jvision.model.Triangle;

@RestController
public class DetectDocumentTextController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/mynumber")
	public Mynumber handle() {

		Builder pointLeftBuilder = Vertex.newBuilder();
		pointLeftBuilder.setX(50);
		pointLeftBuilder.setY(50);

		Builder pointRightBuilder = Vertex.newBuilder();
		pointRightBuilder.setX(100);
		pointRightBuilder.setY(100);

		Triangle triangle = new Triangle(pointLeftBuilder.build(), pointRightBuilder.build());
		triangle.getDegree(triangle.getSin());

		Mynumber mynumber = new Mynumber();
		List<AnnotateImageRequest> requests = new ArrayList<>();

		ByteString imgBytes = ByteString.EMPTY;
		try {
			imgBytes = ByteString.readFrom(new FileInputStream(
					"C:\\Users\\hanxiaoli\\git\\jvision\\src\\main\\resources\\image\\4842_001.jpg"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Image img = Image.newBuilder().setContent(imgBytes).build();
		Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
		AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img)
				.setImageContext(ImageContext.newBuilder().addLanguageHints("ja")).build();
		requests.add(request);

		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();
			client.close();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					logger.error("Error: %s\n", res.getError().getMessage());
					return mynumber;
				}

				// For full list of available annotations, see http://g.co/cloud/vision/docs
				TextAnnotation annotation = res.getFullTextAnnotation();
				for (Page page : annotation.getPagesList()) {
					String pageText = "";
					for (Block block : page.getBlocksList()) {
						String blockText = "";
						for (Paragraph para : block.getParagraphsList()) {
							String paraText = "";
							for (Word word : para.getWordsList()) {
								String wordText = "";
								for (Symbol symbol : word.getSymbolsList()) {
									wordText = wordText + symbol.getText();
								}
								paraText = paraText + wordText;
							}
							// Output Example using Paragraph:
							logger.info("Paragraph: \n" + paraText);
							logger.info("Bounds: \n" + para.getBoundingBox() + "\n");
							blockText = blockText + paraText;
						}
						pageText = pageText + blockText;
					}
				}
				logger.info(annotation.getText());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mynumber;
	}

}
