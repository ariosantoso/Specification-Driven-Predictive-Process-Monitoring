/*******************************************************************************
 *     Specification-Driven-Predictive-Process-Monitoring
 *     Copyright (C) 2018 Ario Santoso (santoso.ario@gmail.com)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package org.astw.util.encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.astw.util.Util;
import org.astw.util.XESUtil;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;


/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */
public class Encoder {
	
	//Encoding type
	public enum EncodingType {
		OneHot, 			// one hot encoding
		OneHotV2, 			// one hot encoding V2
		AttEnc, 			// one hot encoding V2
		TimeSinceMidnight, 	// time since midnight FOR THE LAST EVENT IN THE PREFIX
		TimeSinceFDoW, 		// time since first day of the week FOR THE LAST EVENT IN THE PREFIX - Note: the first day of the week is taken from the locale infromation of the corresponding machine
		TimeDiffBefore 	// time since the previous event FOR THE LAST EVENT IN THE PREFIX
	}
	
	//pre-defined XES attribute name reference for one hot encoding
	private static String XES_ONE_HOT_ATTR = "concept:name";
	
	//pre-defined XES attribute name reference for time related encoding
	public static String XES_TS_ATTR = "time:timestamp";
	
	//attribute names for WEKA instance
	public static String ATTR_ONE_HOT = "t_oh";
	public static String ATTR_TIME_SINCE_MIDNIGHT = "t_sm"; 
	public static String ATTR_TIME_SINCE_FDOW = "t_fdow"; 
	public static String ATTR_TIME_DIFF_BEFORE = "t_before"; 
	public static String ATTR_TIME_DIFF_AFTER = "t_after";
	public static String ATTR_TARGET = "t_target";
	//END OF attribute names for WEKA instance
	
	
	/**
	 * Create the instances structure (i.e., Set of instance). E.g., add all possible WEKA Feature Attribute Names
	 * 
	 * 
	 * @param totalTraces - total number of all all traces in the log under consideration
	 * @param name - instances name - just a name for this 'instances'
	 * @param encodes - list of the chosen encoding type
	 * 
	 * Note: 3 features di bawah ini buat kebutuhan one hot encoding
	 * 
	 * @param maxLength - the highest trace length in the log under consideration
	 * @param oneHotPref - prefix name for the WEKA attribute key for one hot encoding
	 * @param totalUniqueEvents - it will be used for determining the number of the features of an event. 
	 * 
	 * Note: totalUniqueEvents - akan dipake buat nentuin size features buat one hot encoding. pada kenyataannya, ini macam jumlah kemungkinan value untuk attribute concept:name
	 * 
	 * 
	 * @return
	 */
	public static Instances createInstancesTemplate(
			int totalTraces, String name, EncodingType[] encodes,
			Set<String> allPossibleTargetClassValues,
			int maxLength, int totalUniqueEvents) {
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();
		
		//if (oneHotPref == null)
		String oneHotPref= ATTR_ONE_HOT;
		
		for (EncodingType et : encodes) {
			if (et == EncodingType.OneHot) {
				attrList.addAll(getOneHotAttributeList(oneHotPref, maxLength, totalUniqueEvents));
				
			} else if (et == EncodingType.TimeSinceMidnight) {
				attrList.add(new Attribute(ATTR_TIME_SINCE_MIDNIGHT));
				
			} else if (et == EncodingType.TimeSinceFDoW) {
				attrList.add(new Attribute(ATTR_TIME_SINCE_FDOW));
				
			} else if (et == EncodingType.TimeDiffBefore) {
				attrList.add(new Attribute(ATTR_TIME_DIFF_BEFORE));
				
//			} else if (et == EncodingType.TargetClass) {
//				ArrayList<String> l = new ArrayList<String>();
//				l.add(PingPong.nopingpong);
//				l.add(PingPong.pingpong);
//				attrList.add(new Attribute(ATTR_TARGET, l));
			}
		}
		
		//Add the target class  OF TYPE STRING!!
		ArrayList<String> l = new ArrayList<String>();
		for(String targetVal: allPossibleTargetClassValues)
			l.add(targetVal);
		attrList.add(new Attribute(ATTR_TARGET, l));

		
		return new Instances(name, attrList, totalTraces);
	}

	
	/**
	 * Create the instances structure (i.e., Set of instance). E.g., add all possible WEKA Feature Attribute Names
	 * 
	 * 
	 * @param totalTraces - total number of all all traces in the log under consideration
	 * @param instancesName - instances name - just a name for this 'instances'
	 * @param encodes - list of the chosen encoding type
	 * 
	 * Note: 3 features di bawah ini buat kebutuhan one hot encoding
	 * 
	 * @param maxLength - the highest trace length in the log under consideration
	 * @param oneHotPref - prefix name for the WEKA attribute key for one hot encoding
	 * @param totalUniqueEvents - it will be used for determining the number of the features of an event. 
	 * 
	 * Note: totalUniqueEvents - akan dipake buat nentuin size features buat one hot encoding. pada kenyataannya, ini macam jumlah kemungkinan value untuk attribute concept:name
	 * 
	 * 
	 * @return
	 */
	public static Instances createInstancesTemplateV2(
			int totalTraces, String instancesName, EncodingType[] encodes,
			Set<String> allPossibleTargetClassValues, OneHotEncodingV2Info[] oneHotInfo, AttributeEncodingInfo[] attEncodingInfo) {
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();
		
		//if (oneHotPref == null)
//		String oneHotPref= ATTR_ONE_HOT;
		
		for (EncodingType et : encodes) {
			if (et == EncodingType.OneHot) {
				//attrList.addAll(getOneHotAttributeList(oneHotPref, maxLength, totalUniqueEvents));
				//Unsupported for this one
				
			} else if (et == EncodingType.OneHotV2) {

				for(OneHotEncodingV2Info ohi: oneHotInfo){
					
					ArrayList<Attribute> attListForOHE = getOneHotV2AttributeList(ohi);
					attrList.addAll(attListForOHE);
					System.out.println("- OH_AttName: "+ohi.getXesAttName()+"- OH_AttNameDifNumVal: "+ohi.getNumOfDifferentValues()+"- OH_AttNum: "+attListForOHE.size());
				}
				
			} else if (et == EncodingType.AttEnc) {

				for(AttributeEncodingInfo aei: attEncodingInfo){
					ArrayList<Attribute> attListForAEI = getAttEncAttributeList(aei);
					attrList.addAll(attListForAEI);
					
					System.out.println("- AE_AttName: "+aei.getXesAttName()+"- AE_AttNameDifNumVal: "+aei.getNumOfDifferentValues()+"- AE_AttNum: "+attListForAEI.size());
				}
				
				
			} else if (et == EncodingType.TimeSinceMidnight) {
				attrList.add(new Attribute(ATTR_TIME_SINCE_MIDNIGHT));
				System.out.println("- TimeSinceMidnight ");

				
			} else if (et == EncodingType.TimeSinceFDoW) {
				attrList.add(new Attribute(ATTR_TIME_SINCE_FDOW));
				System.out.println("- TimeSinceFDoW ");
				
			} else if (et == EncodingType.TimeDiffBefore) {
				attrList.add(new Attribute(ATTR_TIME_DIFF_BEFORE));
				System.out.println("- TimeDiffBefore ");
				
//			} else if (et == EncodingType.TargetClass) {
//				ArrayList<String> l = new ArrayList<String>();
//				l.add(PingPong.nopingpong);
//				l.add(PingPong.pingpong);
//				attrList.add(new Attribute(ATTR_TARGET, l));
			}
		}
		
		//==================================================================================
		
		//Add the target class  OF TYPE STRING!!
		ArrayList<String> l = new ArrayList<String>();
		for(String targetVal: allPossibleTargetClassValues){
			//System.out.println("Encoder - prepare target class - Val: "+targetVal);			
			l.add(targetVal);
		}
		attrList.add(new Attribute(ATTR_TARGET, l));

		//==================================================================================
		
		//Add the target class  OF TYPE STRING!!
//		ArrayList<String> l = new ArrayList<String>();
//		ArrayList<String> tarVal = new ArrayList<String>();
//		
//		for(String targetVal: allPossibleTargetClassValues){
//
//			//System.out.println("Encoder - prepare target class - Val: "+targetVal);
//			tarVal.add(targetVal);
//		}
//		
//		
////		System.out.println("Encoder - add target class - tarVal.get(0): "+tarVal.get(0));
////		l.add(tarVal.get(0));
////		System.out.println("Encoder - add target class - tarVal.get(1): "+tarVal.get(1));
////		l.add(tarVal.get(1));
//
//		System.out.println("Encoder - add target class - tarVal.get(1): "+tarVal.get(1));
//		l.add(tarVal.get(1));
//		System.out.println("Encoder - add target class - tarVal.get(0): "+tarVal.get(0));
//		l.add(tarVal.get(0));
//
//		attrList.add(new Attribute(ATTR_TARGET, l));

		//==================================================================================
		
		
		
		return new Instances(instancesName, attrList, totalTraces);
	}

	
	public static Instances createInstancesTemplateNumericTarget(
			int totalTraces, String instancesName, EncodingType[] encodes,
			OneHotEncodingV2Info[] oneHotInfo, AttributeEncodingInfo[] attEncodingInfo) {
		
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();
				
		for (EncodingType et : encodes) {

			if (et == EncodingType.OneHotV2) {

				for(OneHotEncodingV2Info ohi: oneHotInfo){
					
					ArrayList<Attribute> attListForOHE = getOneHotV2AttributeList(ohi);
					attrList.addAll(attListForOHE);
					System.out.println("- OH_AttName: "+ohi.getXesAttName()+"- OH_AttNameDifNumVal: "+ohi.getNumOfDifferentValues()+"- OH_AttNum: "+attListForOHE.size());
				}
				
			} else if (et == EncodingType.AttEnc) {

				for(AttributeEncodingInfo aei: attEncodingInfo){
					ArrayList<Attribute> attListForAEI = getAttEncAttributeList(aei);
					attrList.addAll(attListForAEI);
					
					System.out.println("- AE_AttName: "+aei.getXesAttName()+"- AE_AttNameDifNumVal: "+aei.getNumOfDifferentValues()+"- AE_AttNum: "+attListForAEI.size());
				}
								
			} else if (et == EncodingType.TimeSinceMidnight) {
				attrList.add(new Attribute(ATTR_TIME_SINCE_MIDNIGHT));
				
			} else if (et == EncodingType.TimeSinceFDoW) {
				attrList.add(new Attribute(ATTR_TIME_SINCE_FDOW));
				
			} else if (et == EncodingType.TimeDiffBefore) {
				attrList.add(new Attribute(ATTR_TIME_DIFF_BEFORE));
				
			}
		}
		
		attrList.add(new Attribute(ATTR_TARGET));
		
		return new Instances(instancesName, attrList, totalTraces);
	}

	
	/**
	 * 
	 * Create WEKA 'instance' object for the given Trace. 
	 * Then we will add this WEKA 'instance' into the WEKA 'instances' that is given as the parameter of this method
	 * 
	 * @param instances - WEKA 'instances' to be filled
	 * @param trace - The trace to be encoded
	 * @param maxLength - The highest Trace length
	 * @param consideredPrefixLength - the length of the prefix to be considered
	 * @param encodes - the chosen type of encoding 
	 * @param targetClassValue - nilai target variable
	 * 
	 * Note: dua di bawah ini buat one hot encoding aja
	 * @param oneHotPref - desired prefix for one hot encoding
	 * @param listOfAttributePossibleValues - semua kemungkinan value untuk attribute concept:name - ini sebetulnya adalah SET, cuma disimpen dalam arraylist biar gampang
	 * 
	 * @return
	 */
	public static Instances encodeAnXTrace(
			Instances instances, XTrace trace, 
			int consideredPrefixLength, EncodingType encodes[], 
			int maxLength, String oneHotAttName, ArrayList<String> listOfAttributePossibleValues,
			String targetClassValue) {
		
//		if (oneHotPref == null || oneHotPref.isEmpty())
		String oneHotPref = ATTR_ONE_HOT;
		
		if (oneHotAttName == null || oneHotAttName.isEmpty())
			oneHotAttName = XES_ONE_HOT_ATTR;
		
		Instance i = new DenseInstance(instances.numAttributes());
		
		for (EncodingType et : encodes) {
			if (et == EncodingType.OneHot) {
				getOneHotEncodingInstance(trace, 
						maxLength, listOfAttributePossibleValues, oneHotPref, oneHotAttName, 
						consideredPrefixLength, instances, i);
				
			} else if (et == EncodingType.TimeSinceMidnight) {
				getTimeSinceEncodingInstance(trace, 
						XES_TS_ATTR, EncodingType.TimeSinceMidnight, 
						consideredPrefixLength, instances, i);
				
			} else if (et == EncodingType.TimeSinceFDoW) {
				getTimeSinceEncodingInstance(trace, 
						XES_TS_ATTR, EncodingType.TimeSinceFDoW, 
						consideredPrefixLength, instances, i);
				
			} else if (et == EncodingType.TimeDiffBefore) {
				getTimeDiffEncodingInstance(trace,
						XES_TS_ATTR, EncodingType.TimeDiffBefore,
						consideredPrefixLength, instances, i);
				
//			} else if (et == EncodingType.TargetClass) {
//				Attribute a = instances.attribute(ATTR_TARGET);
//				i.setValue(a, targetClassValue);
			}
			
		}

		//Buat masukin class attribute value
		Attribute a = instances.attribute(ATTR_TARGET);
		i.setValue(a, targetClassValue);

		instances.add(i);
		return instances;
	}


