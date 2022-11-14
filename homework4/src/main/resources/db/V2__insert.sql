INSERT INTO students(surname,name, patronymic,course_id)
VALUES  ('Hrenov','Maksim','Sergeevich','JAVA_SPRING22'),
        ('Pupkin','Petya','Alekseevich','JAVA_SPRING22'),
        ('Ivanov','Ivan','Ivanovich','QA_SPRING22'),
        ('Bogatyrev','Alesha','Popovich','QA_SPRING22');

INSERT INTO teachers(surname,name,patronymic)
VALUES  ('Gorshkov','Artem','Mihailovich'),
        ('Antonov','Matvei','Artemievich');

INSERT INTO schedule(lesson_id,course_id,date,lesson_title)
VALUES  ('1.22j','JAVA_SPRING22','2022-05-09 18:00:00','Creating project.'),
        ('2.22j','JAVA_SPRING22','2022-12-09 18:00:00','Depency injection.'),
        ('1.22q','QA_SPRING22','2022-04-09 18:00:00','Introduction to testing.'),
        ('2.22q','QA_SPRING22','2022-06-09 18:00:00','Types of development.');

INSERT INTO journal(lesson_id,student_id,teacher_id,was_on_lecture,homework_mark)
VALUES  ('1.22j',1,1,true,4),
        ('2.22j',2,2,false,4),
        ('1.22q',3,1,false,2),
        ('2.22q',4,2,true,0);



