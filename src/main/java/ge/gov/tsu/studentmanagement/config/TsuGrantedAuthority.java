package ge.gov.tsu.studentmanagement.config;

import ge.gov.tsu.studentmanagement.entity.security.AccessType;
import org.springframework.security.core.GrantedAuthority;

public class TsuGrantedAuthority implements GrantedAuthority{
    private String permissionName;
    private AccessType accessType;

    public TsuGrantedAuthority(String permissionName, AccessType accessType) {
        this.permissionName = this.permissionName;
        this.accessType = accessType;
    }

    @Override
    public String getAuthority() {
        return this.permissionName + "_" + accessType.name();
    }
}