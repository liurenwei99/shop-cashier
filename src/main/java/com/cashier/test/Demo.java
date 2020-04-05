package com.cashier.test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import com.tencentcloudapi.cvm.v20170312.models.Image;

import utils.HttpUtils;
import utils.ImageUtils;


/**
 * <p>Demo: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2020年4月2日
 * @version 1.0  
 */
public class Demo {

	public static void main(String[] args) throws Exception {
		String basePath = "E:\\windowEditUploadPath\\";
		BufferedImage logo = ImageIO.read(new File(basePath + "logo.jpg"));
		 logo = ImageUtils.convertCircleImageBorder(logo.getWidth(), logo);
		  ImageIO.write(logo, "jpg", new FileOutputStream(basePath+"cricle_logo.jpg"));
		String storeNumber = "123";
	    String id = "wx5c5d3e4258be984d";
        String ass = "ef818f93fb6dfe52b9b7a992d4af552d";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scene", "storeNumber="+storeNumber);
        jsonObject.put("width", "280px");
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + utils.WxAppUtils.getAccessToken(id, ass);
        InputStream is = HttpUtils.doPostJsonBackStream(url, jsonObject.toString());
        BufferedImage code = ImageIO.read(is);
        Graphics2D g2 = code.createGraphics();
        
        g2.drawImage(logo,(code.getWidth()-logo.getWidth())/2,(code.getHeight()-logo.getHeight())/2,logo.getWidth(),logo.getHeight(),null);
        g2.dispose();
        ImageIO.write(code, "jpg", new FileOutputStream(basePath+storeNumber+"_logo.jpg"));
        
//        FileOutputStream fos = new FileOutputStream(new File(basePath + storeNumber+".jpg"));
//        byte[] buff = new byte[1024];
//        int len = 0;
//        while ((len = is.read(buff)) != -1) {
//            fos.write(buff, 0, len);
//        }
//        fos.close();
//        is.close();
	}
}