	/**
	 * 
	 * Create WEKA 'instance' object for the given Trace. 
	 * Then we will add this WEKA 'instance' into the WEKA 'instances' that is given as the parameter of this method
	 * 
	 * @param instances - WEKA 'instances' to be filled
	 * @param trace - The trace to be encoded
	 * @param maxLength - The highest Trace length
	 * @param consideredPrefixLength - the length of the prefix to be considered
	 * @param encodes - the chosen type of encoding 
	 * @param targetClassValue - nilai target variable
	 * 
	 * Note: dua di bawah ini buat one hot encoding aja
	 * @param oneHotPref - desired prefix for one hot encoding
	 * @param listOfAttributePossibleValues - semua kemungkinan value untuk attribute concept:name - ini sebetulnya adalah SET, cuma disimpen dalam arraylist biar gampang
	 * 
	 * @return
	 */
	public static Instances encodeAnXTraceV2(
			Instances instances, XTrace trace, 
			int consideredPrefixLength, EncodingType encodes[], 
			//int maxLength, String oneHotAttName, ArrayList<String> listOfAttributePossibleValues,
			OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo,
			String targetClassValue) {
		
//		if (oneHotPref == null || oneHotPref.isEmpty())
//		String oneHotPref = ATTR_ONE_HOT;
//		
//		if (oneHotAttName == null || oneHotAttName.isEmpty())
//			oneHotAttName = XES_ONE_HOT_ATTR;
		
		Instance i = new DenseInstance(instances.numAttributes());
		
		for (EncodingType et : encodes) {
			if (et == EncodingType.OneHotV2) {
				
				for(OneHotEncodingV2Info ohi: oneHotEncodingV2Info)
					getOneHotV2EncodingInstance(ohi, trace, consideredPrefixLength, instances, i);
				
			}else if (et == EncodingType.AttEnc) {
					
				for(AttributeEncodingInfo aei: attEncodingInfo)
					getAttEncInstance(aei, trace, consideredPrefixLength, instances, i);
					
			} else if (et == EncodingType.TimeSinceMidnight) {
				getTimeSinceEncodingInstance(trace, 
						XES_TS_ATTR, EncodingType.TimeSinceMidnight, 
						consideredPrefixLength, instances, i);
				
			} else if (et == EncodingType.TimeSinceFDoW) {
				getTimeSinceEncodingInstance(trace, 
						XES_TS_ATTR, EncodingType.TimeSinceFDoW, 
						consideredPrefixLength, instances, i);
				
			} else if (et == EncodingType.TimeDiffBefore) {
				getTimeDiffEncodingInstance(trace,
						XES_TS_ATTR, EncodingType.TimeDiffBefore,
						consideredPrefixLength, instances, i);
				
				
//			} else if (et == EncodingType.TargetClass) {
//				Attribute a = instances.attribute(ATTR_TARGET);
//				i.setValue(a, targetClassValue);
			}
			
		}

		//adding class attribute value
		Attribute a = instances.attribute(ATTR_TARGET);
		i.setValue(a, targetClassValue);

		instances.add(i);
		return instances;
	}

