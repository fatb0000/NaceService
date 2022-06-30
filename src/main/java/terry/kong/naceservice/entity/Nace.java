package terry.kong.naceservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`NACE`")
@Data
public class Nace {
    @Id
    @Column(name = "`ORDER`")
    private Long order;
    @Column(name = "`LEVEL`")
    private Integer level;
    @Column(name = "`CODE`")
    private String code;
    @Column(name = "`PARENT`")
    private String parent;
    @Column(name = "`DESC`")
    private String desc;
    @Column(name = "`INCLUDES`")
    private String includes;
    @Column(name = "`INCLUDES_ALSO`")
    private String includesAlso;
    @Column(name = "`RULINGS`")
    private String rulings;
    @Column(name = "`EXCLUDES`")
    private String excludes;
    @Column(name = "`REF`")
    private String ref;

}
