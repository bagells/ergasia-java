BEGIN;
create table if not exists grades(
                                     grade_id numeric,
                                     grade_number numeric,
                                     primary key (grade_id)
);
create table if not exists courses(
                                      course_id numeric,
                                      course_name "varchar",
                                      primary key (course_id)
);
create table if not exists student(
                                      id numeric,
                                      name "varchar",
                                      surename "varchar",
                                      courses numeric,
                                      grades"numeric",
                                      password varchar,
                                      primary key (id),
                                      foreign key (courses) REFERENCES courses(course_id),
                                      foreign key (grades) references grades(grade_id)
);
create table if not exists proffesor(
                                        id numeric,
                                        name "varchar",
                                        surename "varchar",
                                        courses numeric,
                                        password varchar,
                                        primary key (id),
                                        foreign key (courses) REFERENCES courses(course_id)

);
create table if not exists secratary(
                                        id numeric,
                                        name "varchar",
                                        surename "varchar",
                                        courses numeric,
                                        password varchar,
                                        primary key (id),
                                        foreign key (courses) REFERENCES courses(course_id)
);

END;
