create table lessons
(
	id serial not null
		constraint lessons_pk
			primary key,
	day pg_enum not null
);

create table subjects
(
    id serial not null
        constraint subjects_pk
            primary key,
    name varchar not null
);

create table students
(
    id serial not null
        constraint students_pk
            primary key,
    name varchar not null,
    surname varchar not null
);