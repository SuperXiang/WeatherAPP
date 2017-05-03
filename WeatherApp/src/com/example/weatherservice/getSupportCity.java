package com.example.weatherservice;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @author Xiang Yingfei
 * @date 2014.03.28
 *
 */
public class getSupportCity {

	private static final String wsdl = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx?wsdl";
	private static final String namespace = "http://WebXml.com.cn/";
	private static final String methodName = "getSupportCity";
	
	public String[] getCity(String pro){
		String [] pros = null;
		SoapObject soapObject = new SoapObject(namespace, methodName);
		soapObject.addProperty("byProvinceName", pro);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.encodingStyle = "UTF-8";
		envelope.setOutputSoapObject(soapObject);
		
		HttpTransportSE htse = new HttpTransportSE(wsdl);
		
		try {
			htse.call(namespace+methodName, envelope);
			SoapObject result = (SoapObject)envelope.getResponse();
			int count = result.getPropertyCount();
			
			pros = new String[count];
			for(int i=0;i<count;i++){
				pros[i] = result.getProperty(i).toString();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return pros;
	}
	
}