	public static Instances encodeAnXTraceNumericTarget(
			Instances instances, XTrace trace, 
			int consideredPrefixLength, EncodingType encodes[], 
			OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo,
			double targetClassValue) {
		
		Instance i = new DenseInstance(instances.numAttributes());
		
		for (EncodingType et : encodes) {
			if (et == EncodingType.OneHotV2) {
				
				for(OneHotEncodingV2Info ohi: oneHotEncodingV2Info)
					getOneHotV2EncodingInstance(ohi, trace, consideredPrefixLength, instances, i);
				
			}else if (et == EncodingType.AttEnc) {
					
				for(AttributeEncodingInfo aei: attEncodingInfo)
					getAttEncInstance(aei, trace, consideredPrefixLength, instances, i);
					
			} else if (et == EncodingType.TimeSinceMidnight) {
				getTimeSinceEncodingInstance(trace, 
						XES_TS_ATTR, EncodingType.TimeSinceMidnight, 
						consideredPrefixLength, instances, i);
				
			} else if (et == EncodingType.TimeSinceFDoW) {
				getTimeSinceEncodingInstance(trace, 
						XES_TS_ATTR, EncodingType.TimeSinceFDoW, 
						consideredPrefixLength, instances, i);
				
			} else if (et == EncodingType.TimeDiffBefore) {
				getTimeDiffEncodingInstance(trace,
						XES_TS_ATTR, EncodingType.TimeDiffBefore,
						consideredPrefixLength, instances, i);
				
			}
			
		}

		//adding class attribute value
		Attribute a = instances.attribute(ATTR_TARGET);
		i.setValue(a, targetClassValue);

		instances.add(i);
		return instances;
	}

	
	///////////////////////////////////////////////////////
	// Attribute Encoding
	///////////////////////////////////////////////////////	
	
