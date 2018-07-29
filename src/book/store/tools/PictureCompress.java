package book.store.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PictureCompress {
	private Image img;  
    private int width;  
    private int height;
    private String newFile;
    @SuppressWarnings("deprecation")  
    public static void main(String[] args) throws Exception {  
        File file = new File("G:\\fate5.jpg");
        PictureCompress imgCom = new PictureCompress(file, "G:\\fate6.jpg");  
        imgCom.resizeFix(100, 100);  
    }  
    /** 
     * ���캯�� 
     */  
    public PictureCompress(String fileName, String newFile) throws IOException {  
        File file = new File(fileName);// �����ļ�  
        img = ImageIO.read(file);      // ����Image����  
        width = img.getWidth(null);    // �õ�Դͼ��  
        height = img.getHeight(null);  // �õ�Դͼ��  
        this.newFile = newFile;		   // ѹ�����ļ���λ��
    }
    
    public PictureCompress(File file, String newFile) throws IOException {  
        img = ImageIO.read(file);      // ����Image����  
        width = img.getWidth(null);    // �õ�Դͼ��  
        height = img.getHeight(null);  // �õ�Դͼ��  
        this.newFile = newFile;		   // ѹ�����ļ���λ��
    }
    /** 
     * ���տ�Ȼ��Ǹ߶Ƚ���ѹ�� 
     * @param w int ����� 
     * @param h int ���߶� 
     */  
    public void resizeFix(int w, int h) throws IOException {  
        if ((width / height) > (w / h)) {  
//            resizeByWidth(w);  
            resizeByHeight(h);
        } else {  
//            resizeByHeight(h);  
            resizeByWidth(w);
        }  
    }  
    /** 
     * �Կ��Ϊ��׼���ȱ�������ͼƬ 
     * @param w int �¿�� 
     */  
    public void resizeByWidth(int w) throws IOException {  
        int h = (int) (height * w / width);
        System.out.println(w + " 1 " + h);
        resize(w, h);  
        
    }  
    /** 
     * �Ը߶�Ϊ��׼���ȱ�������ͼƬ 
     * @param h int �¸߶� 
     */  
    public void resizeByHeight(int h) throws IOException {  
        int w = (int) (width * h / height);  
        System.out.println(w + " 2 " + h);
        resize(w, h);  
    }  
    /** 
     * ǿ��ѹ��/�Ŵ�ͼƬ���̶��Ĵ�С 
     * @param w int �¿�� 
     * @param h int �¸߶� 
     */  
    public void resize(int w, int h) throws IOException {  
        // SCALE_SMOOTH �������㷨 ��������ͼƬ��ƽ���ȵ� ���ȼ����ٶȸ� ���ɵ�ͼƬ�����ȽϺ� ���ٶ���  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // ������С���ͼ  
        File destFile = new File(newFile);  
        FileOutputStream out = new FileOutputStream(destFile); // ������ļ���  
        // ��������ʵ��bmp��png��gifתjpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG����  
        out.close();  
    }  
}
