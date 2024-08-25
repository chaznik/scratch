package assignment.chaznik.Mappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WinCombination {
    @JsonProperty("reward_multiplier")
    public double rewardMultiplier;
    private String when;
    private int count;
    private String group;
    @JsonProperty("covered_areas")
    private String[][] coveredAreas;

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String[][] getCoveredAreas() {
        return coveredAreas;
    }

    public void setCoveredAreas(String[][] coveredAreas) {
        this.coveredAreas = coveredAreas;
    }
}