	//buat dapetin nama2 attribute buat WEKA instance
	public static ArrayList<Attribute> getAttEncAttributeList(AttributeEncodingInfo attEncodingInfo) {
		
		String attPrefName = attEncodingInfo.getWekaAttName(); 
		int maxTraceLength = attEncodingInfo.getMaxTraceLength(); 
		
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();
		for (int i = 1; i <= maxTraceLength; i++) {
			String att = String.format(AttributeEncodingInfo.ATT_ENC_WEKA_ATTR_TEMPLATE, attPrefName, i);
			Attribute a = new Attribute(att);
			attrList.add(a);
		}
		return attrList;
	}

	public static Instance getAttEncInstance(
			AttributeEncodingInfo attEncodingInfo, XTrace xtrace, int consideredPrefixLength, Instances instances, Instance inst) {
		
		int maxTraceLength = attEncodingInfo.getMaxTraceLength(); 
		ArrayList<String> listOfAllPossibleAttValues = attEncodingInfo.getListOfAllPossibleAttValues();
		String attPrefName = attEncodingInfo.getWekaAttName();
		String xesAttrName = attEncodingInfo.getXesAttName();

		//TODO: null input check?
		
		HashMap<String, Integer> attValuesIndexMap = new HashMap<String, Integer>();
				
		int order = 1;
		for (String s : listOfAllPossibleAttValues) {
			attValuesIndexMap.put(s, order);
			order++;
		}
				
		int numOfEventsInTheCurrentPrefix = xtrace.size();
		if (consideredPrefixLength > 0)
			numOfEventsInTheCurrentPrefix = Math.min(consideredPrefixLength, xtrace.size());
		
		for (int i = 0; i < maxTraceLength; i++) {
			if (i < numOfEventsInTheCurrentPrefix) { 
				XEvent xEvent = xtrace.get(i);
				int value = 0;
				
				if(xEvent.getAttributes().containsKey(xesAttrName)){
					String ev = xEvent.getAttributes().get(xesAttrName).toString();
					
//					if(ev == null || attValuesIndexMap == null)
//						System.out.println("huhu");
////					System.out.println("huhu: "+ev);
////					System.out.println("huhu: "+attValuesIndexMap);
////					System.out.println("haha: "+attValuesIndexMap.size());
//					if(attValuesIndexMap != null && ev != null)
//						
					if(attValuesIndexMap.containsKey(ev))
						value = attValuesIndexMap.get(ev);
				}

				String attrName = String.format(AttributeEncodingInfo.ATT_ENC_WEKA_ATTR_TEMPLATE, attPrefName, (i+1));
				Attribute a = instances.attribute(attrName); 
				inst.setValue(a, value);

			} else {
				//fill the rest of the instance with 0
				double value = 0.0;
				String attrName = String.format(AttributeEncodingInfo.ATT_ENC_WEKA_ATTR_TEMPLATE, attPrefName, (i+1));
				Attribute a = instances.attribute(attrName);
				inst.setValue(a, value);
			}
		}
//		System.out.println("inst is " + inst);
		return inst;
	}

