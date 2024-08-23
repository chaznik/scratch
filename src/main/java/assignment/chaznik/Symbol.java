package assignment.chaznik;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Symbol
{
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    private SymbolType type;
    private String impact;
    private double extra;

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }
}
