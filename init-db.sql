CREATE TABLE month_kpi (
  month date NOT NULL PRIMARY KEY,
  total_salary bigint NOT NULL
);

CREATE TABLE employee_kpi (
  month date NOT NULL,
  total_employees bigint NOT NULL,
  status uuid NOT NULL
);

CREATE TABLE department_distribution (
  department_id uuid NOT NULL,
  total_employees bigint NOT NULL,
  total_salary
);

CREATE TABLE it_distribution (
  job_title_id uuid NOT NULL,
  total_employees bigint NOT NULL,
);