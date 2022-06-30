package terry.kong.naceservice.controller.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import terry.kong.naceservice.entity.Nace;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NaceDto {
    private Long order;
    private Integer level;
    private String code;
    private String parentCode;
    private String desc;
    private String includes;
    private String includesAlso;
    private String rulings;
    private String excludes;
    private String ref;

    public static NaceDto toDto(Nace nace){
        return NaceDto.builder().code(nace.getCode())
                .order(nace.getOrder())
                .level(nace.getLevel())
                .parentCode(nace.getParent())
                .desc(nace.getDesc())
                .includes(nace.getIncludes())
                .includesAlso(nace.getIncludesAlso())
                .rulings(nace.getRulings())
                .excludes(nace.getExcludes())
                .ref(nace.getRef()).build();
    }
}
