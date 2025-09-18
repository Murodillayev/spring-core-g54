package uz.pdp.dependency_inversion;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AService {
    private final BService bService;
    public AService(@Lazy BService bService) {
        this.bService = bService;
    }
}
