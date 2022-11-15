INSERT INTO products(title,variety)
VALUES  ('apples','green'),
        ('banana','yellow'),
        ('tomato','red'),
        ('potato','wild'),
        ('oranges','oranges'),
        ('pineapple','black');

INSERT INTO providers(title,TIN,payment_account)
VALUES  ('VotchinaLogus','418467','50803760'),
        ('LogisGusi','607636','17253906'),
        ('VitaKamin','239678','32086753'),
        ('FunnyArbuser','777777','77777777'),
        ('Kolpak','34536','456456');

INSERT INTO invoice_info(date,provider)
VALUES  ('1913-06-12','LogisGusi'),
        ('1913-06-12','VotchinaLogus'),
        ('1913-08-20','VotchinaLogus'),
        ('1915-03-04','FunnyArbuser'),
        ('1918-07-14','VitaKamin');

INSERT INTO invoice_data(invoice_id,product,price,amount)
VALUES  (1,2,250,10),
        (1,3,500,20),
        (2,3,333,13),
        (2,4,33,31),
        (3,1,521,50),
        (3,5,712,75),
        (4,3,123,56),
        (4,4,188,36),
        (5,2,100,150),
        (5,4,200,400);