	///////////////////////////////////////////////////////
	// END OF Attribute Encoding
	///////////////////////////////////////////////////////	

	
	
	///////////////////////////////////////////////////////
	// one hot encoding V2
	///////////////////////////////////////////////////////	
	
	//to get the attribute names for the WEKA instance
	public static ArrayList<Attribute> getOneHotV2AttributeList(OneHotEncodingV2Info oneHotInfo) {
		
		String oneHotAttPrefName = oneHotInfo.getWekaAttName(); 
		int maxTraceLength = oneHotInfo.getMaxTraceLength(); 
		int numOfDifferentValues = oneHotInfo.getNumOfDifferentValues();
		
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();
		for (int i = 1; i <= maxTraceLength; i++) {
			for (int j = 1; j <= numOfDifferentValues; j++) {
				
//				String att = oneHotAttPrefName + (i) + "_" + (j);
				String att = String.format(OneHotEncodingV2Info.XES_ONE_HOT_WEKA_ATTR_V2_TEMPLATE, oneHotAttPrefName, i, j);
				Attribute a = new Attribute(att);
				attrList.add(a);
			}
		}
		return attrList;
	}

	/**
	 * 1 trace 1 instance for one hot encoding
	 * @param xtrace
	 * @param maxLength
	 * @param listOfAllPossibleAttValues
	 * @return
	 */
	public static Instance getOneHotV2EncodingInstance(
			OneHotEncodingV2Info oneHotInfo, XTrace xtrace, int consideredPrefixLength, Instances instances, Instance inst) {
		
		int maxTraceLength = oneHotInfo.getMaxTraceLength(); 
		ArrayList<String> listOfAllPossibleAttValues = oneHotInfo.getListOfAllPossibleAttValues();
		String oneHotAttPrefName = oneHotInfo.getWekaAttName();
		String xesAttrName = oneHotInfo.getXesAttName();
		int numOfDifferentValues = oneHotInfo.getNumOfDifferentValues();

		//TODO: null input check?
		
		HashMap<String, Integer> attValuesIndexMap = new HashMap<String, Integer>();
		int order = 0;
		for (String s : listOfAllPossibleAttValues) {
			attValuesIndexMap.put(s, order);
			order++;
		}
				
		int numOfEventsInTheCurrentPrefix = xtrace.size();
		if (consideredPrefixLength > 0)
			numOfEventsInTheCurrentPrefix = Math.min(consideredPrefixLength, xtrace.size());
		
		for (int i = 0; i < maxTraceLength; i++) {
			if (i < numOfEventsInTheCurrentPrefix) { 
				XEvent xEvent = xtrace.get(i);
				
				int pos = -1;
				if(xEvent.getAttributes().containsKey(xesAttrName)){
					String ev = xEvent.getAttributes().get(xesAttrName).toString();

					if(attValuesIndexMap.containsKey(ev))
						pos = attValuesIndexMap.get(ev);
				}
				
				for (int j = 0; j < numOfDifferentValues; j++) {
					double value = 0.0;
//						String attrName = oneHotAttPrefName + (i+1) +"_"+ (j+1);
					String attrName = String.format(OneHotEncodingV2Info.XES_ONE_HOT_WEKA_ATTR_V2_TEMPLATE, oneHotAttPrefName, (i+1), (j+1));
					Attribute a = instances.attribute(attrName); 
					if (pos == j) {
						value = 1.0;
					}
					inst.setValue(a, value);
				}
				
			} else {
//				fill the rest of the instance with 0
				double value = 0.0;
				for (int j = 0; j < numOfDifferentValues; j++) {
//					String attrName = oneHotAttPrefName + (i+1) +"_"+ (j+1);
					String attrName = String.format(OneHotEncodingV2Info.XES_ONE_HOT_WEKA_ATTR_V2_TEMPLATE, oneHotAttPrefName, (i+1), (j+1));
					Attribute a = instances.attribute(attrName);
					inst.setValue(a, value);
				}
			}
		}
//		System.out.println("inst is " + inst);
		return inst;
	}

