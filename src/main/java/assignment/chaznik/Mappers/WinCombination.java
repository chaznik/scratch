package assignment.chaznik.Mappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WinCombination {
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    private String when;
    private int count;
    private String group;
    @JsonProperty("covered_areas")
    private String[][] coveredAreas;

    public WinCombination(double rewardMultiplier, String when, int count, String group, String[][] coveredAreas) {
        this.rewardMultiplier = rewardMultiplier;
        this.when = when;
        this.count = count;
        this.group = group;
        this.coveredAreas = coveredAreas;
    }

    public WinCombination() {}

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public String getWhen() {
        return when;
    }

    public int getCount() {
        return count;
    }

    public String getGroup() {
        return group;
    }

    public String[][] getCoveredAreas() {
        return coveredAreas;
    }

    @Override
    public String toString() {
        return "WinCombination{" + "rewardMultiplier=" + rewardMultiplier + ", when='"
                + when + '\'' + ", count=" + count + ", group='" + group + '\'' + '}';
    }
}
