package uz.pdp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.entity.base.IdEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends IdEntity {
    private String name;
}
