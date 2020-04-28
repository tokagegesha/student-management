package ge.gov.tsu.studentmanagement.entity.security;

import java.util.HashMap;
import java.util.Map;

public enum AccessType {
    CREATE(0), READ(1), UPDATE(2), DELETE(3);

    private static Map<Integer, AccessType> map;

    static {
        map = new HashMap<>();
        for (AccessType a : AccessType.class.getEnumConstants()) {
            map.put(a.getValue(), a);
        }
    }

    int value;

    AccessType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AccessType getByValue(int i) {
        return map.get(i);
    }

    public static int length() {
        return AccessType.class.getEnumConstants().length;
    }


}