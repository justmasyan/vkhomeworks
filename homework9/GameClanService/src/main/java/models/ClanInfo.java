package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter @Setter
public class ClanInfo implements Serializable {

    @JsonProperty
    private final String clanTitle;

    @JsonProperty
    private final int maxAmountMembers;

    @JsonProperty
    private final List<String> members;

    @JsonCreator
    public ClanInfo(@JsonProperty("clanTitle")          String clanTitle,
                    @JsonProperty("maxAmountMembers")   int maxAmountMembers,
                    @JsonProperty("members")            List<String> members) {
        this.clanTitle = clanTitle;
        this.maxAmountMembers = maxAmountMembers;
        this.members = members;
    }

    public ClanInfo() {
        clanTitle = null;
        maxAmountMembers = -1;
        members = null;
    }

    @Override
    public String toString() {
        return "{" +
                "\"clanTitle\"=\"" + clanTitle + "\",\n" +
                "\"maxAmountMembers\"=\"" + maxAmountMembers + "\",\n" +
                "\"members\"=" + members + "\n" +
                '}';
    }

}
