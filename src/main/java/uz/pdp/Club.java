package uz.pdp;

import java.util.List;

public class Club {
    List<String> members = List.of("Muhammadkomil", "Oybek", "Abbos");

    public boolean isMember(String member) {
        return members.contains(member);
    }
}
