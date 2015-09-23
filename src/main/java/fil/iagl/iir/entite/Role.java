package fil.iagl.iir.entite;

import lombok.Getter;

public enum Role {

	PARTICULIER(1);

	@Getter
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

}
