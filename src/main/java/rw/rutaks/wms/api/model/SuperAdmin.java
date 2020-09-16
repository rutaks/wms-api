package rw.rutaks.wms.api.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SUPER_ADMIN")
public class SuperAdmin extends User {}
