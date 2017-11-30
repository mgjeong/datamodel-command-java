package org.command.json.format;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class EdgeJsonFormatter {
	private static Gson gson = new Gson();

	public static String encodeEdgeDataToJsonString(EdgeData edgeData) {
		return gson.toJson(edgeData);
	}

	public static EdgeData decodeJsonStringToEdgeData(String json) {
		return gson.fromJson(json, EdgeData.class);
	}

	public static Object getObjectValueByName(List<EdgeAttribute> edgeAttributeList, String edgeAttributeName) {
		for (EdgeAttribute edgeAttr : edgeAttributeList) {
			if (edgeAttr.getName().equals(edgeAttributeName) == true) {
				return edgeAttr.getValue();
			} else if (edgeAttr.getDataType().equals(EdgeFormatIdentifier.ATTRIBUTES_TYPE.getValue()) == true) {
				return getObjectValueByName(covertAttrubiteListFromObject(edgeAttr.getValue()), edgeAttributeName);
			}
		}
		return null;
	}

	public static String getStringValueByName(List<EdgeAttribute> edgeAttributeList, String edgeAttributeName) {
		for (EdgeAttribute edgeAttr : edgeAttributeList) {
			if (edgeAttr.getName().equals(edgeAttributeName) == true
					&& edgeAttr.getName().equalsIgnoreCase(EdgeFormatIdentifier.STRING_TYPE.getValue())) {
				return String.valueOf(edgeAttr.getValue());
			} else if (edgeAttr.getDataType().equals(EdgeFormatIdentifier.ATTRIBUTES_TYPE.getValue()) == true) {
				return getStringValueByName(covertAttrubiteListFromObject(edgeAttr.getValue()), edgeAttributeName);
			}
		}
		return null;
	}

	public static Double getDoubleValueByName(List<EdgeAttribute> edgeAttributeList, String edgeAttributeName) {
		for (EdgeAttribute edgeAttr : edgeAttributeList) {
			if (edgeAttr.getName().equals(edgeAttributeName) == true
					&& edgeAttr.getName().equalsIgnoreCase(EdgeFormatIdentifier.DOUBLE_TYPE.getValue())) {
				return (Double) edgeAttr.getValue();
			} else if (edgeAttr.getDataType().equals(EdgeFormatIdentifier.ATTRIBUTES_TYPE.getValue()) == true) {
				return getDoubleValueByName(covertAttrubiteListFromObject(edgeAttr.getValue()), edgeAttributeName);
			}
		}
		return 0.0;
	}

	public static Integer getIntegerValueByName(List<EdgeAttribute> edgeAttributeList, String edgeAttributeName) {
		for (EdgeAttribute edgeAttr : edgeAttributeList) {
			if (edgeAttr.getName().equals(edgeAttributeName) == true
					&& edgeAttr.getName().equalsIgnoreCase(EdgeFormatIdentifier.INTEGER_TYPE.getValue())) {
				return (Integer) edgeAttr.getValue();
			} else if (edgeAttr.getDataType().equals(EdgeFormatIdentifier.ATTRIBUTES_TYPE.getValue()) == true) {
				
				return getIntegerValueByName(covertAttrubiteListFromObject(edgeAttr.getValue()), edgeAttributeName);
			}
		}
		return 0;
	}

	public static Float getFloatValueByName(List<EdgeAttribute> edgeAttributeList, String edgeAttributeName) {
		for (EdgeAttribute edgeAttr : edgeAttributeList) {
			if (edgeAttr.getName().equals(edgeAttributeName) == true
					&& edgeAttr.getName().equalsIgnoreCase(EdgeFormatIdentifier.FLOAT_TYPE.getValue())) {
				return (Float) edgeAttr.getValue();
			} else if (edgeAttr.getDataType().equals(EdgeFormatIdentifier.ATTRIBUTES_TYPE.getValue()) == true) {
				return getFloatValueByName(covertAttrubiteListFromObject(edgeAttr.getValue()), edgeAttributeName);
			}
		}
		return 0.0f;
	}
	
	private static List<EdgeAttribute> covertAttrubiteListFromObject(Object obj){
		ArrayList arr = (ArrayList) obj;
		List<EdgeAttribute> attributeList = new ArrayList<EdgeAttribute>();
		for (int i = 0; i < arr.size(); i++) {
			attributeList.add((EdgeAttribute) arr.get(i));
		}
		return attributeList;
	}
}