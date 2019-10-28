package org.astw.exp.bpi13.ex2;

import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.Encoder.EncodingType;
import org.astw.util.encoder.OneHotEncodingV2Info;

public class E21 extends E2{

	public E21(String configFile) throws Exception{

		initializeE2(this.getClass().getSimpleName(), configFile);
		
		//Specify the Encoding Type
		this.setEncodingType(new EncodingType[]{
//				EncodingType.OneHotV2, 
				EncodingType.AttEnc,
//				EncodingType.TimeSinceMidnight, 
//				EncodingType.TimeSinceFDoW, 
//				EncodingType.TimeDiffBefore, 
				});
		
		//Set One Hot Encoding V2 Info
		this.setOneHotEncodingInfo(new OneHotEncodingV2Info[]{
//				new OneHotEncodingV2Info("org:resource", xlogOri), 
//				new OneHotEncodingV2Info("org:group", xlogOri), 
//			new OneHotEncodingV2Info("concept:name", xlogOri), 
//				new OneHotEncodingV2Info("lifecycle:transition", xlogOri), 
//				new OneHotEncodingV2Info("organization involved", xlogOri), 
//				new OneHotEncodingV2Info("impact", xlogOri), 
//				new OneHotEncodingV2Info("product", xlogOri), 
//				new OneHotEncodingV2Info("resource country", xlogOri), 
//				new OneHotEncodingV2Info("organization country", xlogOri), 
//				new OneHotEncodingV2Info("org:role", xlogOri), 
		});
		//END OF Set One Hot Encoding V2 Info
		
		//Set Attribute Encoding Info
		this.setAttributeEncodingInfo(new AttributeEncodingInfo[]{
				new AttributeEncodingInfo("org:resource", xlogOri), 
				new AttributeEncodingInfo("org:group", xlogOri), 
				new AttributeEncodingInfo("concept:name", xlogOri), 
				new AttributeEncodingInfo("lifecycle:transition", xlogOri), 
				new AttributeEncodingInfo("organization involved", xlogOri), 
				new AttributeEncodingInfo("impact", xlogOri), 
				new AttributeEncodingInfo("product", xlogOri), 
				new AttributeEncodingInfo("resource country", xlogOri), 
				new AttributeEncodingInfo("organization country", xlogOri), 
				new AttributeEncodingInfo("org:role", xlogOri), 
		});
		//END OF Set Attribute Encoding Info

	}
	
	public static void main(String ar[]){
		
		try {
			
			new E21(ar[0]).exec();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
}
