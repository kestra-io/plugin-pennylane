package io.kestra.plugin.pennylane.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Changelog {

    @Schema(title = "Identifier of the resource this changelog event refers to")
    @JsonProperty("id")
    private Long id;

    @Schema(title = "Operation applied to the resource: insert, update, or delete")
    @JsonProperty("operation")
    private String operation;

    @Schema(title = "Timestamp when Pennylane processed the change, oldest first")
    @JsonProperty("processed_at")
    private String processedAt;

    @Schema(title = "Resource updated_at copied onto the changelog event")
    @JsonProperty("updated_at")
    private String updatedAt;

    @Schema(title = "Resource created_at copied onto the changelog event")
    @JsonProperty("created_at")
    private String createdAt;

    @Builder.Default
    private Map<String, Object> additionalProperties = new HashMap<>();

    public boolean deleted() {
        return operation != null && operation.equalsIgnoreCase("delete");
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        if (this.additionalProperties == null) {
            this.additionalProperties = new HashMap<>();
        }
        this.additionalProperties.put(name, value);
    }
}
