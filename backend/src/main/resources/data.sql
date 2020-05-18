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


--recipes
INSERT INTO `recipes` (id, name, description, recipe_type) VALUES (1, 'recipe1', 'recipe1', 0);
INSERT INTO `recipes` (id, name, description, recipe_type) VALUES (2, 'recipe2', 'recipe2', 1);
--ingredients
INSERT INTO `ingredients` (id, name, description, ingredient_type, price, quantity, calories, recipe_id,total_price,total_calories) VALUES (1, 'ingredient1', 'ingredient1', 0, 10,10,10,1,0,0);
INSERT INTO `ingredients` (id, name, description, ingredient_type, price, quantity, calories, recipe_id,total_price,total_calories) VALUES (2, 'ingredient2', 'ingredient2', 0, 20,20,20,1,0,0);
INSERT INTO `ingredients` (id, name, description, ingredient_type, price, quantity, calories, recipe_id,total_price,total_calories) VALUES (3, 'ingredient3', 'ingredient3', 0, 30,30,30,2,0,0);
INSERT INTO `ingredients` (id, name, description, ingredient_type, price, quantity, calories, recipe_id,total_price,total_calories) VALUES (4, 'ingredient4', 'ingredient4', 0, 40,40,40,2,0,0);
INSERT INTO `ingredients` (id, name, description, ingredient_type, price, quantity, calories, recipe_id,total_price,total_calories) VALUES (5, 'ingredient5', 'ingredient5', 1, 50,50,50,1,0,0);
INSERT INTO `ingredients` (id, name, description, ingredient_type, price, quantity, calories, recipe_id,total_price,total_calories) VALUES (6, 'ingredient6', 'ingredient6', 1, 60,60,60,1,0,0);
INSERT INTO `ingredients` (id, name, description, ingredient_type, price, quantity, calories, recipe_id,total_price,total_calories) VALUES (7, 'ingredient7', 'ingredient7', 1, 70,70,70,2,0,0);
INSERT INTO `ingredients` (id, name, description, ingredient_type, price, quantity, calories, recipe_id,total_price,total_calories) VALUES (8, 'ingredient8', 'ingredient8', 1, 80,80,80,2,0,0);


        
        
