package me.gogosing.support.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 기간 필터링 조건 모델.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class FilterLocalDateTimeRange {

  /**
   * 필터링 적용 시작일.
   */
  @JsonProperty(value = "startDate")
  private LocalDateTime startDate;

  /**
   * 필터링 적용 종료일).
   */
  @JsonProperty(value = "endDate")
  private LocalDateTime endDate;

  @Builder
  public FilterLocalDateTimeRange(
      LocalDateTime startDate,
      LocalDateTime endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