	///////////////////////////////////////////////////////
	// END OF one hot encoding V2
	///////////////////////////////////////////////////////	

	
	///////////////////////////////////////////////////////
	// one hot encoding
	///////////////////////////////////////////////////////	
	
	//buat dapetin nama2 attribute buat WEKA instance
	public static ArrayList<Attribute> getOneHotAttributeList(
			String prefName, int maxLength, int totalUniqueEvents) {
		
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();
		for (int i = 1; i <= maxLength; i++) {
			for (int j = 1; j <= totalUniqueEvents; j++) {
				String att = prefName + (i) + "_" + (j);
				Attribute a = new Attribute(att);
				attrList.add(a);
			}
		}
		return attrList;
	}

	/**
	 * 1 trace 1 instance for one hot encoding
	 * @param xtrace
	 * @param maxLength
	 * @param listOfAllPossibleAttValues
	 * @return
	 */
	public static Instance getOneHotEncodingInstance(XTrace xtrace, int maxLength, 
			ArrayList<String> listOfAllPossibleAttValues, String prefName, String xesAttrName,
			int consideredPrefixLength, Instances instances, Instance inst) {
		
		if (prefName == null) {
			prefName = ATTR_ONE_HOT;
		}
		HashMap<String, Integer> eventOrderMap = new HashMap<String, Integer>();
		int order = 0;
		for (String s : listOfAllPossibleAttValues) {
			eventOrderMap.put(s, order);
			order++;
		}
		int totalUniqueEvents = listOfAllPossibleAttValues.size();
		
//		int totalInstance = maxLength * totalUniqueEvents;
//		Instance inst = new DenseInstance(totalInstance);
		
		int limitConsideration = xtrace.size();
		if (consideredPrefixLength > 0)
			limitConsideration = Math.min(consideredPrefixLength, xtrace.size());
		
		for (int i = 0; i < maxLength; i++) {
			if (i < limitConsideration) { 
				XEvent xEvent = xtrace.get(i);
				String ev = xEvent.getAttributes().get(xesAttrName).toString();
				int pos = eventOrderMap.get(ev);
				
				for (int j = 0; j < totalUniqueEvents; j++) {
					double value = 0.0;
					String attrName = prefName + (i+1) +"_"+ (j+1);
					Attribute a = instances.attribute(attrName); 
					if (pos == j) {
						value = 1.0;
					}
					inst.setValue(a, value);
					
				}
			} else {
//				fill the rest of the instance with 0
				double value = 0.0;
				for (int j = 0; j < totalUniqueEvents; j++) {
					String attrName = prefName + (i+1) +"_"+ (j+1);
					Attribute a = instances.attribute(attrName);
					inst.setValue(a, value);
				}
			}
		}
//		System.out.println("inst is " + inst);
		return inst;
	}

