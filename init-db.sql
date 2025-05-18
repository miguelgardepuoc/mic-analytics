CREATE TABLE month_kpi (
  month date NOT NULL PRIMARY KEY,
  total_salary NUMERIC(19, 2) NOT NULL
);

CREATE TABLE employee_kpi (
  month date NOT NULL,
  total_employees bigint NOT NULL
);

CREATE TABLE department_distribution (
  department_id uuid NOT NULL,
  total_employees bigint NOT NULL,
  total_salary NUMERIC(19, 2) NOT NULL
);

CREATE TABLE it_distribution (
  job_title_id uuid NOT NULL,
  total_employees bigint NOT NULL
);

INSERT INTO month_kpi (month, total_salary) VALUES
('2025-01-01', 300000),
('2025-02-01', 400000),
('2025-03-01', 500000),
('2025-04-01', 800000),
('2025-05-01', 1555000);

INSERT INTO employee_kpi (month, total_employees) VALUES
('2025-01-01', 5),
('2025-02-01', 10),
('2025-03-01', 9),
('2025-04-01', 15),
('2025-05-01', 30);

INSERT INTO department_distribution (department_id, total_employees, total_salary) VALUES
('a4a77bc5-e352-4ac0-8ec3-d3af8271f61f', 21, 1260000), -- IT Department
('db978bbe-a875-4368-a58a-3a864d3af8ec', 4, 140000),  -- Human Resources
('2bc45806-1fc2-47f5-a39a-2d60f399f47a', 3, 150000),  -- Finance
('3bcc77b4-13c3-4f73-9627-0c27ab3ffe8d', 2, 50000);  -- Marketing

INSERT INTO it_distribution (job_title_id, total_employees) VALUES
('d8f90d3f-b6a9-4c45-9f4f-951f1d1b9571', 2),
('bf3e2b6b-d2e9-409f-97b3-b36ec0b1a90e', 6),
('c7924409-7eaf-4022-b0b3-9b8a78a0bb7d', 1),
('098bd9b4-8b57-4a18-bbde-9e6d1d678cfa', 5),
('dbf1a11c-f7f5-4b9f-98d4-e83c72ab1c57', 2),
('f6180285-9f66-493b-b629-0892118c6d75', 1),
('3c01ef99-9c73-4bac-a27f-24dc5089df16', 2),
('e513050a-a90a-4d3c-b820-113b9e098e52', 2);
