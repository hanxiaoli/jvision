package com.hanxiaoli.jvision.utility;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageUtility {

	public static BufferedImage rotateImage(BufferedImage src, double degree) {

		 int w= src.getWidth();// 得到图片宽度。
		 int h= src.getHeight();// 得到图片高度。
		 int type= src.getColorModel().getTransparency();// 得到图片透明度。
		 BufferedImage img;// 空的图片。
		 Graphics2D graphics2d;// 空的画笔。
		 (graphics2d= (img= new BufferedImage(w, h, type))
		 .createGraphics()).setRenderingHint(
		 RenderingHints.KEY_INTERPOLATION,
		 RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		 graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);// 旋转，degree是整型，度数，比如垂直90度。
		 graphics2d.drawImage(src, 0, 0, null);// 从bufferedimagecopy图片至img，0,0是img的坐标。
		 graphics2d.dispose();
		 return img;// 返回复制好的图片，原图片依然没有变，没有旋转，下次还可以使用。
	}
}
