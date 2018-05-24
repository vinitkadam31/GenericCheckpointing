package genericCheckpointing.strategy;

import genericCheckpointing.util.SerializableObject;

public interface StrategyI {
	void processInput(SerializableObject sObject, String wireFormat);
	String getWireFormat();
	SerializableObject getDeserializedObject();
}
