package com.antharos.analytics.infrastructure.in.controller;

import com.antharos.analytics.domain.*;
import com.antharos.analytics.domain.service.ReportService;
import com.antharos.analytics.infrastructure.security.ManagementOnly;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/kpi")
@Tag(name = "KPI", description = "Analytics and KPIs related to employees and salaries")
public class KpiController {

  private final ReportService reportService;

  @ManagementOnly
  @GetMapping("/employees/monthly")
  @Operation(
      summary = "Get employee count by month",
      description = "Returns number of employees hired each month")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved employee counts",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MonthlyEmployeeCount.class))),
    @ApiResponse(responseCode = "403", description = "Access denied"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<MonthlyEmployeeCount> getEmployeesByMonth() {
    return reportService.getEmployeeCountByMonth();
  }

  @ManagementOnly
  @GetMapping("/salary/monthly")
  @Operation(
      summary = "Get salary cost by month",
      description = "Returns monthly aggregated salary cost")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved salary data",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MonthlySalaryCost.class))),
    @ApiResponse(responseCode = "403", description = "Access denied"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<MonthlySalaryCost> getSalaryByMonth() {
    return reportService.getSalaryCostByMonth();
  }

  @ManagementOnly
  @GetMapping("/employees/department")
  @Operation(
      summary = "Get employee count by department",
      description = "Returns total employees grouped by department")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved department-wise employee count",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DepartmentEmployees.class))),
    @ApiResponse(responseCode = "403", description = "Access denied"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<DepartmentEmployees> getEmployeesByDepartment() {
    return reportService.getEmployeesByDepartment();
  }

  @ManagementOnly
  @GetMapping("/salary/department")
  @Operation(
      summary = "Get salary cost by department",
      description = "Returns total salary cost grouped by department")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved department salary cost",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DepartmentSalary.class))),
    @ApiResponse(responseCode = "403", description = "Access denied"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<DepartmentSalary> getSalaryByDepartment() {
    return reportService.getSalaryByDepartment();
  }

  @ManagementOnly
  @GetMapping("/employees/job-title")
  @Operation(
      summary = "Get employee count by job title",
      description = "Returns total employees grouped by job title")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved job title employee count",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = JobTitleEmployees.class))),
    @ApiResponse(responseCode = "403", description = "Access denied"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<JobTitleEmployees> getEmployeesByJobTitle() {
    return reportService.getEmployeesByJobTitle();
  }
}
