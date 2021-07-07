package me.gogosing.support.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 숫자 필터링 조건 모델.
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "숫자 위 모델")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class FilterNumericRange {

  /**
   * 필터링 적용 최소 수.
   */
  @JsonProperty(value = "min")
  @ApiModelProperty("검색 최소 수")
  private BigDecimal min = BigDecimal.ZERO;

  /**
   * 필터링 적용 최대 수.
   */
  @JsonProperty(value = "max")
  @ApiModelProperty("검색 최대 수")
  private BigDecimal max = BigDecimal.ZERO;
}
