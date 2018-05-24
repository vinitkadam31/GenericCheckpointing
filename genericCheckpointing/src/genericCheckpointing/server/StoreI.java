package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface StoreI extends StoreRestoreI {
	String writeObj(MyAllTypesFirst aRecord, int authID, String wireFormat);
    String writeObj(MyAllTypesSecond bRecord, int authID, String wireFormat);
}
