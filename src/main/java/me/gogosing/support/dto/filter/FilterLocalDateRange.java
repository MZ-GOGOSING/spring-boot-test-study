package me.gogosing.support.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class FilterLocalDateRange implements Serializable {

  /**
   * 필터링 적용 시작일.
   */
  @JsonProperty(value = "startDate")
  private LocalDate startDate;

  /**
   * 필터링 적용 종료일.
   */
  @JsonProperty(value = "endDate")
  private LocalDate endDate;

  public LocalDateTime getStartDateTime() {
    return startDate == null ? null : startDate.atStartOfDay();
  }

  public LocalDateTime getEndDateTime() {
    return endDate == null ? null : LocalDateTime.of(endDate, LocalTime.MAX);
  }

  @Builder
  public FilterLocalDateRange(
      LocalDate startDate,
      LocalDate endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
