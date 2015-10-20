package fil.iagl.iir.entite;

import java.io.Serializable;

public enum Role implements Serializable {

  PARTICULIER(1);

  private Integer id;

  Role(Integer id) {
    this.id = id;
  }

  public static Role findById(Integer id) {
    for (Role role : Role.values()) {
      if (id.equals(role.getId())) {
        return role;
      }
    }
    throw new RuntimeException("No Enum Found for ID : " + id);
  }

  public Integer getId() {
    return id;
  }

}
