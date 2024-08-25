package assignment.chaznik.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SymbolType {
    @JsonProperty("standard")
    STANDARD,
    @JsonProperty("bonus")
    BONUS
}
