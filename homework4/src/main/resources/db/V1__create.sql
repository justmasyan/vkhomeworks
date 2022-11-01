CREATE TABLE students(
    student_id SERIAL PRIMARY KEY NOT NULL,
    surname VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    patronymic VARCHAR(20),
    course_id VARCHAR(20)
);

CREATE TABLE teachers(
    teacher_id SERIAL PRIMARY KEY NOT NULL,
    surname VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    patronymic VARCHAR(20)
);

CREATE TABLE schedule(
    lesson_id VARCHAR(20) NOT NULL PRIMARY KEY,
    course_id VARCHAR(20) NOT NULL,
    lesson_title VARCHAR(40) NOT NULL,
    date TIMESTAMP NOT NULL
);

CREATE TABLE journal(
    lesson_id VARCHAR(20) PRIMARY KEY NOT NULL,
    student_id INTEGER NOT NULL,
    teacher_id INTEGER NOT NULL,
    was_on_lecture BOOLEAN NOT NULL,
    homework_mark INTEGER CHECK(homework_mark >= 0 AND homework_mark <= 5) NOT NULL
)

