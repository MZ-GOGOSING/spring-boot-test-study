package me.gogosing.support.dto.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 기간 필터링 조건 모델.
 */
@Schema(description = "날짜 범위 검색 모델")
@ParameterObject
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class FilterLocalDateRange implements Serializable {

  /**
   * 필터링 적용 시작일.
   */
  @JsonProperty(value = "startDate")
  @DateTimeFormat(iso = ISO.DATE)
  @Parameter(description = "yyyy-MM-dd 형식의 검색 시작일 (goe)")
  private LocalDate startDate;

  /**
   * 필터링 적용 종료일.
   */
  @JsonProperty(value = "endDate")
  @DateTimeFormat(iso = ISO.DATE)
  @Parameter(description = "yyyy-MM-dd 형식의 검색 종료일 (loe)")
  private LocalDate endDate;

  @JsonIgnore
  public LocalDateTime getStartDateTime() {
    return Optional.ofNullable(startDate)
        .map(LocalDate::atStartOfDay)
        .orElse(null);
  }

  @JsonIgnore
  public LocalDateTime getEndDateTime() {
    return Optional.ofNullable(endDate)
        .map(it -> LocalDateTime.of(endDate, LocalTime.MAX))
        .orElse(null);
  }


  @Builder
  public FilterLocalDateRange(
      LocalDate startDate,
      LocalDate endDate
  ) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
