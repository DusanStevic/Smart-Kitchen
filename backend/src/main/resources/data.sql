-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Ovo su sve imena kolona u tabelama db(Column anotacija u klasama), a ne imena polja u klasama

INSERT INTO authorities (id, role) VALUES (1, 'ROLE_REGISTERED_USER');
INSERT INTO authorities (id, role) VALUES (2, 'ROLE_ADMIN');


-- password is 'user' (bcrypt encoded)
INSERT INTO users (user_type, id, username, password, first_name, last_name, email, enabled,last_password_reset_date, phone_number, first_time, image_url) VALUES ( 'user', 1, 'user', '$2a$10$AIj1/uEYflVWgnovOkb0Uu8FgMYTIdgX.MCm4ZLT9Xvd66h/1UnuC','User_Name', 'User_Lastname', 'user@gmail.com', true, '2020-04-22 22:22:00', '0641234567', false, 'https://res.cloudinary.com/djxkexzcr/image/upload/v1574108111/zbvvptxlxzzhzomjvp2s.jpg');
-- password is 'admin' (bcrypt encoded)
INSERT INTO users (user_type, id, username, password, first_name, last_name, email, enabled,last_password_reset_date, phone_number, first_time, image_url) VALUES ( 'admin', 2, 'admin', '$2a$10$CDnBIedTKUJbyG3b/qJ2G.Vjx45s9nCZFpPn9id/eIh/siZ81bO3W','Admin_Name', 'Admin_Lastname', 'admin@gmail.com', true, '2020-04-22 22:22:00', '0647654321', false, 'https://res.cloudinary.com/djxkexzcr/image/upload/v1574108111/zbvvptxlxzzhzomjvp2s.jpg');



INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1); -- registered user has ROLE_REGISTERED_USER
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2); -- adminstrator has ROLE_ADMIN




        
        
