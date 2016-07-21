package com.yougou.kaidian.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 商品图片工具类
 * 
 * @author huang.tao
 *
 */
public class PictureUtil {
	
	// private static final Log log = LogFactory.getLog(PictureUtil.class);
	
	// 定义图片后缀 【 详情页左侧图、缩略图、手机图、手机图】
	private final static String[] SUFFIX = new String[] { "_l.jpg", "_m.jpg", "_t.jpg", "_mb.jpg", "_ms.jpg" };
	// 颜色图、列表图、后台程序图
	private final static String[] SUFFIX_F = new String[] { "_c.jpg", "_s.jpg", "_u.jpg" };
	
	public static Map<String, String> createImageNameList(String id, String serialNo, String commodityNo) {
		Map<String, String> imageNames = new HashMap<String, String>();
		for (String suffix : SUFFIX) {
			imageNames.put(id + "_" + serialNo + suffix, commodityNo + "_" + serialNo + suffix);
		}
		
		return imageNames;
	}
	
	/**
	 * 创建颜色小图列表
	 * 
	 * @param id
	 * @param serialNo
	 * @param commodityNo
	 * @return
	 */
	public static Map<String, String> createImageNameList_F(String id, String commodityNo) {
		Map<String, String> imageNames = new HashMap<String, String>();
		for (String suffix : SUFFIX_F) {
			imageNames.put(id + "_01" + suffix, commodityNo + "_01" + suffix);
		}
		
		return imageNames;
	}
	
	/**
	 * 在图片目录创建文件名
	 * 
	 * @param 文件名
	 * @param merchantCode 商家编码
	 * @return String
	 */
	public static String createTemporaryPicname(String picdir,String filename, String merchantCode) {
		File picDir=new File(picdir+File.separator+merchantCode+File.separator);
		if(!picDir.exists()){
			picDir.mkdirs();
		}
		return (new File(picDir,ObjectUtils.defaultIfNull(filename, new java.rmi.server.UID().toString().replaceAll("(:|-)", "_") + ".jpg").toString())).getAbsolutePath();
	}
	
	/**
	 * 在指定目录创建文件名
	 * 
	 * @param 文件名
	 * @return String
	 */
	public static String createTemporaryPicname(String picdir,String filename) {
		return new StringBuilder()
		.append(picdir)
		.append(File.separator)
		.append(ObjectUtils.defaultIfNull(filename, new java.rmi.server.UID().toString().replaceAll("(:|-)", "_") + ".jpg"))
		.toString();
	}
	
	/**
	 * 替换图片名称 uuid_01_l.jpg ==> xx_01_l.jpg
	 * 
	 * @param str
	 * @return
	 */
	public static String imageReplaceNameByStr(String fileName, String str) {
		if (StringUtils.isBlank(fileName)) {
			return null;
		}
		String[] arrays = fileName.split("_"); 
		if (arrays.length != 3) {
			return fileName;
		}
		
		return new StringBuilder().append(str).append("_").append(arrays[1]).append("_").append(arrays[2]).toString();
	}
	
	/**
	 * 标准化图片URL
	 * 
	 * @param imageUrl
	 * @return String
	 */
	public static String normalizeImageUrl(String imageUrl) {
		return imageUrl == null ? imageUrl : imageUrl.replaceAll("(?<!http:)/{2,}", "/");
	}
	
	/**
	 * 从图片URL中提取名称
	 * 
	 * @return String
	 */
	public static String extractNameFromImageUrl(String imageUrl) {
		if (imageUrl == null) {
			return imageUrl;
		}
		
		int index = imageUrl.lastIndexOf("/");
		if (index == -1) {
			return imageUrl;
		} else {
			imageUrl = imageUrl.substring(index + 1);
		}
		
		index = imageUrl.indexOf("?");
		return index == -1 ? imageUrl : imageUrl.substring(0, index);
	}
	
	public static File createThumbnailFile(InputStream source,String thumbnailSrc) throws IOException {
        Image src=Toolkit.getDefaultToolkit().createImage(IOUtils.toByteArray(source));
        BufferedImage image=null;
        if (image instanceof BufferedImage) {
        	image= (BufferedImage)image;
        }else{
            src = new ImageIcon(src).getImage();
            image = new BufferedImage(src.getWidth(null), src.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, null);
            g.dispose();
        }
        
        int w=image.getWidth(null);
        int h=image.getHeight(null);

        //计算缩略图
        int nw=128;
        int nh=128;
        if(w>h){
        	nh=(nw*h)/w;
        }else{
        	nw=(nh*w)/h;
        }                   
        BufferedImage tag=new BufferedImage(nw,nh,BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(image.getScaledInstance(nw, nh, Image.SCALE_SMOOTH), 0, 0, null);
        File thumbnailFile=new File(thumbnailSrc);
        try {
            FileOutputStream out=new FileOutputStream(thumbnailFile);
            JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();
		} catch (IOException e) {
			throw e;
		}finally{
	        tag.flush();
	        image.flush();
	        src.flush();
		}
		return thumbnailFile;
	}
	/**
	 * 在临时目录创建图片缩略图
	 * 
	 * @param 文件名
	 * @return String
	 * @throws IOException 
	 */
	public static File createThumbnailFile(String source) throws IOException {
        Image src=Toolkit.getDefaultToolkit().getImage(source);
        BufferedImage image=null;
        if (image instanceof BufferedImage) {
        	image= (BufferedImage)image;
        }else{
            src = new ImageIcon(src).getImage();
            image = new BufferedImage(src.getWidth(null), src.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, null);
            g.dispose();
        }
        
        int w=image.getWidth(null);
        int h=image.getHeight(null);

        //计算缩略图
        int nw=128;
        int nh=128;
        if(w>h){
        	nh=(nw*h)/w;
        }else{
        	nw=(nh*w)/h;
        }                   
        BufferedImage tag=new BufferedImage(nw,nh,BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(image.getScaledInstance(nw, nh, Image.SCALE_SMOOTH), 0, 0, null);
        File thumbnailFile=new File(source.replaceAll("\\.jpg$", ".png"));
        try {
            FileOutputStream out=new FileOutputStream(thumbnailFile);
            JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();
		} catch (IOException e) {
			throw e;
		}finally{
	        tag.flush();
	        image.flush();
	        src.flush();
		}
		return thumbnailFile;
	}
	
	/**
	 * 分隔标准yougou图片名称
	 * 
	 * @param fileName  99900055_01_b.jpg
	 * @return new String[] {款色编码|商品编码, 2位流水号, 图片类型, 后缀名}
	 */
	public static String[] splitImageName(String fileName) {
		if (StringUtils.isBlank(fileName)) return null;
		
		String[] array = new String[4];
		//去除空格
		fileName = StringUtils.deleteWhitespace(fileName);
		String prefix = StringUtils.substringBeforeLast(fileName, ".");
		String suffix = StringUtils.substringAfterLast(fileName, ".").toLowerCase();
		if (StringUtils.isNotBlank(prefix)) {
			String[] _temp = prefix.split("_");
			if (_temp.length == 3) {
				array[0] = _temp[0];
				array[1] = _temp[1];
				array[2] = _temp[2];
			}
		}
		//兼容jpeg
		if ("jpeg".equals(suffix)) {
			suffix = "jpg";
		}
		array[3] = suffix;
		return array;
	}
}
