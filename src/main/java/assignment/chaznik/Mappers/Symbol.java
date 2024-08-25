package assignment.chaznik.Mappers;

import assignment.chaznik.Enums.Impact;
import assignment.chaznik.Enums.SymbolType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Symbol
{
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    private SymbolType type;
    private Impact impact;
    private double extra;

    public Symbol(double extra, Impact impact, double rewardMultiplier, SymbolType type) {
        this.extra = extra;
        this.impact = impact;
        this.rewardMultiplier = rewardMultiplier;
        this.type = type;
    }

    public Symbol() {}

    public double getExtra() {
        return extra;
    }

    public Impact getImpact() {
        return impact;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public SymbolType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Symbol{" + "rewardMultiplier=" + rewardMultiplier + ", type=" + type + ", impact='" + impact + '\'' + ", extra=" + extra + "}";
    }
}
