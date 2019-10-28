package org.astw.exp.bpi15.ex1;

import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.Encoder.EncodingType;
import org.astw.util.encoder.OneHotEncodingV2Info;

public class E11 extends E1{

	public E11(String configFile) throws Exception{

		initializeE1(this.getClass().getSimpleName(), configFile);
		
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
//				new OneHotEncodingV2Info("monitoringResource", xlogOri), 
//				new OneHotEncodingV2Info("org:resource", xlogOri), 
//				new OneHotEncodingV2Info("activityNameNL", xlogOri), 
//				new OneHotEncodingV2Info("activityNameEN", xlogOri), 
//				new OneHotEncodingV2Info("question", xlogOri), 
//				new OneHotEncodingV2Info("concept:name", xlogOri), 
		});
		//END OF Set One Hot Encoding V2 Info
		
		//Set Attribute Encoding Info
		this.setAttributeEncodingInfo(new AttributeEncodingInfo[]{
				new AttributeEncodingInfo("monitoringResource", xlogOri), 
				new AttributeEncodingInfo("org:resource", xlogOri), 
				new AttributeEncodingInfo("activityNameNL", xlogOri), 
				new AttributeEncodingInfo("activityNameEN", xlogOri), 
				new AttributeEncodingInfo("question", xlogOri), 
				new AttributeEncodingInfo("concept:name", xlogOri), 
		});
		//END OF Set Attribute Encoding Info

	}
	
	public static void main(String ar[]){
		
		try {
			
			new E11(ar[0]).exec();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	


}