	///////////////////////////////////////////////////////
	// END OF one hot encoding
	///////////////////////////////////////////////////////	

	
	
	///////////////////////////////////////////////////////
	// timeSinceMidnight & timeSinceFDoW
	///////////////////////////////////////////////////////
	
	/**
	 * Get Instance for the encoding type: time difference since midnight
	 * and time difference since first day of the week
	 * @param xtrace
	 * @param xesAttributeName
	 * @param encodingType the parameter that selects the function to call
	 * @param consideredPrefixLength
	 * @param instances
	 * @return
	 */
	public static Instance getTimeSinceEncodingInstance(XTrace xtrace, 
			String xesAttributeName, EncodingType encodingType,
			int consideredPrefixLength, Instances instances, Instance inst) {
		int limitConsideration = xtrace.size();
		if (consideredPrefixLength > 0)
			limitConsideration = Math.min(consideredPrefixLength, xtrace.size());
				
		XEvent ev = xtrace.get(limitConsideration - 1);
		XAttribute attr = ev.getAttributes().get(xesAttributeName);
		String timeStr = attr.toString();
		long ts = 0l;
		String wekaAttrName = null;
		if (encodingType == EncodingType.TimeSinceMidnight) {
			ts = Util.timeDiffSinceMidnight(timeStr);
			wekaAttrName = ATTR_TIME_SINCE_MIDNIGHT;
		} else {
			ts = Util.timeDiffSinceFirstDayOfTheWeek(timeStr);
			wekaAttrName = ATTR_TIME_SINCE_FDOW;
		}
		
		Attribute a = instances.attribute(wekaAttrName);
		inst.setValue(a, ts);
		return inst;
	}

