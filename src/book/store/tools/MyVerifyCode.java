package book.store.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class MyVerifyCode {

	public static String imageAndText(OutputStream output) throws IOException,
			FileNotFoundException {
		ArrayList<String> s=new ArrayList<String>();
		String[] fontName={"宋体","华文楷体","黑体","微软雅黑","楷体"};
		String realText="";
		for(int i=2;i<10;i++){
			s.add(i+"");
		}
		for(int i=97;i<123;i++){
			char a=(char)(i);
			s.add(a+"");
		}
		for(int i=65;i<91;i++){
			char a=(char)(i);
			s.add(a+"");
		}
		
		int num1=(int)(Math.random()*62);
		int num2=(int)(Math.random()*62);
		int num3=(int)(Math.random()*62);
		int num4=(int)(Math.random()*62);
		realText=s.get(num1)+s.get(num2)+s.get(num3)+s.get(num4);
		
		int locnum1x=(int)(Math.random()*27);
		int locnum1y=(int)(Math.random()*10+30);
		int locnum2x=(int)(Math.random()*28+37);
		int locnum2y=(int)(Math.random()*10+30);
		int locnum3x=(int)(Math.random()*28+75);
		int locnum3y=(int)(Math.random()*10+30);
		int locnum4x=(int)(Math.random()*20+112);
		int locnum4y=(int)(Math.random()*10+30);
		
		BufferedImage bi=new BufferedImage(150,50,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=(Graphics2D)bi.getGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 150, 50);
		
		
		int fontNum1=(int)(Math.random()*5);
		int fontNum2=(int)(Math.random()*5);
		int fontNum3=(int)(Math.random()*5);
		int fontNum4=(int)(Math.random()*5);
		
		int red=(int)(Math.random()*255);
		int green=(int)(Math.random()*255);
		int blue=(int)(Math.random()*255);
		g2.setColor(new Color(red,green,blue));
		g2.setFont(new Font(fontName[fontNum1],Font.PLAIN,40));
		g2.drawString(s.get(num1)+"", locnum1x, locnum1y);
		
		red=(int)(Math.random()*255);
		green=(int)(Math.random()*255);
		blue=(int)(Math.random()*255);
		g2.setColor(new Color(red,green,blue));
		g2.setFont(new Font(fontName[fontNum2],Font.PLAIN,40));
		g2.drawString(s.get(num2)+"", locnum2x, locnum2y);
		
		red=(int)(Math.random()*255);
		green=(int)(Math.random()*255);
		blue=(int)(Math.random()*255);
		g2.setColor(new Color(red,green,blue));
		g2.setFont(new Font(fontName[fontNum3],Font.PLAIN,40));
		g2.drawString(s.get(num3)+"", locnum3x, locnum3y);
		
		red=(int)(Math.random()*255);
		green=(int)(Math.random()*255);
		blue=(int)(Math.random()*255);
		g2.setColor(new Color(red,green,blue));
		g2.setFont(new Font(fontName[fontNum4],Font.PLAIN,40));
		g2.drawString(s.get(num4)+"", locnum4x, locnum4y);
		
		//3条干扰线
		g2.setColor(Color.black);
		g2.setFont(new Font(fontName[fontNum4],Font.BOLD,40));
		for(int i=0;i<3;i++){
			int qiX=(int)(Math.random()*150);
			int qiY=(int)(Math.random()*50);
			int zhongX=(int)(Math.random()*150);
			int zhongY=(int)(Math.random()*50);
			g2.drawLine(qiX,qiY,zhongX,zhongY);
		}
		
		ImageIO.write(bi, "JPEG", output);
		
		return realText;
	}



}
