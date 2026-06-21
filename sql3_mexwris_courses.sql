BEGIN;


create table if not exists student(
                                      id numeric,
                                      username varchar(30),
                                      name varchar(30),
                                      surename varchar(30),
                                      password varchar(30),
                                      department varchar(30),
                                      primary key (id)
);
create table if not exists courses(
                                      course_id numeric,
                                      course_name varchar(30),
                                      primary key (course_id)
);
create table if not exists grades
(
    s_id         numeric,
    c_id         numeric,
    grade        numeric,
    exetatistiki numeric,
    foreign key (s_id) references student(id),
    foreign key (c_id) references courses(course_id),
    primary key (s_id,c_id,exetatistiki)
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
