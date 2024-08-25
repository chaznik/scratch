package assignment.chaznik.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Impact {
    @JsonProperty("multiply_reward")
    MULTIPLY,
    @JsonProperty("extra_bonus")
    ADD,
    @JsonProperty("miss")
    NONE
}
