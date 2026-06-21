BEGIN;
create table if not exists grades(
                                     grade_id numeric,
                                     grade_number numeric,
                                     primary key (grade_id)
);
create table if not exists student(
                                      id numeric,
                                      username varchar(30),
                                      name varchar(30),
                                      surename varchar(30),
                                      courses numeric,
                                      grades"numeric",
                                      password varchar(30),
                                      department varchar(30),
                                      primary key (id),
                                      foreign key (grades) references grades(grade_id)
);
create table if not exists courses(
                                      course_id numeric,
                                      course_name varchar(30),
                                      primary key (course_id)
);
create table if not exists dilosi(
                                     id numeric,
                                     c_id numeric,
                                     s_id numeric,
                                     primary key  (id),
                                     foreign key (c_id) references  courses(course_id),
                                     foreign key (s_Id) references  student(id)
);


create table if not exists proffesor(
                                        id numeric,
                                        name varchar(30),
                                        username varchar(30),
                                        surename varchar(30),
                                        courses numeric,
                                        password varchar(30),
                                        department varchar(30),
                                        primary key (id),
                                        foreign key (courses) REFERENCES courses(course_id)

);
create table if not exists dilosi_k(
                                       id numeric,
                                       p_id numeric,
                                       c_id numeric,
                                       primary key (id),
                                       foreign key(p_id) references proffesor(id),
                                       foreign key (c_id) references courses(course_Id)
);
create table if not exists secratary(
                                        id numeric,
                                        username varchar(30),
                                        name varchar(30),
                                        surename varchar(30),
                                        courses numeric,
                                        password varchar(30),
                                        department varchar(30),
                                        primary key (id),
                                        foreign key (courses) REFERENCES courses(course_id)
);

END;
