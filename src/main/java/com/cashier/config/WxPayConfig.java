package com.cashier.config;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.cashier.wxpay.IWXPayDomain;
import com.cashier.wxpay.WXPayConfig;
import com.cashier.wxpay.WXPayConstants;
import com.cashier.wxpay.IWXPayDomain.DomainInfo;

/**
 * <p>WxPayConfig: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月11日
 * @version 1.0  
 */
public class WxPayConfig extends WXPayConfig{

	    private byte[] certData;

	    public WxPayConfig() throws Exception {
//	        String certPath = "D:\\site\\java\\apiclient_cert.p12";
	    	String certPath = "E:\\dev-wei-19813\\eclipse\\workspace\\shangtao\\shop-cashier\\src\\main\\resources\\weixin_cert\\apiclient_cert.p12";
	    	
	        File file = new File(certPath);
	        InputStream certStream = new FileInputStream(file);
	        this.certData = new byte[(int) file.length()];
	        certStream.read(this.certData);
	        certStream.close();
	    }

	    public String getAppID() {
	        return "wxa23d9815d991b55b";
	    }

	    public String getMchID() {
	        return "1543277861";
	    }

	    public String getKey() {
	        return "9e11e87475ebfcb0cfcb2de4a6f1b9bb";
	    }

	    public InputStream getCertStream() {
	        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
	        return certBis;
	    }

	    public int getHttpConnectTimeoutMs() {
	        return 8000;
	    }

	    public int getHttpReadTimeoutMs() {
	        return 10000;
	    }

		@Override
		public IWXPayDomain getWXPayDomain() {
			
			return new IWXPayDomain() {
				
				@Override
				public void report(String domain, long elapsedTimeMillis, Exception ex) {
					// TODO Auto-generated method stub
				}
				@Override
				public DomainInfo getDomain(WXPayConfig config) {
					return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
				}
			};
		}
}
