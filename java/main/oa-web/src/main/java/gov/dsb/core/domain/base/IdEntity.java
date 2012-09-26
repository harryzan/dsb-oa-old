package gov.dsb.core.domain.base;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-1-8
 * Time: 13:08:01
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass()
public abstract class IdEntity {

//    protected Long id;

    @Id
    @SequenceGenerator(name = "OA", sequenceName = "oa", initialValue = 100000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA")
    abstract public Long getId();
//
    abstract public void setId(Long id);
}
