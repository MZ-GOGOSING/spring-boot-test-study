package me.gogosing.support.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class FilterNumericRange {

  /**
   * 필터링 적용 최소 수.
   */
  @JsonProperty(value = "min")
  private BigDecimal min = BigDecimal.ZERO;

  /**
   * 필터링 적용 최대 수.
   */
  @JsonProperty(value = "max")
  private BigDecimal max = BigDecimal.ZERO;
}
