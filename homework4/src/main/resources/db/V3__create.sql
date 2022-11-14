CREATE TABLE courses (
    course_id VARCHAR(20) PRIMARY KEY NOT NUll,
    course_title VARCHAR(40) NOT NULL,
    newfeatures VARCHAR(40) NOT NULL
);

INSERT INTO courses(course_id,course_title,newfeatures)
VALUES  ('JAVA_SPRING22','Java: from words to deeds','Changed second and third lectures'),
        ('QA_SPRING22','Testing game projects','add more live codeing');

ALTER TABLE students ADD FOREIGN KEY (course_id) REFERENCES courses(course_id);
ALTER TABLE schedule ADD FOREIGN KEY (course_id) REFERENCES courses(course_id);