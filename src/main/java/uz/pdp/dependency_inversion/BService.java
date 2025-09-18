package uz.pdp.dependency_inversion;

import org.springframework.stereotype.Component;

@Component
public class BService {

    private final AService aService;

    public BService(AService aService) {
        this.aService = aService;
    }
}
