-- Инициализация таблицы ROLE
INSERT INTO ROLE (name) VALUES
('Администратор'),
('Менеджер'),
('Пользователь');

-- Инициализация таблицы POLZOVATELI
INSERT INTO POLZOVATELI (familia, name, email, password, telefon, vozrst, lgota, id_role) VALUES
('Смирнов', 'Дмитрий', 'dmitry@example.com', 'password', '+7(912)345-67-89', '1991-05-14', false, 1),
('Козлов', 'Олег', 'oleg@example.com', 'password', '+7(922)222-22-22', '1994-08-22', false, 3),
('Михайлова', 'Ольга', 'olga@example.com', 'password', '+7(932)333-33-33', '1985-12-05', false, 2);

-- Инициализация таблицы STATUS
INSERT INTO STATUS (name) VALUES
('На рассмотрении'),
('Одобрено'),
('Отклонено');

-- Инициализация таблицы ACTER
INSERT INTO ACTER (name, familia, vozrast) VALUES
('Алексей', 'Иванов', '1990-05-14'),
('Дмитрий', 'Сергеев', '1985-11-22'),
('Екатерина', 'Николаева', '1978-03-07');

-- Инициализация таблицы SHOW
INSERT INTO SHOW (nazvatie, cena, opisanie, vozrast_ogr, photo) VALUES
('Волшебный мир приключений', 1000, 'Захватывающее шоу для всей семьи с магией и чудесами.', 5, 'show_photo_1.jpg'),
('Невероятные иллюзии', 1500, 'Иллюзионисты поражают воображение своими талантами.', 16, 'show_photo_2.jpg'),
('Шоу огня и света', 900, 'Яркие выступления с огненными и световыми эффектами.', 16, 'show_photo_3.jpg');

-- Связь актеров и шоу
INSERT INTO ACTER_PREDSTAVLENIE (id_show, id_acter) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 1),
(3, 3);

-- Инициализация таблицы POKAZ
INSERT INTO POKAZ (id_show, date, time) VALUES
(1, CURRENT_DATE + 7, '16:30:00'),
(1, CURRENT_DATE + 14, '12:00:00'),
(2, CURRENT_DATE + 10, '19:00:00'),
(3, CURRENT_DATE + 5, '20:00:00');