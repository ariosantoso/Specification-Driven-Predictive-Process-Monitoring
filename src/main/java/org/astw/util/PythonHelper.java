package org.astw.util;

import weka.core.Instance;
import weka.core.Instances;

public class PythonHelper {

	public static long[] getFeaturesFromInstance(Instance instance) {
		int numFeatures = instance.numAttributes()-1;
		long[] features = new long[numFeatures];
		
		//int classIndex = instances.get(0).classIndex();
		//System.out.println("classIndex is: " + classIndex);
			
		for (int j = 0; j < instance.numAttributes(); j++) {
			if (instance.classIndex() != j) {
				if (j > instance.classIndex()) {
					features[j-1] = (long)instance.value(j);
				}
				else {
					features[j] = (long)instance.value(j);
				}
			}
		}

		return features;
	}

	
	public static long[] getFeaturesFromInstances(Instances instances) {
		int numFeatures = instances.numAttributes()-1;
		long[] features = new long[numFeatures * instances.numInstances()];
		//int classIndex = instances.get(0).classIndex();
		//System.out.println("classIndex is: " + classIndex);
		for (int i = 0; i < instances.size(); i++) {
			Instance inst = instances.get(i);
			
			long[] temp = new long[numFeatures];
			for (int j = 0; j < inst.numAttributes(); j++) {
				if (inst.classIndex() != j) {
					if (j > inst.classIndex()) {
						temp[j-1] = (long)inst.value(j);
					}
					else {
						temp[j] = (long)inst.value(j);
					}
				}
			}
			System.arraycopy(temp, 0, features, (i*numFeatures), temp.length);
		}
		return features;
	}
	
	public static double[] getNumericTargetFromInstances(Instances instances) {
		double[] target = new double[instances.size()];
		for (int i = 0; i < instances.size(); i++) {
			Instance inst = instances.get(i);
			target[i] = (double) inst.classValue();
		}
		return target;
	}
	
	public static String[] getNominalTargetFromInstances(Instances instances) {
		String[] target = new String[instances.size()];
		for (int i = 0; i < instances.size(); i++) {
			Instance inst = instances.get(i);
			target[i] = inst.stringValue(inst.classIndex());
		}
		return target;
	}
	
	public static boolean compareLongVals(long[] longArr, Instance inst) {
		boolean same = true;
		if (longArr.length != inst.numAttributes() - 1)
			same = false;
		if (same) {
			for (int i = 0; i < longArr.length; i++) {
				long fromInst = (long)inst.value(i);
				if (fromInst != longArr[i]) {
					same = false;
				}
			}
		}
		return same;
	}
}