	///////////////////////////////////////////////////////
	// END OF timeSinceMidnight & timeSinceFDoW
	///////////////////////////////////////////////////////

	
	
	///////////////////////////////////////////////////////
	// timeDiff	
	///////////////////////////////////////////////////////	
	
	/**
	 * Get Instance for the encoding type: timeDiffBeforeEvent
	 * @param xtrace
	 * @param xesAttributeName 
	 * @param encodingType the deciding event
	 * @param consideredPrefixLength
	 * @param instances
	 * @return
	 */
	public static Instance getTimeDiffEncodingInstance(XTrace xtrace, 
			String xesAttributeName, EncodingType encodingType,
			int consideredPrefixLength, Instances instances, Instance inst) {
		int limitConsideration = xtrace.size();
		if (consideredPrefixLength > 0)
			limitConsideration = Math.min(consideredPrefixLength, xtrace.size());
		
		XEvent ev1 = xtrace.get(limitConsideration - 1);
		String ts1 = ev1.getAttributes().get(xesAttributeName).toString();
		String ts2 = null;
		String wekaAttrName = null;
		
		long ts = 0;
		
		if (encodingType == EncodingType.TimeDiffBefore) {
			wekaAttrName = ATTR_TIME_DIFF_BEFORE;

			if(consideredPrefixLength > 1){
				XEvent ev2 = xtrace.get(consideredPrefixLength - 2);
				ts2 = ev2.getAttributes().get(xesAttributeName).toString();
				ts = Util.timeDiff(ts1, ts2);
			}
			
		} 
		
		
		Attribute a = instances.attribute(wekaAttrName);
//		Instance inst = new DenseInstance(instances.numAttributes());
		inst.setValue(a, ts);
		return inst;
	}

	///////////////////////////////////////////////////////
	// END OF timeDiff	
	///////////////////////////////////////////////////////	

	
}
